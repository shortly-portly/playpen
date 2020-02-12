FROM openjdk:8-alpine

COPY target/uberjar/playpen.jar /playpen/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/playpen/app.jar"]
