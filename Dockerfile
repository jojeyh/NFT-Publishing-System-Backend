# syntax=docker/dockerfile:1
FROM gradle:7.4-jdk8 AS GRADLE_BUILD_IMAGE

ENV APP_HOME = /usr/app
COPY . $APP_HOME
WORKDIR $APP_HOME

RUN ./gradlew build --no-daemon

FROM openjdk:8

EXPOSE 9090

COPY --from=GRADLE_BUILD_IMAGE $APP_HOME/build/libs/*.jar nft-publisher.jar

ENTRYPOINT ["java", "-jar", "nft-publisher.jar"]
