// Deploy to root and set env variables in local dev mode
grails.app.context = '/'
grails.config.locations = ["file:${userHome}/.grails/devops-simulator-config.groovy"]
grails.project.groupId = 'benorama.devops.simulator'

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {

    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: "%d{ISO8601} %p %c{1} - %m%n")
        environments {
            development {
                rollingFile name: 'stacktrace', file: "target/stacktrace.log"
            }
            production {
                rollingFile name: 'logFile', file: "${System.getProperty('catalina.base') ?: 'target'}/logs/${appName}.log"
                rollingFile name: 'stacktrace', file: "${System.getProperty('catalina.base') ?: 'target'}/logs/${appName}-stacktrace.log"
            }
        }
    }

    environments {
        development {
            root {
                error 'stdout' // Default loggers with ERROR level to console (by default, it logs ERROR level and above)
            }
            // Loggers with DEBUG level
            debug   'grails.app.conf',
                    'grails.app.controllers',
                    'grails.app.domain',
                    'grails.app.filters',
                    'grails.app.services'
        }
        production {
            root {
                error 'logFile', 'sentry' // Default loggers with ERROR level to rolling file (by default, it logs ERROR level and above)
            }
        }
    }

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Grails config
environments {
    development {
        grails.serverURL = "http://localhost:8080"
    }
    production {
        grails.serverURL = System.getProperty('GRAILS_SERVER_URL')
    }
}

/**
 * Grails plugins
 */
grails {
    assets {
        excludes = [
                'newrelic/*',                   // .ebextensions
                '*.sh',                         // .ebextensions
                '*.config',                     // .ebextensions
                'apple-touch-icon.png',         // legacy assets from plugins web-app
                'apple-touch-icon-retina.png',  // legacy assets from plugins web-app
                'grails_logo.png',              // legacy assets from plugins web-app
                'main.css',                     // legacy assets from plugins web-app
                'mobile.css',                   // legacy assets from plugins web-app
                'skin/**',                      // legacy assets from plugins web-app
                'spinner.gif',                  // legacy assets from plugins web-app
                'springsource.png',             // legacy assets from plugins web-app
                'libs/**',                      // all dependencies assets
        ]
        includes = [
                'libs/bootstrap-datepicker/js/**.js',
                'libs/font-awesome/fonts/*',
                'libs/jquery/dist/jquery.js',
                'libs/modernizr/modernizr.js',
                'libs/seiyria-bootstrap-slider/dist/*'
        ]
        minifyOptions = [
                mangleOptions: [mangle: false] // Otherwise it generate issues with AngularJS dependencies injection
        ]
    }
    plugin {
        // Facebook SDK plugin
        facebooksdk {
            app = [
                    id: "${System.getProperty('FACEBOOK_APP_ID') ?: 0}".toLong(),
                    secret: System.getProperty('FACEBOOK_APP_SECRET')
            ]
        }
        // Segmentio plugin
        segmentio {
            apiKey = System.getProperty('SEGMENTIO_API_KEY')
        }
    }
    plugins {
        // Raven plugin (Sentry)
        raven {
            dsn = System.getProperty('SENTRY_DSN')
        }
    }
}

/**
 * Assets
 */
def cdnStoragePath = "assets/${appName}-${appVersion}/"
environments {
    deployment {
        // Env only used for asset-cdn-push (with a bucket env parameter)
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
        grails {
            assets {
                cdn {
                    provider = 'S3'
                    region = 'eu-west-1'
                    storagePath = cdnStoragePath
                    expires = 365
                }
            }
        }
    }
    production  {
        grails.assets.url = "${System.getProperty('ASSETS_URL')}/${cdnStoragePath}"
    }
}

/**
 * AWS Beanstalk (only used by Travis for deployment)
 */
grails {
    plugin {
        awsElasticBeanstalk {
            serviceEndpointUrl = 'https://elasticbeanstalk.eu-west-1.amazonaws.com'
            accessKey = System.getProperty('BEANSTALK_ACCESS_KEY')
            secretKey = System.getProperty('BEANSTALK_SECRET_KEY')
            applicationName = 'Demo'
        }
    }
}
environments {
    production {
        grails {
            plugin {
                awsElasticBeanstalk {
                    environmentName = "devops-simulator-${appVersion.replaceAll('\\.' ,'-')}"
                    savedConfigurationName = 'devops-simulator'
                }
            }
        }
    }
}