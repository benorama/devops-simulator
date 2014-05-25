import benorama.devops.simulator.AccountService
import grails.plugin.facebooksdk.FacebookContext
import org.codehaus.groovy.grails.commons.GrailsApplication

import javax.servlet.http.HttpSession

class AuthenticationFilters {

    FacebookContext facebookContextProxy

    def filters = {

        all(controller: '^(account|assets|facebookSdk|error|home|monitor)$', invert: true) {
            before = {
                if (!isAuthenticatedAndValid(session)) {
                    log.debug "Redirect to home"
                    redirect controller: 'home'
                    return false
                }
                return true
            }
        }

        home(controller: 'home') {
            before = {
                if (isAuthenticatedAndValid(session)) {
                    log.debug "Redirect to simulator"
                    redirect controller: 'simulator'
                    return false
                }
            }
        }

    }

    def isAuthenticatedAndValid(HttpSession session) {
        facebookContextProxy.authenticated && session.accountId
    }

}