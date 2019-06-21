FROM openjdk:8-jre

EXPOSE 8080
EXPOSE 1100

WORKDIR /opt/app
COPY target/benchmark.jar .

ENTRYPOINT ["java", "-jar", "/opt/app/benchmark.jar"]
