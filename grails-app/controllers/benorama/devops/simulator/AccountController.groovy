package benorama.devops.simulator

import grails.plugin.facebooksdk.FacebookContext

class AccountController {

    static allowedMethods = [install: 'POST']

    AccountService accountService
    FacebookContext facebookContext

    def signin() {
        if (facebookContext.authenticated) {
            if (session.accountId) {
                redirect controller: 'simulator'
                return
            } else {
                synchronized (request.session) {
                    // Check account
                    Account account = Account.findByFacebookId(facebookContext.user.id)
                    if (!account) {
                        // Check facebook token
                        def facebookToken = facebookContext.user.token
                        if (!facebookToken) {
                            redirect controller: 'home', params: [error: 'invalid-token']
                            return
                        }
                        // Account does not exist, automatically sign up
                        account = accountService.signup(facebookContext.user.id, facebookToken)
                    }
                    if (!account.id) {
                        // Something went wrong during signup
                        redirect controller: 'home', params: [error: 'signup-error']
                        return
                    } else {
                        // Account exists, sign in
                        accountService.signin(account)
                        session.accountId = account.id
                        redirect controller: 'simulator'
                        return
                    }
                }
            }

        }
        redirect controller: 'home'
    }

    def logout() {
        session.accountId = null
        //facebookContext.user.invalidate()
        redirect controller: 'home'
    }

}