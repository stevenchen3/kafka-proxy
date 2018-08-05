package io.alphash.kafka.proxy.grpc

import io.alphash.kafka.proxy.producer._
import io.alphash.kafka.proxy.rest.{Record, Message}

import io.grpc.{Server, ServerBuilder}
import io.grpc.stub.StreamObserver

class SimpleGrpcServer(val port: Int) {
  private lazy val server: Server =
    ServerBuilder.forPort(port).addService(new KafkaProxyImpl()).build

  def start(): Unit = {
    server.start
    println(s"Server started, listening on $port")
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = {
        SimpleGrpcServer.this.stop
      }
    })
  }

  def stop(): Unit = {
    server.shutdown
  }

  def blockUntilShutdown(): Unit = {
    server.awaitTermination
  }
}

class KafkaProxyImpl extends KafkaProxyGrpc.KafkaProxyImplBase {
  val proxy: KafkaProducerProxy = SimpleKafkaProducerProxy()

  override def publish(
    request: KafkaMessage,
    responseObserver: StreamObserver[PublishReply]
  ): Unit = {
    import collection.JavaConverters._
    val topic: String = request.getTopic
    val records: Seq[Record] = request.getRecordsList.asScala.map(Record(_))
    println(s"Received: ${topic}, ${records}")
    proxy.publish(topic, Message(records))

    val reply = PublishReply.newBuilder.setStatus(201).setMessage("OK").build
    responseObserver.onNext(reply)
    responseObserver.onCompleted
  }
}

object SimpleGrpcServer extends App {
  def apply(port: Int): SimpleGrpcServer = new SimpleGrpcServer(port)

  def start(): Unit = {
    val port: Int = System.getProperty("server.port", "50051").toInt
    val server = SimpleGrpcServer(port)
    server.start
    server.blockUntilShutdown
  }

  start()
}
