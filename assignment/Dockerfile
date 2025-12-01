FROM maven:3.9-eclipse-temurin-22 as build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests


FROM eclipse-temurin:22-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080


LABEL authors="ajayas"

ENTRYPOINT ["java", "-jar","app.jar"]
