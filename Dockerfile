FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:21
COPY --from=build /app/target/DS_2023_Tulbure_Claudiu_Marcel_2_ChatManagement-0.0.1-SNAPSHOT.jar /chat-management.jar
ENTRYPOINT ["java", "-jar", "/chat-management.jar"]
