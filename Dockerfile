# syntax=docker/dockerfile:1
FROM gradle:latest AS build
COPY . /home/gradle/app
WORKDIR /home/gradle/app

RUN gradle build --no-daemon

FROM openjdk:8

EXPOSE 9090

COPY --from=build /home/gradle/app/build/libs/NFTPublisher-0.0.1-SNAPSHOT.jar nft-publisher.jar
CMD ["java", "-jar", "nft-publisher.jar"]
