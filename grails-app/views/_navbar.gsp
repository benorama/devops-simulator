<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">DevOps Simulator</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <g:if test="${account}">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Choose your build pipeline <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <g:link params="[pipeline: 'manual']">
                                    <strong>Manual</strong> <g:if test="${pipeline == 'manual'}"><i class="fa fa-check"></i></g:if>
                                </g:link>
                            </li>
                            <li>
                                <g:link params="[pipeline: 'semi']">
                                    <strong>Semi-automated</strong> <g:if test="${pipeline == 'semi'}"><i class="fa fa-check"></i></g:if>
                                </g:link>
                            </li>
                            <li>
                                <g:link params="[pipeline: 'auto']">
                                    <strong>Automated</strong> <small>(100% DevOps)</small> <g:if test="${pipeline == 'auto'}"><i class="fa fa-check"></i></g:if>
                                </g:link>
                            </li>
                        </ul>
                    </li>
                </ul>
            </g:if>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="https://github.com/benorama/devops-simulator" target="_blank">
                        <i class="fa fa-github-square"></i>
                        See on GitHub
                    </a>
                </li>
                <g:if test="${account}">
                    <li>
                        <a href="http://facebook.com/${account.facebookId}" target="_blank">
                            <img class="img-rounded pull-left" src="http://graph.facebook.com/${account.facebookId}/picture" alt="" height="22" width="22">
                            &nbsp;
                            ${account.fullName}
                        </a>
                    </li>
                    <li>
                        <g:link controller="account" action="logout">
                            <i class="fa fa-power-off"></i>
                            Logout
                        </g:link>
                    </li>
                </g:if>
                <g:else>
                    <li>
                        <facebook:loginLink
                                appPermissions="${facebookContext.app.permissions}"
                                returnUrl="${createLink(controller: 'account', action: 'signin')}">
                            <i class="fa fa-facebook-square"></i>
                            Connect with Facebook
                        </facebook:loginLink>
                    </li>
                </g:else>
            </ul>

        </div>
        <!--/.navbar-collapse -->
    </div>
</div>