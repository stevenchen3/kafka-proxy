package io.alphash.kafka.proxy.rest

import io.alphash.kafka.proxy.producer._

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

final class SimpleRestServer extends KafkaRestRoutes {
  implicit val system: ActorSystem = ActorSystem("kafka-rest")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val blockingDispatcher = system.dispatchers.lookup("http-blocking-dispatcher")

  override lazy val log = Logging(system, classOf[SimpleRestServer])

  //val kafkaProducerActor: ActorRef = system.actorOf(KafkaProducerActor.props, "KafkaRestProxy")
  val proxy: KafkaProducerProxy = SimpleKafkaProducerProxy()

  def start(host: String = "0.0.0.0", port: Int = 8080): Unit = {
    Http().bindAndHandle(kafkaRestRoutes, host, port)
    log.info(s"Server is listening on $host:$port")
  }
}

object SimpleRestServer extends App {
  val server = new SimpleRestServer()
  server.start()
}
