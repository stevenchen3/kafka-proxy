# Kafka Proxy

Inspired by [Confluent REST Proxy](https://github.com/confluentinc/kafka-rest) and
[Kafka-Pixy](https://github.com/mailgun/kafka-pixy). Give it a try to implement a
simple REST/gRPC proxy built atop Akka HTTP for Apache Kafka using Scala and carry
out some benchmarks. The codes are for quick prototyping purpose, and have not yet
been covered by unit tests.


## Building the source on bare-metal

Prerequisites:
- JDK 1.8
- SBT
- Protobuf 3.0.0 or above
- `grpc-java` codegen compiler plugin

### Installing `grpc-java` plugin from source

The plugin `protoc-gen-grpc-java` is required by `sbt` in order to automatically generate gRPC
related Java source files in `Compile` scope.

```bash
git clone --recursive git@github.com:grpc/grpc-java.git
cd grpc-java/compiler
../gradlew java_pluginExecutable
../gradlew test
cp build/exe/java_plugin/protoc-gen-grpc-java /usr/local/bin/
```

### Building

Compile the source codes and run the tests:

```bash
sbt compile
sbt test
sbt assembly
```


## Building the source inside Docker

Prerequisites:
- Docker
- Docker Compose

### Building

Compile the source codes and run the tests (it may take quite some time for the first time):

```bash
docker-compose run test
```

### Building the Docker image

Below will create a Docker image tagged as `kafka-proxy:0.1.0-rc1`.

```bash
docker-compose build
```


## Gotcha

+ `dyld: Library not loaded` error during compilation

```bash
dyld: Library not loaded: /usr/local/opt/protobuf/lib/libprotoc.13.dylib
```

Reinstall `grpc-java` to fix it.
