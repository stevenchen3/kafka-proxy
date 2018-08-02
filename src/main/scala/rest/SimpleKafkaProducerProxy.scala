package io.alphash.kafka.proxy.rest

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.utils.Utils

final class SimpleKafkaProducerProxy extends KafkaProducerProxy {
  lazy val producerConfig =
    System.getProperty("config.file", "/etc/kafka-proxy/producer.properties")

  lazy val props: Properties = {
    val properties: Properties = new Properties()
    properties.putAll(Utils.loadProps(producerConfig))
    properties.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.ByteArraySerializer"
    )
    properties.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.ByteArraySerializer"
    )
    properties
  }

  lazy val base64Decode: Boolean =
    props.getProperty("enable.base64.decode", "false").toBoolean

  def getPayload(record: Record, base64Decode: Boolean): Array[Byte] = {
    import java.util.Base64
    if (base64Decode) {
      Base64.getDecoder.decode(record.value)
    } else {
      record.value.getBytes
    }
  }

  def publish(topic: String, message: Message): Unit= {
    val producer: KafkaProducer[Array[Byte], Array[Byte]] = new KafkaProducer(props)
    //producer.initTransactions
    //producer.beginTransaction
    message.records.map { r ⇒
      producer.send(new ProducerRecord(topic, getPayload(r, base64Decode)), SimpleCallback())
    }
    //producer.commitTransaction
    //producer.flush
    producer.close
  }
}

final case class SimpleCallback() extends Callback {
  def onCompletion(m: RecordMetadata, e: Exception): Unit = {}
}

object SimpleKafkaProducerProxy {
  def apply() = new SimpleKafkaProducerProxy()
}
