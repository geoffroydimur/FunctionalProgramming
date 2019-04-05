package Project

import Project.Main.Reine
import java.util.Properties
import java.util

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._
import play.api.libs.json._





object AlertConsumer {
  implicit val reineReads = Json.reads[Reine]
  implicit val reineWrites = Json.writes[Reine]
  def readReine(jsonReine: JsValue) = {
    jsonReine.as[Reine]
  }
  val propsConsummer: Properties = new Properties()
  propsConsummer.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  propsConsummer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  propsConsummer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  propsConsummer.put(ConsumerConfig.GROUP_ID_CONFIG, "Alert")
  val consumer = new KafkaConsumer[String, String](propsConsummer)
  consumer.subscribe(util.Collections.singletonList("atopic"))


  while (true) {
    for (read <- consumer.poll(100).asScala) {
      println("Objet lu :")
      println(read)
      val key = read.key()
      val abeille = readReine(Json.parse(read.value()))
      println()
      if (abeille.Alerte){
        Mail.send_email(abeille.ID, abeille.Heure, abeille.Minute)
      }
    }
  }
}
