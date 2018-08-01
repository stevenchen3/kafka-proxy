package io.alphash.kafka.proxy.rest

import KafkaProducerActor._

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val recordJsonFormat  = jsonFormat1(Record)
  implicit val messageJsonFormat = jsonFormat1(Message)
  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
