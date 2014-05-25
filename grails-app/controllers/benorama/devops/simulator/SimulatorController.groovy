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
        Pipeline pipeline = session.pipeline
        Account account = Account.get(session.accountId)
        [
                account: account,
                deployment: params.deploy ? simulatorService.deployApp(account, pipeline) : [:],
                reply: params.message ? simulatorService.sendMessage(account, pipeline) : [:],
                pipeline: pipeline
        ]
    }

}