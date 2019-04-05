package Project

object Main {
  def main(args: Array[String]): Unit ={
    ReineProducer
  }
  case class Reine (ID : Int, Etat : Boolean, BatterieFaible : Boolean,  Action : Int,
                    ID_Objectif : Int, ID_Obs : Int, Hemisphere : String, Temp : Double,
                    Alerte : Boolean, Heure : Int, Minute : Int)




}
