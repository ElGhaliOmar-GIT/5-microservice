FROM openjdk:8
EXPOSE 8089
ADD target/dockertest.jar dockertest.jar
ENTRYPOINT ["java", "-jar", "dockertest.jar"]
