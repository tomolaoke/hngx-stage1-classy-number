# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Copy the JAR file into the container
COPY target/classify-number-0.0.1-SNAPSHOT.jar classify-number-0.0.1-SNAPSHOT.jar

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "classify-number-0.0.1-SNAPSHOT.jar"]