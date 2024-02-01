FROM eclipse-temurin:latest
COPY /Auditor/target/Auditor-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 2205
ENTRYPOINT ["java", "-jar", "/app/app.jar"]