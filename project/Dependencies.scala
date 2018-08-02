import sbt._

object Dependencies {
  // Akka main library dependencies
  lazy val akkaVersion = "2.5.11"
  val akkaDeps = Seq(
    "com.typesafe.akka" %% "akka-stream",
    "com.typesafe.akka" %% "akka-stream-testkit",
    "com.typesafe.akka" %% "akka-testkit"
  ).map(_ % akkaVersion)

  // Akka HTTP library dependencies
  lazy val akkaHttpVersion = "10.0.11"
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

  // ScalaCheck library dependencies
  val scalaCheckDeps = Seq("org.scalacheck" %% "scalacheck" % "1.13.4" % "test")

  lazy val kafkaVersion = "2.0.0"
  val kafkaDeps = Seq(
    "org.apache.kafka" %% "kafka",
    "org.apache.kafka" % "kafka-clients"
  ).map(_ % kafkaVersion)
}
