FROM openjdk:21
WORKDIR /app
COPY build/libs/Auth-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar", "Auth-0.0.1-SNAPSHOT.jar"]