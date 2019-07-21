FROM openjdk:8-jre-alpine

RUN apk add --no-cache tini

ENTRYPOINT ["/sbin/tini", "--"]

ARG JAR_FILE
CMD ["java", "-jar", "/notes.jar"]

ADD target/${JAR_FILE} /notes.jar
