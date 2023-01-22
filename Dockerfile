FROM amazoncorretto:11-alpine-jdk
MAINTAINER Agustin-Clemente
COPY target/mini-control-empleados-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/target/mini-control-empleados-0.0.1-SNAPSHOT.jar"]
