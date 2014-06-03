DevOps Simulator Grails Demo
============================

This is the demo app for [Gr8Conf Europe 2014](http://gr8conf.eu) presentation [Running a Lean Startup - Devops & Grails & AWS](https://speakerdeck.com/benorama/running-a-lean-startup-devops-and-grails-and-aws).

RUNNING THE APP LOCALLY

- Create a Facebook App on https://developers.facebook.com/apps
- In Facebook App settings, add a 'Web site' platform and enter your app URL (ex.: http://localhost:8080)
- In Config.groovy, copy Facebook App ID and secret key
- Run `bower install` to get static assets dependencies
- Run `grunt less` to generate CSS files
- Run `grails run-app`
