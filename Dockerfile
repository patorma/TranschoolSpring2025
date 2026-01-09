FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/Transchool2025-0.0.1.jar
COPY ${JAR_FILE} Transchool2025.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Transchool2025.jar"]