FROM adoptopenjdk/openjdk11:jre-11.0.18_10
WORKDIR /app
COPY target/java-shutdown-1.0.jar /app
ENTRYPOINT ["java", "-cp", "/app/*", "org.example.Main"]