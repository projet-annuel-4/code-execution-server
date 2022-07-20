FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn clean
COPY src ./src
RUN ["mvn", "package", "-Dmaven.test.skip=true"]

FROM openjdk:17-alpine

RUN apk add --update --no-cache\
    nodejs \
    build-base \
    python3 \
    py3-pip

RUN python3 -m ensurepip

COPY . /app/
RUN mkdir /exec
RUN mkdir /testExec


COPY --from=build /home/app/target/*.jar /usr/local/lib/build.jar
EXPOSE 3002

ENTRYPOINT ["java","-jar","/usr/local/lib/build.jar"]


# docker build -t remote-exec-serv .  */
# docker run -d -p 3002:3002 --network host --name consumer remote-exec-serv */