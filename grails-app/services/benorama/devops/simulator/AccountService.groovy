package benorama.devops.simulator

import grails.plugin.segmentio.SegmentioService

class AccountService {

    FacebookUserService facebookUserService
    SegmentioService segmentioService

    void signin(Account account) {
        // Track event
        segmentioService.track(account.trackerId, 'Signed in')
        log.debug 'Signed in'
    }

    Account signup(long facebookId, String facebookToken) {
        Account account = new Account(facebookId: facebookId)
        boolean success = facebookUserService.synchronize(facebookToken, account)
        if (success) {
            account.save(flush: true)

            if (account.id) {
                // Identify user
                segmentioService.identify(account.trackerId, [
                        firstName: account.firstName,
                        lastName: account.lastName,
                        name: account.fullName
                ])
                // Track event
                segmentioService.track(account.trackerId, 'Signed up')
                log.debug 'Signed up'
            }
        }
        account
    }
}
