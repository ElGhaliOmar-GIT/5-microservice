FROM openjdk:8

# Set the working directory within the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/reclamationoffre.jar reclamationoffre.jar

# Copy the "resources" directory into the container
COPY src/main/resources/ resources/

# Expose the port your application is listening on (if necessary)
EXPOSE 8801

# Define the entry point
ENTRYPOINT ["java", "-jar", "reclamationoffre.jar"]