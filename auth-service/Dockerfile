# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY auth-service/pom.xml auth-service/
# Download all required dependencies into one layer
RUN mvn -B dependency:go-offline -f auth-service/pom.xml
COPY auth-service/src auth-service/src
RUN mvn -B clean package -DskipTests -f auth-service/pom.xml

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/auth-service/target/*.jar app.jar

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8083/actuator/health || exit 1

# Add metadata
LABEL maintainer="OrderInventorySystem Team"
LABEL version="1.0"
LABEL description="Auth Service for OrderInventorySystem"

# Expose port
EXPOSE 8083

# Run with proper JVM settings for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
