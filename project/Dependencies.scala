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

  // Scalatest library dependencies
  val scalatestDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test
  )

  // Apache Kafka libaray dependencies
  lazy val kafkaVersion = "1.1.0"
  val kafkaDeps = Seq(
    "org.apache.kafka" %% "kafka",
    "org.apache.kafka" % "kafka-clients"
  ).map(_ % kafkaVersion)

  val sl4jDeps = Seq(
    "org.slf4j" % "slf4j-api" % "1.7.5",
    "org.slf4j" % "slf4j-log4j12" % "1.7.5"
  )

  val gRpcDeps = Seq(
    "io.grpc" % "grpc-all" % "1.14.0"
  )
}
