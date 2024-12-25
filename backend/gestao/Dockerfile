FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gestao-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]
