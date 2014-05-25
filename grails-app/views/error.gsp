<!DOCTYPE html>
<html lang="en">
	<head>
		<title>
            <g:if env="development">
                Grails Runtime Exception
            </g:if>
            <g:else>
                Error
            </g:else>
        </title>
		<g:if env="development">
            <asset:stylesheet href="error.css"/>
        </g:if>
	</head>
	<body>
        <div class="container">
            <g:if env="development">
                <g:renderException exception="${exception}" />
            </g:if>
            <g:else>
                <ul class="errors">
                    <li>An error has occurred</li>
                </ul>
            </g:else>
        </div>
	</body>
</html>
