package Project

import java.util.{Properties}

import Project.Main.Reine
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import play.api.libs.json._

object ReineProducer {
  implicit val reineReads = Json.reads[Reine]
  implicit val reineWrites = Json.writes[Reine]
  val propsProducer: Properties = new Properties()
  propsProducer.put("bootstrap.servers", "localhost:9092")
  propsProducer.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  propsProducer.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](propsProducer)
  producer.send(new ProducerRecord("atopic","1",Json.stringify(Json.toJson(Reine(1,false,true,1,1,2,"Nord",25,false, 12, 21)))))
  producer.send(new ProducerRecord("atopic","2",Json.stringify(Json.toJson(Reine(2,false,true,1,1,2,"Nord",25.3,false, 12, 22)))))
  producer.send(new ProducerRecord("atopic","3",Json.stringify(Json.toJson(Reine(3,false,true,1,1,2,"Nord",7,false, 6, 54)))))
  producer.send(new ProducerRecord("atopic","4",Json.stringify(Json.toJson(Reine(4,false,false,1,1,2,"Nord",5,false, 1, 10)))))
  producer.send(new ProducerRecord("atopic","5",Json.stringify(Json.toJson(Reine(5,false,false,1,1,2,"Nord",20,false, 13, 21)))))
  producer.send(new ProducerRecord("atopic","6",Json.stringify(Json.toJson(Reine(6,false,false,1,1,2,"Nord",18,true, 3, 0)))))

  producer.send(new ProducerRecord("atopic","7",Json.stringify(Json.toJson(Reine(7,false,true,1,1,2,"Sud",32,false, 12, 21)))))
  producer.send(new ProducerRecord("atopic","8",Json.stringify(Json.toJson(Reine(8,false,true,1,1,2,"Sud",21,false, 12, 22)))))
  producer.send(new ProducerRecord("atopic","9",Json.stringify(Json.toJson(Reine(9,false,true,1,1,2,"Sud",25.4,false, 6, 54)))))
  producer.send(new ProducerRecord("atopic","10",Json.stringify(Json.toJson(Reine(10,false,false,1,1,2,"Sud",10,false, 1, 10)))))
  producer.send(new ProducerRecord("atopic","11",Json.stringify(Json.toJson(Reine(11,false,false,1,1,2,"Sud",12,false, 13, 21)))))
  println("Fin de l'envoi")
  producer.close()
}


