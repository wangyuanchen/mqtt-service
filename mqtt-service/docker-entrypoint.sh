#!/bin/bash

set -ex;

exec /usr/bin/java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap ${JAVA_OPTS} \
     	-Djava.security.egd=file:/dev/./urandom \
     	-javaagent:/pinpoint-agent/pinpoint-bootstrap-1.8.4.jar \
        -Dpinpoint.agentId=mqtt-service \
        -Dpinpoint.applicationName=mqtt-service \
     	-jar /app/mqtt-service/app.jar ${RUN_ARGS} "$@"