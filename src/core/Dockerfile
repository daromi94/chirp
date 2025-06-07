FROM amazoncorretto:21-alpine

ARG JAR_PATH=app/build/libs/*.jar

WORKDIR /app

COPY $JAR_PATH app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
