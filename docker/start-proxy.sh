#!/bin/bash

source ${PWD}/config-utils.sh

function configure() {
  local CONFIG=$1
  generate_config ${CONFIG} _TRANSACTIONAL_ID_ ${TRANSACTIONAL_ID:-"my-transactional-id"}
  generate_config ${CONFIG} _BATCH_SIZE_ ${BATCH_SIZE:-"102400"}
  generate_config ${CONFIG} _BOOTSTRAP_SERVERS_ ${BOOTSTRAP_SERVERS:-"kafka-0.kafka-headless-svc:9092,kafka-1.kafka-headless-svc:9092,kafka-2.kafka-headless-svc:9092"}
}

configure /etc/kafka-proxy/producer.properties
exec java -cp ${KAFKA_PROXY_HOME}/lib/* io.alphash.kafka.proxy.rest.SimpleRestServer
