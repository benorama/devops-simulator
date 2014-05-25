package benorama.devops.simulator

import grails.plugin.facebooksdk.FacebookContext

class HomeController {

    static Random random = new Random()

    FacebookContext facebookContext

    def beforeInterceptor = {
        log.info "START ${actionUri} with params=${params}"
    }
    def afterInterceptor = {
        log.info "END ${actionUri}"
    }

    def index() {
        [
                facebookContext: facebookContext,
                tweetId: random.nextInt(9)
        ]
    }

}
