FROM azul/zulu-openjdk-alpine:21
VOLUME /tmp
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Xmx:32m -Xss:256k -XX:+UseZGC -XX:+ZGenerational -jar /app.jar"]