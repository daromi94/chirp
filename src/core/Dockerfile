FROM amazoncorretto:21-alpine

ARG JAR_PATH=app/build/libs/*.jar

COPY ${JAR_PATH} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]