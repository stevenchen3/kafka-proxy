import sbt._

object Dependencies {
  // Akka main library dependencies
  lazy val akkaVersion = "2.5.14"
  val akkaDeps = Seq(
    "com.typesafe.akka" %% "akka-stream",
    "com.typesafe.akka" %% "akka-stream-testkit",
    "com.typesafe.akka" %% "akka-testkit"
  ).map(_ % akkaVersion)

  // Akka HTTP library dependencies
  lazy val akkaHttpVersion = "10.1.3"
  val akkaHttpDeps = Seq(
    "com.typesafe.akka" %% "akka-http",
    "com.typesafe.akka" %% "akka-http-testkit",
    "com.typesafe.akka" %% "akka-http-spray-json",
    "com.typesafe.akka" %% "akka-http-xml"
  ).map(_ % akkaHttpVersion)

  lazy val gRpcJavaVersion = "1.14.0"
  val gRpcDeps = Seq(
    "io.grpc" % "grpc-protobuf",
    "io.grpc" % "grpc-stub",
    "io.grpc" % "grpc-netty",
    "io.grpc" % "grpc-testing"
  ).map(_ % gRpcJavaVersion)

  // Apache Kafka libaray dependencies
  lazy val kafkaVersion = "1.1.0"
  val kafkaDeps = Seq(
    "org.apache.kafka" %% "kafka",
    "org.apache.kafka" %  "kafka-clients"
  ).map(_ % kafkaVersion)

  // Scalatest library dependencies
  val scalatestDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test
  )

  // sl4j logging library dependencies, required to include in assembly
  lazy val sl4jVersion = "1.7.5"
  val sl4jDeps = Seq(
    "org.slf4j" % "slf4j-api",
    "org.slf4j" % "slf4j-log4j12"
  ).map(_ % sl4jVersion)
}
