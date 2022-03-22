# Build stage
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /api-gateway
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:11
COPY --from=build /target/api-gateway-1.0.0.jar /api-gateway-1.0.0.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "/api-gateway-1.0.0.jar"]