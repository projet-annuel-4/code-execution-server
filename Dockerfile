FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn clean
COPY src ./src
RUN ["mvn", "package", "-Dmaven.test.skip=true"]

FROM openjdk:17.0.2-slim-buster

RUN apt update
RUN apt install -y nodejs
RUN apt install -y python3
RUN apt install -y build-essential
RUN apt install -y firejail

COPY . /app/
RUN mkdir /exec
RUN mkdir /testExec


COPY --from=build /home/app/target/*.jar /usr/local/lib/build.jar
EXPOSE 3002

ENTRYPOINT ["java","-jar","/usr/local/lib/build.jar"]