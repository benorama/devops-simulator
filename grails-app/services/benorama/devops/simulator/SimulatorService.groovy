package benorama.devops.simulator

import grails.plugin.segmentio.SegmentioService
import org.springframework.context.MessageSource

class SimulatorService {

    static Random random = new Random()
    static IMAGES_URL = 'https://s3-eu-west-1.amazonaws.com/devops-simulator'

    MessageSource messageSource
    SegmentioService segmentioService

    Map deployApp(Account account, Pipeline pipeline) {
        boolean success = false
        switch (pipeline) {
            case Pipeline.AUTO:
                success = true
                break
            case Pipeline.SEMI:
                // Randomly get a success or fail image
                success = random.nextInt(2) ? true : false
                break
        }
        Map deployment = [:]
        int randomId = random.nextInt(6)
        if (success) {
            // Randomly get a success image
            deployment['image'] = "${IMAGES_URL}/deployment/success-${randomId}.gif"
            deployment['text'] = messageSource.getMessage("deployment.success.${randomId}", [] as Object[], Locale.US)
            deployment['alert'] = 'success'
        } else {
            // Randomly get a fail image
            deployment['image'] = "${IMAGES_URL}/deployment/fail-${randomId}.gif"
            deployment['text'] = messageSource.getMessage("deployment.fail.${randomId}", [] as Object[], Locale.US)
            deployment['alert'] = 'danger'
        }
        // Track event
        segmentioService.track(account.trackerId, 'Deployed an app')
        log.debug 'Deployed an app'
        // Return deployment info
        deployment
    }

    Map sendMessage(Account account, Pipeline pipeline) {
        Map reply = [:]
        int randomId = random.nextInt(6)
        switch (pipeline) {
            case Pipeline.AUTO:
                // Randomly get a yes image
                reply['image'] = "${IMAGES_URL}/reply/yes-${randomId}.gif"
                reply['text'] = messageSource.getMessage("reply.yes.${randomId}", [] as Object[], Locale.US)
                reply['alert'] = 'success'
                break
            case Pipeline.SEMI:
                reply['image'] = "${IMAGES_URL}/reply/confused-${randomId}.gif"
                reply['text'] = messageSource.getMessage("reply.confused.${randomId}", [] as Object[], Locale.US)
                reply['alert'] = 'warning'
                break
            case Pipeline.MANUAL:
                // Randomly get a no image
                reply['image'] = "${IMAGES_URL}/reply/no-${randomId}.gif"
                reply['text'] = messageSource.getMessage("reply.no.${randomId}", [] as Object[], Locale.US)
                reply['alert'] = 'danger'
                break
        }
        // Track event
        segmentioService.track(account.trackerId, 'Sent a message')
        log.debug 'Sent a message'
        // Return reply
        reply
    }
}
