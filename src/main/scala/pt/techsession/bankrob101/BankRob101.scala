package pt.techsession.bankrob101

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object BankRob101 extends App {

  def openSafeLock(thief: Thief, victim: String): Future[Loot] = {
    unlockTheDoor //primeiro
      .flatMap(isOpen =>
                //em paralelo
                figureOutSafetyBoxNumber(victim)
                  .zip(hackSecretPin(victim))
      )
      .flatMap(x => openSafeLock(x._1, x._2) )
                  //x._1 -> primeiro valor do tuplo: safetyBoxNumber
                  //x._2 -> segundo valor do tuplo: pin
      .recoverWith({
                case _ =>  {
                  val name = thief.getThiefName
                  println(s"--> Ups... Police caught a thief named $name robbing the bank!")
                  Future(Loot.BAD)
                }
      })//Loot.BAD
  }

  //mapeando as acções com Futuros para uma aplicação mais idiomática
  def unlockTheDoor : Future[Boolean] = Future(Actions.unlockTheDoor)

  def figureOutSafetyBoxNumber(victim: String): Future[String] = Future(Actions.figureOutSafetyBoxNumber(victim))

  def hackSecretPin(victim: String): Future[Int] = Future(Actions.hackSecretPin(victim))

  def openSafeLock(safetyBoxNumber: String,pin: Int) : Future[Loot] = Future(Actions.openSafeLock(safetyBoxNumber, pin))

  //main execution of app:
  Await.ready(openSafeLock(Thief.PETE, "António Madeira"), 20 seconds)
  println("--------------------------------------------------")
  Await.ready(openSafeLock(Thief.LORA, "Tiago"), 20 seconds)
  println("--------------------------------------------------")
  Await.ready(openSafeLock(Thief.WILL, "Douglas"), 20 seconds)
  println("--------------------------------------------------")
  Await.ready(openSafeLock(Thief.WILL, "Harry Potter"), 20 seconds)//expect failure here
}
