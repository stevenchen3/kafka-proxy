import java.util.Properties;

import org.apache.kafka.clients.{ClientRequest, ClientResponse}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.errors.{ApiException, NetworkException}
import org.apache.kafka.common.metrics.Metrics
import org.apache.kafka.common.protocol.{ApiKeys, Errors, Protocol}
import org.apache.kafka.common.record.MemoryRecords
import org.apache.kafka.common.requests.MetadataResponse.{PartitionMetadata, TopicMetadata}
import org.apache.kafka.common.requests.ProduceResponse.PartitionResponse
import org.apache.kafka.common.requests._

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.{ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.utils.Utils

final class SimpleKafkaProducerProxy extends KafkaProducerProxy {
  //val producerConfig: String = "/Users/steven/Desktop/kafka/producer.properties"
  val producerConfig: String = "/etc/kafka-proxy/producer.properties"

  val props: Properties = {
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

  def publish(topic: String, message: Message): Unit= {
    val producer: KafkaProducer[Array[Byte], Array[Byte]] = new KafkaProducer(props)
    //val record: ProducerRecord[Array[Byte], Array[Byte]] = 
  }
}

object SimpleKafkaProducerProxy {
  def apply() = new SimpleKafkaProducerProxy()
}
