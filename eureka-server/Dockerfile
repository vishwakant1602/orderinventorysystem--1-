# Build stage
FROM maven:3.9.6-openjdk-17 AS builder
WORKDIR /app
COPY ./eureka-server/pom.xml .
COPY ./eureka-server/src ./src
COPY ./pom.xml ../
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:18-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
