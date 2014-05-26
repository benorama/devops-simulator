package benorama.devops.simulator

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MonitorController)
class MonitorControllerSpec extends Specification {

    void "test something"() {
        when:
        controller.index()

        then:
        true
    }

}
