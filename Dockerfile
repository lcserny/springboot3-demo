FROM azul/zulu-openjdk-alpine:21
VOLUME /tmp
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xms32M", "-Xmx64M", "-jar","/app.jar"]
