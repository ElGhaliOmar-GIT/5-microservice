FROM openjdk:8
EXPOSE 8805
ADD target/productDocker.jar productDocker.jar
ENTRYPOINT ["java", "-jar", "productDocker.jar"]