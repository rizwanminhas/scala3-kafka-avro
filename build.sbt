name := "scala3-kafka-avro"

version := "1.0"

scalaVersion := "3.1.0"

resolvers += "confluent" at "https://packages.confluent.io/maven/"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.11.0",
  "org.apache.kafka" % "kafka-clients" % "2.8.0",
  "io.confluent" % "kafka-avro-serializer" % "5.5.1",
  "org.slf4j" % "slf4j-api" % "1.7.33",
  "org.slf4j" % "slf4j-simple" % "1.7.33"
)

AvroConfig / sourceDirectory := file("src/main/resources/avro")
//AvroConfig / javaSource := file("src/main/scala")

// To generate the sources in "target/scala-3.1.0/src_managed/main/compiled_avro/com.rminhas..." type:
// sbt avro:generate or sbt Avro/generate (this task automatically executes before the Compile task)

// This will add the generated avro sourcecode on the classpath or you can manually add this folder on class path from module settings:
Compile / unmanagedSourceDirectories += baseDirectory.value / "target/scala-3.1.0/src_managed/main/compiled_avro"