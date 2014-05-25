#!/bin/sh
# New Relic (Application monitoring)
mkdir /var/lib/newrelic
mv ./.ebextensions/newrelic/newrelic.jar /var/lib/newrelic/
bash ./.ebextensions/newrelic/newrelic.yml.sh > /var/lib/newrelic/newrelic.yml

# New Relic Agent (Server monitoring)
rpm -Uvh https://yum.newrelic.com/pub/newrelic/el5/x86_64/newrelic-repo-5-3.noarch.rpm
yum -y install newrelic-sysmond
/usr/sbin/nrsysmond-config --set license_key=$NEWRELIC_LICENSE
/etc/init.d/newrelic-sysmond start

# New Relic deployment event
export APP_VERSION=`cat ./WEB-INF/classes/application.properties | grep app.version | cut -d= -f2`
java -jar /var/lib/newrelic/newrelic.jar deployment --revision=$APP_VERSION