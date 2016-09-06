package kafka

import java.util
import java.util.Properties

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

/**
  * Created by cgnal on 05/09/16.
  */
object MessageConsumer {

  def run() = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "test")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("session.timeout.ms", "30000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    val consumer = new KafkaConsumer(props)
    consumer.subscribe(util.Arrays.asList("pluto", "pippo"))

    while (true) {
      val records = consumer.poll(1000).iterator()
      while (records.hasNext) {
        val record = records.next()
        System.out.println(record.offset() + ": " + record.value())
      }
    }
  }

  }
  object MessageConsumerMain {
    def main(args: Array[String]): Unit = {
      MessageConsumer.run()
    }
  }

