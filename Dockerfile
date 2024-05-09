FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21.0.1_12-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/authentication-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4040

ENTRYPOINT ["java" , "-jar" , "app.jar"]