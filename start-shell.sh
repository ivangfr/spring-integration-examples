#!/usr/bin/env bash

echo
echo "Starting spring-integration-shell..."

docker run -it --rm --name spring-integration-shell \
  -e CALCULATOR_API_HOST=calculator-api -e FILE_SERVICE_HOST=file-service \
  -e APPLICATION_OUTBOUND_PATH=/app/shared/files \
  -v ${PWD}/shared:/app/shared \
  --network spring-integration-examples_default \
  ivanfranchin/spring-integration-shell:1.0.0
