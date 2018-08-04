package io.alphash.kafka.proxy.rest

import io.alphash.kafka.proxy.producer._

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
  val proxy = SimpleKafkaProducerProxy()

  def receive: Receive = {
    case Publish(topic, message) ⇒
      SimpleKafkaProducerProxy().publish(topic, message)
      proxy.publish(topic, message)
      sender() ! ActionPerformed("OK")
    case _ ⇒ sender() ! ActionPerformed("Bad request")
  }
}
