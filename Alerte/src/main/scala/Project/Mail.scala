package Project

import org.apache.commons.mail._

object Mail extends App {
  def send_email(Id: Int, Heure : Int, Minute : Int): Unit ={
    println("Envoi de mail en cours.")
    val email = new SimpleEmail()
    email.setHostName("smtp.googlemail.com")
    email.setSmtpPort(465)
    email.setAuthenticator(new DefaultAuthenticator("testabeillesscala@gmail.com", "abeilles"))
    email.setSSLOnConnect(true)
    email.setFrom("testabeillesscala@gmail.com")
    email.setSubject("Alerte pour appareil : " + Id.toString)
    email.setMsg("Alerte nécessitant intervention humaine\n" +
      "Abeille numero : " + Id.toString + " Heure :" + Heure.toString + " : " + Minute.toString)
    email.addTo("grayme9725@gmail.com")
    email.send()
    println("Envoi de mail terminé")
  }

}