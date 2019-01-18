# Kafka Proxy

Inspired by [Confluent REST Proxy](https://github.com/confluentinc/kafka-rest) and
[Kafka-Pixy](https://github.com/mailgun/kafka-pixy). Give it a try to implement a
simple REST/gRPC proxy built atop Akka HTTP for Apache Kafka using Scala and carry
out some benchmarks. The codes are for quick prototyping purpose, and have not yet
been covered by unit tests.


## Building the source on bare-metal

Prerequisites:

- JDK 1.8
- SBT 0.13.x or 1.0.x
- Protobuf 3.0.0 or above
- `grpc-java` codegen compiler plugin

### Installing `grpc-java` plugin from source

The plugin `protoc-gen-grpc-java` is required by `sbt-protobuf` plugin in order to
automatically generate gRPC related Java source files in `Compile` scope. Below is
one way to get this `protoc-gen-grpc-java` binary.

```bash
git clone --recursive git@github.com:grpc/grpc-java.git
cd grpc-java/compiler
../gradlew java_pluginExecutable
../gradlew test
cp build/exe/java_plugin/protoc-gen-grpc-java /usr/local/bin/
```

See [here](https://github.com/grpc/grpc-java/blob/master/COMPILING.md#how-to-build-code-generation-plugin)
for details.

### Building

Compile the source codes and run the tests:

```bash
sbt compile
sbt test
sbt assembly
```


## Building the source inside Docker

Alternatively, we can build the source using Docker in that we could possibly avoid complex 
dependencies on bare-metal environment.

Prerequisites:

- Docker 18 or above
- Docker Compose 1.23 or above

### Building

Compile the source codes and run the tests (it may take some time for the first time):

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
