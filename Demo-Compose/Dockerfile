FROM java:8
FROM maven:alpine
WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests
EXPOSE 2021
ADD ./target/Demo-Compose-0.0.1-SNAPSHOT.jar test-deploy.jar
CMD ["java","-jar","test-deploy.jar"]