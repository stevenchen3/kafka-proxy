# Kafka Proxy

Inspired by [Confluent REST Proxy](https://github.com/confluentinc/kafka-rest) and
[Kafka-Pixy](https://github.com/mailgun/kafka-pixy). Give it a try to implement a
simple REST/gRPC proxy built atop Akka HTTP for Apache Kafka using Scala and carry
out some benchmarks. The codes are for quick prototyping purpose, and have not yet
been covered by unit tests.


## Installing `grpc-java` plugin from source

The plugin `protoc-gen-grpc-java` is required by `sbt` in order to automatically generate gRPC
related Java source files in `Compile` scope. Before proceeding to the following steps, make
sure that you have [Gradle](https://gradle.org/install/) properly.

```
git clone --recursive git@github.com:grpc/grpc-java.git
cd grpc-java/compiler
../gradlew java_pluginExecutable
../gradlew test
cp build/exe/java_plugin/protoc-gen-grpc-java /usr/local/bin/
```

## Building the Docker image

Make sure that you have Docker, Docker Compose and Protocol Buffers compiler (i.e., `protoc`)
installed properly, and then do the following:

```
sbt assembly
docker-compose build
```
