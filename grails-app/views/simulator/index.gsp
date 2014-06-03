<%@ page import="benorama.devops.simulator.Pipeline" %>
<g:render template="/navbar" model="[account: account, pipeline: pipeline]"/>

<div class="container">
    <g:if test="${pipeline == Pipeline.AUTO}">
        <h1>Automated Build Pipeline <small>100% DevOps</small></h1>
        Agile development, Continuous Integration & Continuous Delivery, Automated Config, Provisioning & Deployment
    </g:if>
    <g:elseif test="${pipeline == Pipeline.SEMI}">
        <h1>Semi-automated Build Pipeline</h1>
        Agile development, Continuous Integration & Continuous Delivery
    </g:elseif>
    <g:else>
        <h1>Manual Build Pipeline</h1>
        Agile development, <span class="text-warning">Manual testing</span>
    </g:else>
    <hr/>
    <div class="row">
        <div class="col-md-6">
            <g:if test="${reply}">
                <div class="alert alert-${reply['alert']}">
                    <h2>
                        ${reply['text']}
                    </h2>
                    <image src="${reply['image']}" style="width: 100%"/>
                    <hr/>
                    <form id="messageForm" method="post" role="form">
                        <input type="hidden" name="message" value="${params.message}"/>
                        <button type="submit" class="btn btn-default">
                            <i class="fa fa-send"></i>
                            Re-Send
                        </button>
                    </form>
                </div>
            </g:if>
            <g:else>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        VOTB (Virtual Ops Team Bot)
                    </div>
                    <div class="panel-body">
                        <ul class="media-list">
                            <li class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-rounded" src="http://graph.facebook.com/${account.facebookId}/picture" alt="" height="50" width="50">
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading">
                                        ${account.fullName}
                                    </h4>
                                    <form id="deployForm" method="post" role="form">
                                        <div class="form-group">
                                            <textarea class="form-control" name="message" rows="3">Can we deploy to production?</textarea>
                                        </div>
                                        <button type="submit" class="btn btn-default">
                                            <i class="fa fa-send"></i>
                                            Send
                                        </button>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </g:else>
        </div>
        <div class="col-md-6">
            <g:if test="${deployment}">
                <div class="alert alert-${deployment['alert']}">
                    <h2>
                        ${deployment['text']}
                    </h2>
                    <image src="${deployment['image']}" style="width: 100%"/>
                    <hr/>
                    <form method="post" role="form">
                        <input type="hidden" name="deploy" value="true"/>
                        <button class="btn btn-primary btn-lg" type="submit">
                            <i class="fa fa-cloud-upload"></i>
                            Re-Deploy
                        </button>
                    </form>
                </div>
            </g:if>
            <g:else>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        VCDCaaS (Virtual Cloud Data Center as a Service)
                    </div>
                    <div class="panel-body bg-${deployment['alert']}">
                        <form method="post" role="form">
                            <input type="hidden" name="deploy" value="true"/>
                            <button class="btn btn-primary btn-lg" type="submit">
                                <i class="fa fa-cloud-upload"></i>
                                Deploy
                            </button>
                        </form>
                    </div>
                </div>
            </g:else>
        </div>
    </div>
    <g:render template="/footer"/>
</div>