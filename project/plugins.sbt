resolvers += Resolver.typesafeRepo("releases")

libraryDependencies += "com.geirsson" %% "scalafmt-bootstrap" % "0.6.6"

// Add the sbt-scalariform plugin
//addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
// Add the sbt-doctest plugin
addSbtPlugin("com.github.tkawachi" % "sbt-doctest" % "0.5.0")
// Add the sbt-packager plugin
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")
// Add the code coverage plugin
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
// Add the scala style check plugin
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")
// Add dependency tree graph plugin
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
// Add `sbt-scalafmt` plugin
//addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "0.4")
//addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.0.0-RC1")

// sbt plugin for scapegoat scala static analysis plugin
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.4")
