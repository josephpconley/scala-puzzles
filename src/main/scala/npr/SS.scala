package npr

import puzzles.WordBank

object SS extends App{
  val doubleS = WordBank.SCRABBLE.words.filter(word => word.matches(".*ss.*"))

  val pairs = doubleS.map(word => word -> word.replace("ss", "")).seq
  val validWords = pairs.filter(t => WordBank.SCRABBLE.words.contains(t._2))
  println(validWords.size)
  validWords.foreach(println)
}