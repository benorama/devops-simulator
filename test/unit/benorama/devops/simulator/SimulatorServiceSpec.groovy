package benorama.devops.simulator

import grails.plugin.segmentio.SegmentioService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.codecs.MD5Codec
import org.springframework.context.MessageSource
import spock.lang.Specification

@Mock(Account)
@TestFor(SimulatorService)
class SimulatorServiceSpec extends Specification {

    static long FACEBOOK_ID = 123456789

    Account account

    def setup() {
        // Mock collaborators
        service.messageSource = Mock(MessageSource)
        service.segmentioService = Mock(SegmentioService)
        // Mock codec
        mockCodec(MD5Codec)
        // Build account
        account = new Account(
                facebookId: FACEBOOK_ID,
                firstName: 'John',
                lastName: 'Cooper',
                name: 'John Cooper'
        ).save()
    }

    void "Deploy app"() {
        given:
        Pipeline pipeline = Pipeline.MANUAL

        when:
        Map deployment = service.deployApp(account, pipeline)

        then:
        deployment
        1 * service.messageSource.getMessage(_, _, Locale.US) >> 'Some text'
        1 * service.segmentioService.track(account.trackerId, 'Deployed an app')
    }

    void "Send message"() {
        given:
        Pipeline pipeline = Pipeline.MANUAL

        when:
        Map reply = service.sendMessage(account, pipeline)

        then:
        reply
        1 * service.messageSource.getMessage(_, _, Locale.US) >> 'Some text'
        1 * service.segmentioService.track(account.trackerId, 'Sent a message')
    }
}
