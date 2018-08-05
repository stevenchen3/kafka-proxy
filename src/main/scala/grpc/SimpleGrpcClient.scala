package io.alphash.kafka.proxy.grpc

import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import java.util.concurrent.TimeUnit

class SimpleGrpcClient(val host: String, val port: Int) {
  // Channels are secure by default (via SSL/TLS).
  // For the example we disable TLS to avoid needing certificates.
  private val channel: ManagedChannel =
    ManagedChannelBuilder.forAddress(host, port).usePlaintext.build
  private val blockingStub = KafkaProxyGrpc.newBlockingStub(channel)

  def shutdown(): Unit = channel.shutdown.awaitTermination(5, TimeUnit.SECONDS)

  def publish(topic: String, records: Seq[String]): Unit = {
    import collection.JavaConverters._
    val request: KafkaMessage =
      KafkaMessage.newBuilder.setTopic(topic).addAllRecords(records.asJava).build
    val response = blockingStub.publish(request)
    println(s"Reply: ${response.getStatus}, ${response.getMessage}")
  }
}

object SimpleGrpcClient extends App {
  def apply(host: String, port: Int) = new SimpleGrpcClient(host, port)

  lazy val client: SimpleGrpcClient = args match {
    case Array(host, port, _*) ⇒ SimpleGrpcClient(host, port.toInt)
    case _ ⇒ SimpleGrpcClient("localhost", 50051)
  }

  println(s"Connecting to ${client.host}:${client.port}")
  for (x <- 1 to 10) {
    client.publish("scala", List(s"hello-$x"))
  }
  client.shutdown
}
