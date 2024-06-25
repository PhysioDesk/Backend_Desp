FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/platform-1.0.0.jar
COPY ${JAR_FILE} app_physiodesk.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app_physiodesk.jar"]