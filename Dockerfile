FROM openjdk:17-oracle

WORKDIR /app

COPY target/your-cli-tool-1.0.0.jar /app/

ENTRYPOINT ["java", "-jar", "your-cli-tool-1.0.0.jar"]