package io.alphash.kafka.proxy.rest

//import akka.actor.{ActorRef, ActorSystem}
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

//import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.ExecutionContext
//import scala.concurrent.duration.Duration

final class SimpleRestServer extends KafkaRestRoutes {
  implicit val system: ActorSystem = ActorSystem("kafka-rest-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  override lazy val log = Logging(system, classOf[SimpleRestServer])

  //val kafkaProducerActor: ActorRef = system.actorOf(KafkaProducerActor.props, "kafkaProducerActor")

  def start(host: String = "0.0.0.0", port: Int = 8080): Unit = {
    //Http().bindAndHandle(kafkaRestRoutes, host, port)
    Http().bind(host, port).runForeach(_.handleWith(Route.handlerFlow(kafkaRestRoutes)))
    log.info(s"Server is listening on $host:$port")
  }
}

object SimpleRestServer extends App {
  val server = new SimpleRestServer()
  server.start()
}
