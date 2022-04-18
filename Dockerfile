# syntax=docker/dockerfile:1
FROM gradle:7.4-jdk8
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/nft-publisher.jar

ENTRYPOINT ["java", "-jar", "/app/nft-publisher.jar"]
