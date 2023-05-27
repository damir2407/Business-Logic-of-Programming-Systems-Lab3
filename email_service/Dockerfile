FROM openjdk:17-slim

ARG JAR_FILE=target/*.jar

COPY $JAR_FILE /email_service.jar

ENTRYPOINT exec java -jar /email_service.jar
