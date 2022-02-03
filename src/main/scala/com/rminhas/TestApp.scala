package com.rminhas

import com.rminhas.avro.generated.v1.User as UserV1
import com.rminhas.avro.generated.v2.User as UserV2
import com.rminhas.producer.{ProducerV1, ProducerV2}

@main
def main(): Unit =
  val producerV1 = ProducerV1.send(new UserV1("rizwan1", 1))
  val producerV2 = ProducerV2.send(new UserV2("rizwan2", 2, "rizwan@test.com"))
  println("***********************************************************************************************************")
  println(producerV1.get())
  println(producerV2.get())
  ProducerV1.close()
  ProducerV2.close()

