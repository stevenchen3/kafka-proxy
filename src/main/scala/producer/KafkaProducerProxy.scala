package io.alphash.kafka.proxy.producer

import io.alphash.kafka.proxy.rest._

trait KafkaProducerProxy {
  def publish(topic: String, message: Message): Unit
}
