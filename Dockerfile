FROM gradle:7.5.1-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11.0.2
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar  /app/spring-boot-application.jar
CMD ["java","-jar","/app/spring-boot-application.jar"]