import Dependencies._

lazy val commonSettings = Seq(
  name := "kafka-proxy",
  organization := "io.alphash",
  scalaVersion := "2.12.8",
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
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.typesafeRepo("releases")
  )
)

// Path to where the grpc-java codegen compiler (i.e., 'protoc-gen-grpc-java' binary) is installed
// See https://github.com/grpc/grpc-java/tree/master/compiler for details
lazy val grpcJavaPluginPath = "/usr/local/bin/protoc-gen-grpc-java"

lazy val root = Project(id = "kafka-proxy", base = file("."))
  .enablePlugins(ProtobufPlugin)
  .settings(commonSettings: _*)
  .settings(fork in run  := true)
  .settings(fork in Test := true)
  .settings(libraryDependencies ++= akkaDeps)
  .settings(libraryDependencies ++= akkaHttpDeps)
  .settings(libraryDependencies ++= kafkaDeps)
  .settings(libraryDependencies ++= sl4jDeps)
  .settings(libraryDependencies ++= gRpcDeps)
  // To generate gRPC Java classes, below protobuf options are required
  .settings(protobufProtocOptions in ProtobufConfig ++= Seq(
    s"--plugin=protoc-gen-grpc-java=$grpcJavaPluginPath",
    // Output to where other generated protobuf Java classes reside
    // By default, SBT will compile sources from this directory
    s"--grpc-java_out=${sourceManaged.value}/main/compiled_protobuf"
  ))

assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" ⇒ MergeStrategy.first
  case x ⇒
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
