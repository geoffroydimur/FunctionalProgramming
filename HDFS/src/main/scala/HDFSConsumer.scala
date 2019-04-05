package scala

import scala.Main.Reine
import java.util.Properties
import java.util

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._
import play.api.libs.json._
import java.io._





object HDFSConsumer {
  implicit val reineReads = Json.reads[Reine]
  implicit val reineWrites = Json.writes[Reine]
  val propsConsummer: Properties = new Properties()
  propsConsummer.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  propsConsummer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  propsConsummer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  propsConsummer.put(ConsumerConfig.GROUP_ID_CONFIG, "HDFS")

  val consumer = new KafkaConsumer[String, String](propsConsummer)
  consumer.subscribe(util.Collections.singletonList("atopic"))

  while (true) {
    for (read <- consumer.poll(100).asScala) {
      val writer = new PrintWriter(new FileOutputStream(new File("HDFS.txt"),true))
      writer.write(Json.stringify(Json.parse(read.value())).concat("\n"))
      writer.close()
    }
  }
}
