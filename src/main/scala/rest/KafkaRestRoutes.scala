package io.alphash.kafka.proxy.rest

import KafkaProducerActor._

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete

import scala.concurrent.Future
import akka.pattern.ask
import akka.util.Timeout

trait KafkaRestRoutes extends JsonSupport {
  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[KafkaRestRoutes])

  def kafkaProducerActor: ActorRef

  implicit lazy val timeout = Timeout(5.seconds)

  val message = Message(List(Record("hello")))
  lazy val kafkaRestRoutes: Route =
    pathPrefix("topics" / Segment) { topic ⇒
      pathEnd {
        post {
          // Parse request body into Json object
          entity(as[Message]) { message ⇒
            val kafkaProducerActor: ActorRef = system.actorOf(KafkaProducerActor.props)
            val future: Future[ActionPerformed] =
              (kafkaProducerActor ? Publish(topic, message)).mapTo[ActionPerformed]

            onSuccess(future) { result ⇒
              complete((StatusCodes.Created, result))
            }
          } // End of 'entity'
        } ~
        // For routing test only
        options {
          complete((StatusCodes.OK, ActionPerformed("OK")))
        }
      }
    }
}
