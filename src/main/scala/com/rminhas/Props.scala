package com.rminhas

import io.confluent.kafka.serializers.{KafkaAvroDeserializer, KafkaAvroDeserializerConfig, KafkaAvroSerializer}
import org.apache.kafka.clients.producer.ProducerConfig.{BOOTSTRAP_SERVERS_CONFIG, KEY_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER_CLASS_CONFIG}
import org.apache.kafka.clients.consumer.ConsumerConfig.{GROUP_ID_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import java.util.Properties

object Props {
  val TOPIC = "rizwan.test.topic"

  def getProducerProps():Properties =
    val props: Properties = new Properties()
    props.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9091")
    props.setProperty(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, classOf[KafkaAvroSerializer].getName)
    props.setProperty("schema.registry.url", "http://localhost:8081")
    props

  def getConsumerProps(consumerGroupName: String): Properties =
    val props: Properties = new Properties()
    props.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9091")
    props.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[KafkaAvroDeserializer].getName)
    props.put(GROUP_ID_CONFIG, consumerGroupName)
    props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true)
    props.setProperty("schema.registry.url", "http://localhost:8081")
    props
}
