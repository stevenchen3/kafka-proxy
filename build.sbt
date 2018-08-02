import Dependencies._

lazy val commonSettings = Seq(
  name := "kafka-proxy",
  organization := "alphash.io",
  scalaVersion := "2.12.4",
  scalacOptions in Compile ++= Seq(
    "-encoding",
    "UTF-8",
    "-target:jvm-1.8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint"
  ),
  javacOptions in Compile ++= Seq(
    "-source",
    "1.8",
    "-target",
    "1.8",
    "-Xlint:unchecked",
    "-Xlint:deprecation"
  ),
  javaOptions in Test ++= Seq("-Xms256m", "-Xmx2g", "-Dconfig.resource=test.conf"),
  javaOptions in run  ++= Seq("-Xms256m", "-Xmx2g", "-XX:+UseParallelGC", "-server"),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val root = Project(id = "kafka-proxy", base = file("."))
  .enablePlugins(ProtobufPlugin)
  .settings(commonSettings: _*)
  .settings(fork in run := true)
  .settings(fork in Test := true)
  .settings(libraryDependencies ++= akkaDeps)
  .settings(libraryDependencies ++= akkaHttpDeps)
  .settings(libraryDependencies ++= kafkaDeps)
