## How to run?
1. `docker-compose up`. This will start the confluent control center at [http://localhost:9021](http://localhost:9021) and the schema registry at [http://localhost:8081/subjects](http://localhost:8081/subjects).
2. To generate java classes from the `*.avsc` files execute `sbt avroGenerate` or `sbt compile` this will generate the files under `target\scala-3.1.0/src_managed/main/compiled_avro`. Make sure this directory is also on your classpath.
3. Go to the confluent center at [http://localhost:9021](http://localhost:9021) and make sure `confluent_value_schema_validation` is set to `true` under the `configuration` tab.
4. Run the `ConsumerV1` and `ConsumerV2` and then run the `TestApp` to produce messages from both the producers. `ConsumerV1` will only successfully display the `ProducerV1` message and `ConsumerV2` will only successfully display the `ProducerV2` message.

**Notes:**
1. Enable `--config confluent.value.schema.validation=true` if you want to enable schema validation. This option is only available on `Confluent Server` not `Apache Kafka`. Another option is to first create the topics from docker-compose and then manually go to confluent control center and enable these options on the topic from the UI (step 3 above).
2. To clean all kafka data on shutdown use `docker-compose down -v`.
3. To retain all kafka data on shutdown use `docker-compose down`.