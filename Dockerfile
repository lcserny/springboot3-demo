FROM azul/zulu-openjdk-alpine:21
VOLUME /tmp
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ARG JAVA_OPTS="-Xmx32m -Xss256k"
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]