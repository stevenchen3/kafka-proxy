#!/bin/bash

source ${KAFKA_PROXY_HOME}/bin/config-utils.sh

function broker_url() {
  local INDEX=$1
  echo "kafka-${INDEX}.kafka-headless-svc:9092"
}

function configure() {
  local CONFIG=$1

  local SERVER_LIST=""
  local INDEX=0
  local COUNT=3
  while [ ${INDEX} -lt ${COUNT} ]; do
    SERVER_LIST="${SERVER_LIST}$(broker_url ${INDEX})"
    if [ ${INDEX} -ne $((COUNT - 1)) ]; then
      SERVER_LIST="${SERVER_LIST},"
    fi
    let INDEX=$INDEX+1
  done

  generate_config ${CONFIG} _TRANSACTIONAL_ID_     ${TRANSACTIONAL_ID:-"deault-txn-id"}
  generate_config ${CONFIG} _BATCH_SIZE_           ${BATCH_SIZE:-"131072"} # 128k
  generate_config ${CONFIG} _BOOTSTRAP_SERVERS_    ${BOOTSTRAP_SERVERS:-"${SERVER_LIST}"}
  generate_config ${CONFIG} _COMPRESSION_TYPE_     ${COMPRESSION_TYPE:-"none"}
  generate_config ${CONFIG} _REQUEST_TIMEOUT_MS_   ${REQUEST_TIMEOUT_MS:-"60000"}
  generate_config ${CONFIG} _BUFFER_MEMORY_BYTES_  ${BUFFER_MEMORY_BYTES:-"33554432"} # 32m
  generate_config ${CONFIG} _ENABLE_BASE64_DECODE_ ${ENABLE_BASE64_DECODE:-"false"}
}

configure ${KAFKA_PROXY_HOME}/config/producer.properties
JVM_OPTS="-Xms${HEAP_SIZE_MIN:-1g} -Xmx${HEAP_SIZE_MAX:-2g}"

case "$1" in
rest)
  exec java ${JVM_OPTS} -cp ${KAFKA_PROXY_HOME}/lib/* io.alphash.kafka.proxy.rest.SimpleRestServer
  ;;
grpc)
  exec java ${JVM_OPTS} -cp ${KAFKA_PROXY_HOME}/lib/* io.alphash.kafka.proxy.grpc.SimpleGrpcServer
  ;;
*)
  echo "Unknown argument $1, expects 'rest' or 'grpc'"
  exit 1
esac
