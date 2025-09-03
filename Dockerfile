FROM gradle:8.8-jdk17 AS build
WORKDIR /build

COPY build.gradle settings.gradle gradlew ./
COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /trainingtask
COPY build/libs/trainingtask-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

LABEL authors="makshilo"
