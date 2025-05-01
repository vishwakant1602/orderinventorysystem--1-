# Build stage
FROM maven:3.8.6 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -pl api-gateway -am

# Run stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/api-gateway/target/*.jar app.jar

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8080/actuator/health || exit 1

# Add metadata
LABEL maintainer="OrderInventorySystem Team"
LABEL version="1.0"
LABEL description="API Gateway for OrderInventorySystem"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
