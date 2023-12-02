# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-oracle

# Set the working directory to /app
WORKDIR /app

COPY target/qp-assessment-0.0.1-SNAPSHOT.jar /app/qp-assessment-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "qp-assessment-0.0.1-SNAPSHOT.jar"]
