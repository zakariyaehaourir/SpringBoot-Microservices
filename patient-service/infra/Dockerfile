# Build stage
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine as runtime
LABEL authors="Zakariyae.HAOURIR"
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring && chown -R spring:spring /app
USER spring:spring

COPY --from=build --chown=spring:spring /app/target/*.jar patient-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "patient-service.jar"]


