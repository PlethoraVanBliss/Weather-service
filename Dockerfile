FROM java:8u111-jre-alpine

ENV USER_NAME developer
ENV APP_HOME /home/$USER_NAME/app
ENV APP_NAME weather-forecast-service

RUN adduser -D -u 1000 $USER_NAME
RUN mkdir -p $APP_HOME

COPY target/weatherforecast-0.0.1-SNAPSHOT.jar $APP_HOME/$APP_NAME.jar
RUN chown $USER_NAME $APP_HOME/$APP_NAME.jar

USER $USER_NAME
WORKDIR $APP_HOME

ENTRYPOINT  java -jar -Dspring.server.port.config.uri:9000  $APP_NAME.jar