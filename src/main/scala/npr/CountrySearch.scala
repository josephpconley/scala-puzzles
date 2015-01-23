package npr

import scala.io.Source
import puzzles.Puzzle

/**
 * User: jconley
 * Date: 2/5/13
 *
 * NPR puzzle 7/7/13
 *
 * http://puzzles.blainesville.com/2013/07/npr-sunday-puzzle-jul-7-2013-country.html
 *
 * Rearrange the letters of INDIA + BELARUS to name two other countries. What are they?
 */
object CountrySearch extends App{
  val key = "indiabelarus"
  val countries = Source.fromInputStream(getClass.getResourceAsStream("/countries.txt")).getLines().toSeq
  val countryPairs = for(c1 <- countries ; c2 <- countries) yield (c1, c2)
  countryPairs.filter(p => Puzzle.isAnagram(p._1.toLowerCase + p._2.toLowerCase, key)).foreach(println)
}
