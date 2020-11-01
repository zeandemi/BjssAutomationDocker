FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/bjss

# ADD .jar under target from host
# into this image
ADD target/BjssAutomation-1.0-SNAPSHOT.jar 			BjssAutomation-1.0-SNAPSHOT.jar
ADD target/BjssAutomation-1.0-SNAPSHOT-tests.jar 	BjssAutomation-1.0-SNAPSHOT-tests.jar
ADD target/libs							libs

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well

# ADD suite files
ADD src/TestNG.xml				TestNG.xml
# ADD search-module.xml					search-module.xml

# ADD health check script
# ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT java -cp BjssAutomation-1.0-SNAPSHOT.jar;BjssAutomation-1.0-SNAPSHOT-tests.jar;libs/* -DHUB_HOST=$HUB_HOST  org.testng.TestNG TestNG.xml