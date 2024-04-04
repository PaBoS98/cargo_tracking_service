FROM maven:3.9.5 AS build
WORKDIR /app
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/PaBoS98/cargo_tracking_service.git
WORKDIR /app/cargo_tracking_service
RUN mvn clean install

FROM openjdk:17-jdk-slim
ARG PORT=8080
ENV PORT=${PORT}
WORKDIR /app/cargo_tracking_service
COPY --from=build /app/cargo_tracking_service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]