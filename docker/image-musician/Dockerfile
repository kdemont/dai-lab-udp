FROM eclipse-temurin:latest
COPY Musician/target/Musician-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 9904
ENTRYPOINT ["java", "-jar", "/app/app.jar"]