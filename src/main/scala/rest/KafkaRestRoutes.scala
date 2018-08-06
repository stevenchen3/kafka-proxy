package io.alphash.kafka.proxy.rest

import KafkaProducerActor._

import io.alphash.kafka.proxy.producer._

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait KafkaRestRoutes extends JsonSupport {
  implicit def system: ActorSystem
  implicit def blockingDispatcher: ExecutionContext

  val proxy: KafkaProducerProxy

  lazy val log = Logging(system, classOf[KafkaRestRoutes])

  lazy val kafkaRestRoutes: Route =
    pathPrefix("topics" / Segment) { topic ⇒
      pathEnd {
        post {
          entity(as[Message]) { message ⇒
            val future: Future[ActionPerformed] = Future {
              proxy.publish(topic, message)
              ActionPerformed("OK")
            }
            onSuccess(future) { result ⇒
              complete((StatusCodes.Created, result))
            }
          } // End of 'entity'
        } ~
        // Test Akka HTTP routing performance
        get {
          complete((StatusCodes.OK, ActionPerformed("OK")))
        } ~
        // Test request body deserialization
        put {
          entity(as[Message]) { message ⇒
            complete((StatusCodes.Created, ActionPerformed("ACK")))
          } // End of 'entity'
        }
      }
    }
}
