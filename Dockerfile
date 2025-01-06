FROM openjdk:17.0.1-jdk-slim
COPY build/libs/metricService-1.0-SNAPSHOT.jar /app/metricService.jar
EXPOSE 8002
CMD ["java", "-jar", "/app/metricService.jar"]