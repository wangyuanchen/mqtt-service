FROM 172.16.1.203:5000/socialinvoice/base:latest

WORKDIR /app/mqtt-service

ADD . /app/mqtt-service

RUN mvn clean && \
    mvn package spring-boot:repackage -Dmaven.test.skip=true && \
    rm -rf /usr/local/.m2 && \
    cp target/*.jar /app/mqtt-service/app.jar && \
    chmod +x /app/mqtt-service/docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/app/mqtt-service/docker-entrypoint.sh"]
