FROM centos-base:jre8

ARG KAFKA_PROXY_VERSION=0.9.0-SNAPSHOT

ENV KAFKA_PROXY_HOME=/opt/kafka-proxy

RUN set -ex && \
    mkdir -p ${KAFKA_PROXY_HOME}/bin \
             ${KAFKA_PROXY_HOME}/config \
             ${KAFKA_PROXY_HOME}/lib \
             /etc/kafka-proxy

COPY target/scala-2.11/kafka-proxy-assembly-${KAFKA_PROXY_VERSION}.jar ${KAFKA_PROXY_HOME}/lib
COPY docker/start-proxy.sh      ${KAFKA_PROXY_HOME}/bin
COPY docker/producer.properties ${KAFKA_PROXY_HOME}/config
COPY shell/config-utils.sh      ${KAFKA_PROXY_HOME}/bin

RUN set -ex && \
    ln -s ${KAFKA_PROXY_HOME}/bin/* /usr/local/bin/ && \
    ln -s ${KAFKA_PROXY_HOME}/config/producer.properties /etc/kafka-proxy/producer.properties

EXPOSE 8080

WORKDIR ${KAFKA_PROXY_HOME}

ENTRYPOINT ["start-proxy.sh"]
