FROM openjdk:8
EXPOSE 8080
ADD target/aws-rest-server.jar aws-rest-server.jar
ENTRYPOINT ["java","-jar","aws-rest-server.jar"]