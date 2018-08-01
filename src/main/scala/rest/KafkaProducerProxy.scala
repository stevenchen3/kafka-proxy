trait KafkaProducerProxy {
  def publish(topic: String, message: Message): Unit
}
