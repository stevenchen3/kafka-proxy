# Kafka Proxy

A simple proxy built atop Akka HTTP for Apache Kafka

# Building the Docker image

Make sure that you have Docker and Docker Compose installed properly, and then do the following:

```
sbt assembly
docker-compose build
```

# Compile `protoc-gen-grpc-java` plugin from source

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
