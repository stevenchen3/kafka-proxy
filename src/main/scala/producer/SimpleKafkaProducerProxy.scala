package io.alphash.kafka.proxy.producer

import io.alphash.kafka.proxy.rest.{Record, Message}

import java.util.Properties

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.utils.Utils

final class SimpleKafkaProducerProxy extends KafkaProducerProxy {
  lazy val producerConfig =
    System.getProperty("producer.config", "/etc/kafka-proxy/producer.properties")

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

  private val producer: KafkaProducer[String, String] = new KafkaProducer(props)

  lazy val base64Decode: Boolean =
    props.getProperty("enable.base64.decode", "false").toBoolean

  def getPayload(record: Record, base64Decode: Boolean): String = {
    import java.util.Base64
    if (base64Decode) {
      new String(Base64.getDecoder.decode(record.value))
    } else {
      record.value
    }
  }

  def publish(topic: String, message: Message): Unit = {
    message.records.foreach { record ⇒
      // Asynchronously
      producer.send(new ProducerRecord(topic, getPayload(record, base64Decode)))
    }
    producer.flush
  }
}

object SimpleKafkaProducerProxy {
  def apply() = new SimpleKafkaProducerProxy()
}
