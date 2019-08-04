# `spring-integration-examples`

TODO

## Project Architecture

![project-diagram](images/project-diagram.png)

## Microservices

### spring-integration-shell

TODO

### calculator-api

TODO

## Running microservices

### calculator-api

```
./mvnw clean spring-boot:run --projects calculator-api
```

### spring-integration-shell

```
./mvnw clean package --projects spring-integration-shell -DskipTests

./spring-integration-shell/target/spring-integration-shell-1.0.0.jar
```

