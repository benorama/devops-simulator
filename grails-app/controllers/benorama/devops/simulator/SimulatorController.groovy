package benorama.devops.simulator

class SimulatorController {

    SimulatorService simulatorService

    def beforeInterceptor = {
        if (!session.pipeline) {
            session.pipeline = Pipeline.MANUAL
        }
    }

    def index() {
        if (params.pipeline && Pipeline.contains(params.pipeline)) {
            session.pipeline = params.pipeline.toUpperCase() as Pipeline
        }
        if (params.error) {
            throw new Exception("Some error... throw an exception to demo Sentry exception monitoring!")
        }
        Pipeline pipeline = session.pipeline
        Account account = Account.get(session.accountId)
        [
                account: account,
                deployment: params.deploy ? simulatorService.deployApp(account, pipeline, params.int("deploy")) : [:],
                reply: params.message ? simulatorService.sendMessage(account, pipeline, params.int("reply")) : [:],
                pipeline: pipeline
        ]
    }

}