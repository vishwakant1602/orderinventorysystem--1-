# Build stage
FROM maven:3.8.6 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -pl order-service -am

# Run stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/order-service/target/*.jar app.jar

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

# Add metadata
LABEL maintainer="OrderInventorySystem Team"
LABEL version="1.0"
LABEL description="Order Service for OrderInventorySystem"

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
