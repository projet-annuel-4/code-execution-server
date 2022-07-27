FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn clean
COPY src ./src
RUN mvn package -Dmaven.test.skip=true

FROM ubuntu:18.04

RUN mkdir -p /usr/share/man/man1
RUN DEBIAN_FRONTEND=noninteractive \
  apt update && apt-get install --no-install-recommends -y \
  nodejs \
  python3 \
  build-essential \
  firejail

RUN apt install -y default-jre

COPY . /app/
RUN mkdir /exec
RUN mkdir /testExec


COPY --from=build /home/app/target/*.jar /usr/local/lib/build.jar
EXPOSE 3002

ENTRYPOINT ["java","-jar","/usr/local/lib/build.jar"]