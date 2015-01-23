import org.specs2.mutable._
import puzzles.{WordBank, Puzzle}
import scala.io.Source

/**
 * User: joe
 * Date: 12/6/13
 */
class AnagramSpec extends Specification {

  "Puzzle" should {
    "return matches" in {
      val bank = Seq("one", "two", "three")

      Puzzle.unscramble("eno", bank)(0) must beEqualTo("one")
    }

    "jumble" in {
      val hand = "adahe"
      Puzzle.unscramble(hand, WordBank.UNIX.words).foreach(println)

      2 + 2 must beEqualTo(4)
    }

    "return matches from unix list" in {
      //eon, neo, one
      Puzzle.unscramble("eno", WordBank.UNIX.words).size must beEqualTo(3)
    }

    "scrabble tester" in {
      //random scrabble hand
      val alphabet = "abcdefghijklmnopqrstuvwxyz"
      val hand = Stream.continually(util.Random.nextInt(alphabet.size)).map(alphabet).take(7).mkString

//      Puzzle.matches(hand, Source.fromURL(SCRABBLE.url).getLines().toSeq).foreach(println)

      2 + 2 must beEqualTo(4)
    }

    "scrabble with wild tester" in {
      val hand = "ABCDEF"
//      Puzzle.matchesWithWild(hand, 1, Source.fromURL(SCRABBLE.url).getLines().toSeq).foreach(println)

      2 + 2 must beEqualTo(4)
    }
  }
}
