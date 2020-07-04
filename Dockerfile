FROM openjdk:8u252-jdk-slim
LABEL maintainer="eralmas7@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/FriendService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} FriendService.jar 
ENTRYPOINT ["java","-jar","/FriendService.jar"]
