# Build stage
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /eureka-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:11
COPY --from=build /target/eureka-server-1.0.0.jar /eureka-server-1.0.0.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka-server-1.0.0.jar"]