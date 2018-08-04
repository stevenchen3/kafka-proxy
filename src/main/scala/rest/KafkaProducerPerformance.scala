package io.alphash.kafka.proxy.rest

import java.util.Properties;

import scala.io.Source

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.utils.Utils

object KafkaProducerPerformance extends App with KafkaProducerProxy {
  lazy val producerConfig =
    System.getProperty("config.file", "/etc/kafka-proxy/producer.properties")
  lazy val payload: String = Source.fromFile(
    System.getProperty("payload.file", "/etc/kafka-proxy/payload.txt")
  ).getLines.mkString

  lazy val props: Properties = {
    val properties: Properties = new Properties()
    properties.putAll(Utils.loadProps(producerConfig))
    properties.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer"
    )
    properties.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer"
    )
    properties
  }

  def publish(topic: String = "scala", message: Message = Message(List[Record]())): Unit= {
    val producer: KafkaProducer[String, String] = new KafkaProducer(props)
    val start: Long = System.currentTimeMillis
    for (x <- 1 to 100000) {
      producer.send(new ProducerRecord(topic, payload))
    }
    producer.flush
    producer.close
    val end: Long = System.currentTimeMillis
    println(s"Elapsed time: ${(end - start) / 1000} seconds")
  }

  publish()
}
