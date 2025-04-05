#!/usr/bin/env bash

DOCKER_IMAGE_PREFIX="ivanfranchin"
APP_VERSION="1.0.0"

CALCULATOR_API_APP_NAME="calculator-api"
FILE_SERVICE_APP_NAME="file-service"
SPRING_INTEGRATION_SHELL_APP_NAME="spring-integration-shell"

CALCULATOR_API_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${CALCULATOR_API_APP_NAME}:${APP_VERSION}"
FILE_SERVICE_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${FILE_SERVICE_APP_NAME}:${APP_VERSION}"
SPRING_INTEGRATION_SHELL_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${SPRING_INTEGRATION_SHELL_APP_NAME}:${APP_VERSION}"

SKIP_TESTS="true"

./mvnw clean spring-boot:build-image \
--projects "$CALCULATOR_API_APP_NAME" \
-DskipTests="$SKIP_TESTS" \
-Dspring-boot.build-image.imageName="$CALCULATOR_API_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
--projects "$FILE_SERVICE_APP_NAME" \
-DskipTests="$SKIP_TESTS" \
-Dspring-boot.build-image.imageName="$FILE_SERVICE_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
--projects "$SPRING_INTEGRATION_SHELL_APP_NAME" \
-DskipTests="$SKIP_TESTS" \
-Dspring-boot.build-image.imageName="$SPRING_INTEGRATION_SHELL_DOCKER_IMAGE_NAME"
