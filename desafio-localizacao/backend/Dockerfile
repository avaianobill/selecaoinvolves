FROM openjdk:8u201-jre-alpine
RUN mkdir /home/service
COPY target/*.jar /home/service/desafio-localizacao-1.0.0.jar
WORKDIR /home/service
ENTRYPOINT exec java -jar desafio-localizacao-1.0.0.jar
