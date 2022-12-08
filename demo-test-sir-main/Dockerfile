FROM openjdk:17-jdk-alpine
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo-test.jar
RUN mvn clean install
ENTRYPOINT ["java","-jar","/demo-test.jar"]