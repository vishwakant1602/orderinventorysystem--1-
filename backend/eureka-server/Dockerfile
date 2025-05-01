FROM maven:3.8.4-openjdk-11-slim AS builder

WORKDIR /app
COPY ./pom.xml /app/
COPY ./eureka-server/pom.xml /app/eureka-server/
RUN mvn -B dependency:go-offline -f /app/pom.xml

COPY ./eureka-server/src /app/eureka-server/src
RUN mvn -B package -f /app/pom.xml -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/eureka-server/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
