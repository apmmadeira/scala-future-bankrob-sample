package pt.techsession.bankrob101

object Thief {
  var PETE = new Thief("Pete")
  var LORA = new Thief("Lora")
  var WILL = new Thief("Will")
}

class Thief(name: String) {

  def getThiefName: String = name

  def handleLoot(loot: Loot): String = {
    loot match {
      case Loot.NICE =>
        return name + " [When getting the loot '" + loot.getMsg + "'] : Wooooowww amazing!!"
      case Loot.NOT_BAD =>
        return name + " [When getting the loot '" + loot.getMsg + "'] : Hmmm what a disappointment! Now I need to buy the Han Solo figure..."
      case Loot.BAD =>
        return name + " [When getting the loot '" + loot.getMsg + "'] : Grrrrr :("
    }
    throw new IllegalArgumentException("Unexpected Loot: " + loot)
  }

}
