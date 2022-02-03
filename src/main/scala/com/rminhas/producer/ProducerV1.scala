package com.rminhas.producer

import com.rminhas.Props
import com.rminhas.avro.generated.v1.User
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import org.slf4j.LoggerFactory
import java.util.concurrent.Future

object ProducerV1 {
  val logger = LoggerFactory.getLogger(ProducerV1.getClass)

  private val producer = new KafkaProducer[String, User](Props.getProducerProps())

  def send(user: User): Future[RecordMetadata] =
    val record = new ProducerRecord[String, User](Props.TOPIC, user)
    producer.send(record, (meta: RecordMetadata, ex: Exception) =>
      if (ex == null)
        logger.info(s"""
             |*************************************
             |Topic: ${meta.topic}
             |Partition: ${meta.partition}
             |Offset: ${meta.offset}
             |Timestamp: ${meta.timestamp}
             |*************************************
             |""".stripMargin)
      else
        logger.error(s"Error -> $ex")
    )

  def close(): Unit =
    producer.close()
}
