FROM amazoncorretto:17-alpine3.16
MAINTAINER c.jaquiery
COPY target/my-app*.jar my-app.jar
ENV DB_USER=null
ENV DB_INSTANCE=null
ENV DB_PASS=null
#ENV PROFILE=default
ENV PROFILE=dev
ENV APP_PORT=8080
EXPOSE ${APP_PORT}/tcp
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/my-app.jar"]