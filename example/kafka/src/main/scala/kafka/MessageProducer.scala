package kafka

import java.security.Timestamp

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import java.util.Properties

import org.apache.avro.generic.GenericData
/**
  * Created by cgnal on 02/09/16.
  */
object MessageProducer {

  //val log = LogManager.getLogger(MessageProducer.getClass)


  def run(): Unit = {

    val  props = new Properties()
    props.put("bootstrap.servers", "kafka:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    /*
    val emp1 = new GenericData.Record(schema)
    emp1.put("name", "Siva")
    emp1.put("joining_date", "10-01-2013")
    emp1.put("role", "Architect")
    emp1.put("salary", 30000.0f)

*/
    val topic="pluto"
    val startTime = System.currentTimeMillis



    for (i <-0 to 100) {
      val string =
        """{
          |"type" : "test",
          |"time" : 1,
          |"k": 0
          |}""".stripMargin
      // send lots of messages
      producer.send(new ProducerRecord[String, String](topic, string))

      // every so often send to a different topic
      if (i % 5 == 0) {
        producer.send(new ProducerRecord[String, String](
          "pippo", "", string))
        producer.flush()
        System.out.println("Sent msg number " + i)
      }
    }

    /*
    for(i<- 1 to 2){
      val record = new ProducerRecord(TOPIC, "key", s"hello $i")
      println(s"message $i sent")
      producer.send(record)
      producer.flush()
    }
    */


    val record = new ProducerRecord(topic, "key", "the end "+new java.util.Date)
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
