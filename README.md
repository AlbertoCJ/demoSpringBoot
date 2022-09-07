DEMO APP BACKEND SPRING-BOOT
==========================

## Project tech list

Listado de tecnologías/librerias usadas en el proyecto:

* Spring-boot
* Spring-data
* Base de datos H2
* Lombok
* Mapstruct
* Hibernate-validator


## Requirements
Run as any other Spring Boot app:
For building and running the application you need:

* [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven 3.8.1](https://maven.apache.org/download.cgi)

## Build

* From command line:
```script
mvn clean install
```

This will use the application local configuration and will run the app server on the url:
```script
http://localhost:8080/
```
* H2-ui data conect
```script
http://localhost:8080/h2-ui   <-- BD Manager

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:
```
