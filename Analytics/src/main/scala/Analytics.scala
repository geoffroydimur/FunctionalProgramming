import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.countDistinct
import org.apache.log4j.Logger
import org.apache.log4j.Level
object Analytics {


  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  val ss = SparkSession.builder()
    .appName("Analytics")
    .master("local")
    .getOrCreate()

  val df = ss.read.json("/Users/geoffroydimur/Desktop/Efrei/S8/Functional Programming/HDFS/HDFS.txt")
  df.show
  println()
  println("----------------------------------------------------")
  println("Nombre d'abeilles :\t" + df.count.toString)
  println()
  println("----------------------------------------------------")
  println("Proportion de batteries faible : ")
  df.groupBy("Batteriefaible").count.show
  println()
  println("----------------------------------------------------")
  println("Analyse Batterie faibles selon l'hemisphere : ")
  df.groupBy("Hemisphere").count.show
  println()
  println("----------------------------------------------------")
  println("Analyse Batterie faibles selon la température : ")
  println("Environnement Chaud (22 +) :\t\t" + df.filter(df("Temp") > 22).count.toString)
  println("Environemment Tempéré (10+ - 22) :\t" + df.filter(df("Temp") > 10 && df("Temp") <=22).count.toString)
  println("Environnement Froid (-10) : +\t\t" + df.filter(df("Temp") <=10).count.toString)
  println()
  println("----------------------------------------------------")
  println("Analyse Batterie faibles selon la période de la journée: ")
  println("Matin (5+-12) :\t\t" + df.filter(df("Heure") > 5 && df("Heure") <=12).count.toString)
  println("Milieu de journée (12+ - 18) :\t" + df.filter(df("Heure") > 12 && df("Heure") <=18).count.toString)
  println("Soir (18+ - 22) :\t\t" + df.filter(df("Heure") > 18 && df("Heure") <=22).count.toString)
  println("Nuit (22+ - 5):\t\t" + df.filter(df("Heure") > 22 || df("Heure") <=5).count.toString)
  println()

}
