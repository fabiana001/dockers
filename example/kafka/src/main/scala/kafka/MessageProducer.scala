package kafka

import java.io.StringWriter
import java.security.Timestamp

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import java.util.Properties

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by cgnal on 02/09/16.
  */
object MessageProducer {
case class Message(name: String, time: Long, value: Int)
  //val log = LogManager.getLogger(MessageProducer.getClass)


  def run(): Unit = {

    val  props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)

    val startTime = System.currentTimeMillis

    for (i <-0 to 100) {
      val message = Message("test", System.currentTimeMillis(), i)

      val out = new StringWriter
       mapper.writeValue(out, message)
       val json = out.toString
      // send lots of messages
      producer.send(new ProducerRecord[String, String]("pluto", "", json))

      // every so often send to a different topic
      if (i % 5 == 0) {
        producer.send(new ProducerRecord[String, String]("pippo", "", json))
        producer.flush()
        System.out.println(s"Sent msg number $i value ")
      }
    }

    val record = new ProducerRecord("end", "key", "the end"+new java.util.Date)
    producer.send(record)

    producer.close()
    val endtime = (System.currentTimeMillis() - startTime)/ 1000
    println(s"messages sent in $endtime sec")
  }
}

object MessageProducerMain {
  def main(args: Array[String]): Unit = {
    MessageProducer.run()
  }
}
