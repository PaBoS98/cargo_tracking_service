FROM openjdk:17-jdk-slim
FROM maven:3.9.5

WORKDIR /app

RUN apt-get update \
    && apt-get install -y git \
    && git clone https://github.com/PaBoS98/cargo_tracking_service.git

WORKDIR /app/cargo_tracking_service

RUN mvn clean install package

COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]