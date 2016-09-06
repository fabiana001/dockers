//package kafka
//
//import java.util
//import java.util.Properties
//import scala.collection.JavaConverters._
//import scala.collection.JavaConversions._
//import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
//
///**
//  * Created by cgnal on 05/09/16.
//  */
//object MessageConsumer {
//
//  def run() = {
//    val props = new Properties()
//
//    props.put("bootstrap.servers", "localhost:9092")
//    props.put("group.id", "test")
//    props.put("enable.auto.commit", "true")
//    props.put("auto.commit.interval.ms", "1000")
//    props.put("session.timeout.ms", "30000")
//    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    val consumer = new KafkaConsumer(props)
//    consumer.subscribe(util.Arrays.asList("pluto", "pippo"))
//
//    while (true) {
//      val records = consumer.poll(100)//.asInstanceOf[ConsumerRecord[String, String]]
//      val pippo = records.iterator().asScala.map(el => el.toString)
//    }
//  }
//}
//object MessageConsumerMain {
//  def main(args: Array[String]): Unit = {
//    MessageConsumer.run()
//  }
//}
//
