# Build stage
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /config-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:11
COPY --from=build /target/config-server-1.0.0.jar /config-server-1.0.0.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "/config-server-1.0.0.jar"]