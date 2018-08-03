package io.alphash.kafka.proxy.rest

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

final class SimpleRestServer extends KafkaRestRoutes {
  implicit val system: ActorSystem = ActorSystem("kafka-rest-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatchers.lookup("http-blocking-dispatcher")

  override lazy val log = Logging(system, classOf[SimpleRestServer])

  val kafkaProducerActor: ActorRef = system.actorOf(KafkaProducerActor.props, "KafkaRestProxy")

  def start(host: String = "0.0.0.0", port: Int = 8080): Unit = {
    Http().bindAndHandle(kafkaRestRoutes, host, port)
    log.info(s"Server is listening on $host:$port")
  }
}

object SimpleRestServer extends App {
  val server = new SimpleRestServer()
  server.start()
}
