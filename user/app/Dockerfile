FROM openjdk:11-jre
ARG JAR_FILE
ADD target/${JAR_FILE} /user-api-web.jar
ENTRYPOINT ["java", "-jar", "/user-api-web.jar"]