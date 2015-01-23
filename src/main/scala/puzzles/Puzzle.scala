package puzzles

import scala.io.Source

case class WordBank(name:String, filename: String){
  lazy val words = Source.fromInputStream(getClass.getResourceAsStream("/" + filename)).getLines().toSeq
}

object WordBank{
  //mentioned here: http://pzxc.com/embed-flash-scrabble-dictionary-text-file
  //source: http://pzxc.com/f/posts/14/f2-33.txt
  val SCRABBLE = WordBank("Scrabble", "scrabble.txt")

  //http://www.freebsd.org/cgi/cvsweb.cgi/src/share/dict/web2?rev=1.12;content-type=text%2Fplain
  val UNIX = WordBank("UNIX", "unix.txt")
  val BANKS = Seq(SCRABBLE, UNIX)
}

object Puzzle {
  val alphabet = "abcdefghijklmnopqrstuvwxyz"

  def isAnagram(s1: String, s2: String) = s1.sorted.toLowerCase == s2.sorted.toLowerCase

  def unscramble(jumble: String, wordBank: Seq[String]): Seq[String] = wordBank.filter(w => isAnagram(jumble, w))

  def isSubset(word: String, letters: String) = word.diff(letters) == ""

  //for scrabble-like situations, return all valid subsets (words) from a string of letters
  def subsets(letters: String, wordBank: Seq[String]): Set[String] = wordBank.filter(word => isSubset(word, letters)).toSet

  def subsetsWithWild(letters: String, numWild: Int, wordBank:Seq[String]): Set[String] = {
    numWild match {
      case 0 => subsets(letters, wordBank)
      case x:Int => alphabet.flatMap(alpha => subsetsWithWild(letters + alpha, x - 1, wordBank)).toSet
    }
  }
}

object PuzzleApp extends App{
  val solutions = Puzzle.subsetsWithWild("x", 1, WordBank.SCRABBLE.words)
  println(solutions)
}