package io.alphash.kafka.proxy.rest

import KafkaProducerActor._

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.server.directives.PathDirectives.path

import scala.concurrent.Future
import akka.pattern.ask
import akka.util.Timeout

trait KafkaRestRoutes extends JsonSupport {
  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[KafkaRestRoutes])

  def kafkaProducerActor: ActorRef

  implicit lazy val timeout = Timeout(60.seconds)

  lazy val kafkaRestRoutes: Route =
    pathPrefix("topics" / Segment) { topic ⇒
      pathEnd {
        post {
          entity(as[Message]) { message ⇒
            val messagePublished: Future[ActionPerformed] =
              (kafkaProducerActor ? Publish(topic, message)).mapTo[ActionPerformed]

            onSuccess(messagePublished) { performed ⇒
              log.info("Published message")
              complete((StatusCodes.Created, performed))
            }
          } // end of 'entity'
        }
      } // end of 'post'
    }
}
