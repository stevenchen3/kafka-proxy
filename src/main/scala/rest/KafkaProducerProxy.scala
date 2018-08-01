package io.alphash.kafka.proxy.rest

trait KafkaProducerProxy {
  def publish(topic: String, message: Message): Unit
}
