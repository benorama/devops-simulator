package benorama.devops.simulator

import com.restfb.exception.FacebookException
import grails.plugin.facebooksdk.FacebookGraphClient

class FacebookUserService {

    Map fetch(String token = '', long facebookId) {
        assert facebookId, 'User facebookId is required'

        Map facebookUser = [:]
        FacebookGraphClient facebookGraphClient = new FacebookGraphClient(token)
        try {
            // Call facebook Graph API
            def result = facebookGraphClient.fetchObject(facebookId.toString())
            // Facebook return false if object is not found
            if (result) facebookUser = result as Map
        } catch (FacebookException exception) {
            FacebookUserService.log.warn(exception)
        }
        return facebookUser
    }

    boolean synchronize(String userToken, Account account) {
        assert userToken, 'User token is required'
        assert account, 'Account is required'
        assert account.facebookId, 'Account facebookId is required'

        boolean success = false

        Map facebookUser = fetch(userToken, account.facebookId)
        if (!facebookUser) {
            // First Facebook Graph request failed, try a second time
            facebookUser = fetch(userToken, account.facebookId)
        }
        if (facebookUser) {
            account.firstName = facebookUser['first_name'] ?: ''
            account.lastName = facebookUser['last_name'] ?: ''
            account.name = "${account.firstName} ${account.lastName}"

            success = true
        }
        return success
    }

}