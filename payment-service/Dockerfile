# Build stage
FROM maven:3.8.6 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -pl payment-service -am

# Run stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/payment-service/target/*.jar app.jar

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8084/actuator/health || exit 1

# Add metadata
LABEL maintainer="OrderInventorySystem Team"
LABEL version="1.0"
LABEL description="Payment Service for OrderInventorySystem"

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
