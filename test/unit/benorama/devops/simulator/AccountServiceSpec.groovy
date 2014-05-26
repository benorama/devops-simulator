package benorama.devops.simulator

import grails.plugin.segmentio.SegmentioService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.codecs.MD5Codec
import spock.lang.Specification

@Mock(Account)
@TestFor(AccountService)
class AccountServiceSpec extends Specification {

    static long FACEBOOK_ID = 123456789

    def setup() {
        // Mock collaborators
        service.facebookUserService = Mock(FacebookUserService)
        service.segmentioService = Mock(SegmentioService)
        // Mock codec
        mockCodec(MD5Codec)
    }

    void "Sign in"() {
        given:
        Account account = new Account(
                facebookId: FACEBOOK_ID,
                firstName: 'John',
                lastName: 'Cooper',
                name: 'John Cooper'
        ).save()

        when:
        service.signin(account)

        then:
        1 * service.segmentioService.track(account.trackerId, 'Signed in')
    }

    void "Sign up"() {
        given:
        String token = 'token'
        Long facebookId = 123456789

        when:
        service.signup(facebookId, token)

        then:
        1 * service.facebookUserService.synchronize(token, _) >> true
        1 * service.segmentioService.identify(_, _)
        1 * service.segmentioService.track(_, 'Signed up')
    }

}
