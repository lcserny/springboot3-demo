FROM azul/zulu-openjdk-alpine:21
VOLUME /tmp
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Xmx:128m -Xms:16m -Xss:512k -jar /app.jar"]