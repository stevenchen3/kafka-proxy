import sbt._

object Dependencies {
  // Akka main library dependencies
  lazy val akkaVersion = "2.4.16"
  val akkaDeps = Seq(
    "com.typesafe.akka" %% "akka-actor",
    "com.typesafe.akka" %% "akka-agent",
    "com.typesafe.akka" %% "akka-camel",
    "com.typesafe.akka" %% "akka-cluster",
    "com.typesafe.akka" %% "akka-cluster-metrics",
    "com.typesafe.akka" %% "akka-cluster-sharding",
    "com.typesafe.akka" %% "akka-cluster-tools",
    "com.typesafe.akka" %% "akka-contrib",
    "com.typesafe.akka" %% "akka-multi-node-testkit",
    "com.typesafe.akka" %% "akka-osgi",
    "com.typesafe.akka" %% "akka-persistence",
    "com.typesafe.akka" %% "akka-persistence-tck",
    "com.typesafe.akka" %% "akka-remote",
    "com.typesafe.akka" %% "akka-slf4j",
    "com.typesafe.akka" %% "akka-stream",
    "com.typesafe.akka" %% "akka-stream-testkit",
    "com.typesafe.akka" %% "akka-testkit",
    "com.typesafe.akka" %% "akka-distributed-data-experimental",
    "com.typesafe.akka" %% "akka-typed-experimental",
    "com.typesafe.akka" %% "akka-persistence-query-experimental"
  ).map(_ % akkaVersion)

  // Akka HTTP library dependencies
  lazy val akkaHttpVersion = "10.0.2"
  val akkaHttpDeps = Seq(
    "com.typesafe.akka" %% "akka-http-core",
    "com.typesafe.akka" %% "akka-http",
    "com.typesafe.akka" %% "akka-http-testkit",
    "com.typesafe.akka" %% "akka-http-spray-json",
    "com.typesafe.akka" %% "akka-http-jackson",
    "com.typesafe.akka" %% "akka-http-xml"
  ).map(_ % akkaHttpVersion)

  // typelevel Cats library dependencies, including all modules
  val catsDeps = Seq("org.typelevel" %% "cats" % "0.9.0")

  // ScalaPB library dependencies
  val gRPCDeps = Seq(
    "io.grpc" % "grpc-netty" % com.trueaccord.scalapb.compiler.Version.grpcJavaVersion,
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % com.trueaccord.scalapb.compiler.Version.scalapbVersion
  )

  // Scalatest library dependencies
  val scalatestDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test
  )

  // ScalaCheck library dependencies
  val scalaCheckDeps = Seq("org.scalacheck" %% "scalacheck" % "1.13.4" % "test")

  // Specs2 library dependencies
  val specs2Version = "3.8.9"
  val specs2Deps = Seq(
    "org.specs2" %% "specs2-core",
    "org.specs2" %% "specs2-matcher-extra",
    "org.specs2" %% "specs2-cats",
    "org.specs2" %% "specs2-scalaz",
    "org.specs2" %% "specs2-scalacheck",
    "org.specs2" %% "specs2-mock",
    "org.specs2" %% "specs2-analysis",
    "org.specs2" %% "specs2-gwt",
    "org.specs2" %% "specs2-html",
    "org.specs2" %% "specs2-form",
    "org.specs2" %% "specs2-junit"
  ).map(_ % specs2Version).map(_ % "test")

  // Scala logging framework
  val scalaLoggerVersion = "3.5.0"
  val scalaLoggerDeps = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggerVersion,
    "ch.qos.logback" % "logback-core" % "1.1.7",
    "ch.qos.logback" % "logback-classic" % "1.1.7"
  )

  // Commandline parser
  val cliDeps = Seq("com.github.scopt" %% "scopt" % "3.5.0")

  // Scalaz library dependencies
  val scalazVersion = "7.2.12"
  val scalazDeps = Seq("org.scalaz" %% "scalaz-core").map(_ % scalazVersion)

  // Scala `async` module library dependencies
  val scalaAsyncDeps = Seq("org.scala-lang.modules" %% "scala-async" % "0.9.6")

  // Circe library dependencies
  val circeVersion = "0.8.0"
  val circeDeps = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-generic-extras",
    "io.circe" %% "circe-optics",
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-literal"
  ).map(_ % circeVersion)


  lazy val http4sVersion = "0.17.0"
  val http4sDeps = Seq(
    "org.http4s" %% "http4s-circe",
    "org.http4s" %% "http4s-dsl",
    "org.http4s" %% "http4s-blaze-server",
    "org.http4s" %% "http4s-blaze-client"
  ).map(_ % http4sVersion)

  val scalajDeps = Seq(
    "org.scalaj" %% "scalaj-http" % "1.1.5"
  )

  val logbackDeps = Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
  )

  val jodaTimeDeps = Seq(
    "joda-time" % "joda-time" % "2.8.1"
  )

  lazy val kantanVersion = "0.2.1"
  val kantanDeps = Seq(
    "com.nrinaudo" %% "kantan.csv",
    "com.nrinaudo" %% "kantan.csv-java8",
    "com.nrinaudo" %% "kantan.csv-scalaz",
    "com.nrinaudo" %% "kantan.csv-cats",
    "com.nrinaudo" %% "kantan.csv-generic",
    "com.nrinaudo" %% "kantan.csv-joda-time"
  ).map(_ % kantanVersion)

  lazy val kafkaVersion = "2.0.0"
  val kafkaDeps = Seq(
    "org.apache.kafka" %% "kafka",
    "org.apache.kafka" % "kafka-clients"
  ).map(_ % kafkaVersion)
}
