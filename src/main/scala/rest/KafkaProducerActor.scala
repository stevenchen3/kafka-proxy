package io.alphash.kafka.proxy.rest

import akka.actor.{Actor, ActorLogging, Props}

final case class Record(value: String)
final case class Message(records: Seq[Record])

object KafkaProducerActor {
  final case class ActionPerformed(description: String)
  final case class Publish(topic: String, message: Message)

  def props: Props = Props[KafkaProducerActor]
}

class KafkaProducerActor extends Actor with ActorLogging {
  import KafkaProducerActor._

  def receive: Receive = {
    case Publish(topic, message) ⇒
      SimpleKafkaProducerProxy().publish(topic, message)
      sender() ! ActionPerformed("Message published")
    case _ ⇒ sender() ! ActionPerformed("Operation not allowed")
  }
}
