<facebook:initJS appId="${facebookContext.app.id}"/>

<g:render template="/navbar"/>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>
            <i class="fa fa-cloud-upload"></i>
            The ultimate build pipeline simulator
        </h1>
        <hr/>
        <ol>
            <li>
                Choose your DevOps level
            </li>
            <li>
                Release your app and notify our virtual Ops team, powered by the most sophisticated IA bot
            </li>
            <li>
                Simulate your app deployment on our world-class virtual cloud data center
            </li>
        </ol>
        <hr/>
        <p>
            Are you ready? Please authenticate first!
        </p>
        <p>
            <facebook:loginLink
                    elementClass="btn btn-primary btn-lg"
                    appPermissions="${facebookContext.app.permissions}"
                    returnUrl="${createLink(controller: 'account', action: 'signin')}">
                <i class="fa fa-facebook-square"></i>
                Connect with Facebook
            </facebook:loginLink>
        </p>
    </div>
</div>

<div class="container">
    <blockquote>
        <p>
            <i class="fa fa-quote-left pull-left"></i>
            <g:message code="devops.borat.tweet.${tweetId}"/>
            <i class="fa fa-quote-right"></i>
        </p>
        <small>
            DevOps Borat
        </small>
    </blockquote>
    <g:render template="/footer"/>
</div>