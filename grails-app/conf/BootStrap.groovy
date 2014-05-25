import grails.plugins.raven.RavenClient
import org.codehaus.groovy.grails.commons.GrailsApplication

class BootStrap {

    GrailsApplication grailsApplication
    RavenClient ravenClient

    def init = { servletContext ->
        // Send bootstrap message to Sentry (to check if Sentry works correctly)
        ravenClient.captureMessage("${grailsApplication.metadata['app.name']} ${grailsApplication.metadata['app.version']} successfully bootstrapped")
    }

}
