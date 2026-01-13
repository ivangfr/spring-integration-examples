#!/usr/bin/env bash

source scripts/my-functions.sh

echo
echo "Starting calculator-api..."

docker run -d --rm --name calculator-api -p 9080:9080 \
  --network spring-integration-examples_default \
  ivanfranchin/calculator-api:1.0.0

wait_for_container_log "calculator-api" "Started"

echo
echo "Starting file-service..."

docker run -d --rm --name file-service -p 9081:9081 \
  -u $(id -u):$(id -g) \
  -e MONGODB_HOST=mongodb \
  -e SHARED_FILES_PATH=/app/shared/files \
  -v ${PWD}/shared:/app/shared \
  --network spring-integration-examples_default \
  ivanfranchin/file-service:1.0.0

wait_for_container_log "file-service" "Started"
