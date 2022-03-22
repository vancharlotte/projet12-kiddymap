# Build stage
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /microservice-profil
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:11
COPY --from=build /target/microservice-profil-1.0.0.jar /microservice-profil-1.0.0.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "/microservice-profil-1.0.0.jar"]