FROM amazoncorretto:17-alpine3.16
MAINTAINER c.jaquiery
RUN apk --no-cache add curl
COPY target/my-app*.jar my-app.jar
ENV DB_USER=null
ENV DB_INSTANCE=null
ENV DB_PASS=null
ENV PROFILE=default
ENV SPRING_LOG_LEVEL=INFO
ENV APP_PORT=8081
EXPOSE ${APP_PORT}/tcp
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/my-app.jar"]