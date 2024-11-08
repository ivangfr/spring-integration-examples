#!/usr/bin/env bash

./mvnw clean compile jib:dockerBuild --projects calculator-api
./mvnw clean compile jib:dockerBuild --projects file-service
./mvnw clean compile jib:dockerBuild --projects spring-integration-shell