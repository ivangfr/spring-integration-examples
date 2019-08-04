# `spring-integration-examples`

The goal of this project is to learn [`String Integration Framework`](https://docs.spring.io/spring-integration/reference/html/index.html)
For it, we will implement some [`Spring Boot`](https://spring.io/projects/spring-boot) applications and try to use the
well known [`Enterprise Integration Patterns`](https://www.enterpriseintegrationpatterns.com/patterns/messaging/toc.html). 

## Project Architecture

![project-diagram](images/project-diagram.png)

## Microservices

### calculator-api

Spring Boot Java Web application that exposes one endpoint `api/calculate` so that users can submit which operation
(addition, subtraction, division or multiplication) they want to perform over two decimal numbers `a` and `b`.

### spring-integration-shell

Spring Boot Shell Java application that uses `calculator-api` to compute the four basic Math operations: addition,
subtraction, division and multiplication. All the communication with `calculator-api` is over `HTTP`. Besides, it also
has the command `greet`, so that you can display a greeting message on the screen.

## Running Microservices

### calculator-api

Open a terminal and inside `spring-integration-examples` run
```
./mvnw clean spring-boot:run --projects calculator-api
```

### spring-integration-shell

Open another terminal and inside `spring-integration-examples` root folder, run the command below to package the `jar`
```
./mvnw clean package --projects spring-integration-shell -DskipTests
```

Then, still inside `spring-integration-examples`, run the following command to start `spring-integration-shell`
```
./spring-integration-shell/target/spring-integration-shell-1.0.0.jar
```

