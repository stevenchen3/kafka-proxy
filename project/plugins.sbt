resolvers += Resolver.typesafeRepo("releases")

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

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
