package pt.techsession.bankrob101

//object Actions extends App {
//  System.out.println("Hello, world!")
//}

object Actions {

  def DELAY_MS = 1000L

  import java.util.concurrent.ThreadLocalRandom


  def unlockTheDoor: Boolean = {
    System.out.println("Forcing the door...")
    delay(2000)
    System.out.println("Door unlocked!")
    true
  }

  def hackSecretPin(personName: String): Int = {
    System.out.println(s"Hacking the pin of $personName")
    delay
    val pin = (personName.hashCode % 1000) + 1000
    System.out.println(s"Pin hacked: $pin")
    pin
  }

  def figureOutSafetyBoxNumber(personName: String): String = {
    System.out.println(s"Figuring out the safety box number of $personName")
    delay
    val lock = "A" + ThreadLocalRandom.current.nextInt(100)

    if(personName.contains("Tiago Madeira"))
      throw new RuntimeException("Tiago Madeira")

    System.out.println(s"Got the safety box number: $lock")
    lock
  }

  def openSafeLock(safetyBoxNumber: String, pin: Int): Loot = {
    System.out.println(s"Opening the safe lock $safetyBoxNumber using the pin $pin")
    delay
    System.out.println("--> SUCCESS! Safety Box opened!")
    Loot.randomLoot
  }


  private def delay(): Unit = {
    delay(DELAY_MS + Math.round(10*Math.random()))
  }

  private def delay(ms: Long): Unit = {
    try
      Thread.sleep(ms)
    catch {
      case e: InterruptedException =>
        throw new RuntimeException(e)
    }
  }
}
