FROM openjdk:8-alpine
WORKDIR /usr/share/java
COPY target/weather-prediction-1.0.jar weather-prediction-1.0.jar

ENTRYPOINT ["java", "-jar", "/usr/share/java/weather-prediction-1.0.jar"]