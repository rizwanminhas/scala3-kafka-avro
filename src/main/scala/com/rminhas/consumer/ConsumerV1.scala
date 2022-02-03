package com.rminhas.consumer

import com.rminhas.Props
import com.rminhas.avro.generated.v1.User
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration.ofMillis
import java.util.Properties
import scala.util.{Failure, Success, Try}

object ConsumerV1 extends App {
  def createConsumerWithSchema(groupName: String): KafkaConsumer[String, User] = {
    val consumer = new KafkaConsumer[String, User](Props.getConsumerProps(groupName))
    consumer.subscribe(java.util.Arrays.asList(Props.TOPIC))
    consumer
  }

  def printRecords(consumer: KafkaConsumer[String, User]): Unit =
    consumer
      .poll(ofMillis(50))
      .forEach(data => {
        Try(data.value()) match {
          case Failure(exception) => println(s"unknown type: ${data.value()}")
          case Success(user) =>
            println(s"Name: ${user.getName}")
            println(s"Age: ${user.getAge}")
            println(s"data.toString: ${data.toString}")
            println(s"data.value: ${data.value()}")
            println(s"data.serializedValueSize: ${data.serializedValueSize()}")
        }
        println("\n\n*********************************************************************************************\n\n")
      })

  val v1SchemaConsumer = createConsumerWithSchema("consumer-groupv1")

  while (true)
    printRecords(v1SchemaConsumer)

}
