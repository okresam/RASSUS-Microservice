FROM openjdk:17
EXPOSE 8761
ADD build/libs/EurekaServer-0.0.1-SNAPSHOT.jar EurekaServer.jar
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]