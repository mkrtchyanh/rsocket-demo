FROM openjdk:11-jre
ARG JAR_FILE
ADD target/${JAR_FILE} /notes-api-web.jar
ENTRYPOINT ["java", "-jar", "/notes-api-web.jar"]