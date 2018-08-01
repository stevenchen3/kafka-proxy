package io.alphash.kafka.proxy.rest

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

class SimpleRestServer extends KafkaRestRoutes {
  implicit val system: ActorSystem = ActorSystem("kafka-rest-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  override lazy val log = Logging(system, classOf[SimpleRestServer])

  val kafkaProducerActor: ActorRef = system.actorOf(KafkaProducerActor.props, "kafkaProducerActor")

  def start(host: String = "0.0.0.0", port: Int = 8080): Unit = {
    Http().bindAndHandle(kafkaRestRoutes, host, port)

    log.info(s"Server is listening on $host:$port")
    Await.result(system.whenTerminated, Duration.Inf)
  }
}

object SimpleRestServer extends App {
  val server = new SimpleRestServer()
  server.start()
}
