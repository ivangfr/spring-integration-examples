#!/usr/bin/env bash

#if [ "$1" = "native" ];
#then
#  ./mvnw clean spring-boot:build-image --projects calculator-api
#  ./mvnw clean spring-boot:build-image --projects file-service
#  ./mvnw clean spring-boot:build-image --projects spring-integration-shell
#else
  ./mvnw clean compile jib:dockerBuild --projects calculator-api
  ./mvnw clean compile jib:dockerBuild --projects file-service
  ./mvnw clean compile jib:dockerBuild --projects spring-integration-shell
#fi