FROM amazoncorretto:11-alpine-jdk
MAINTAINER Agustin-Clemente
COPY target/mini-control-empleados-0.0.1-SNAPSHOT.jar gestion-empleados.jar
ENTRYPOINT ["java","-jar","gestion-empleados.jar"]
