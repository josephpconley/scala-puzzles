package npr

import scalax.io.Resource
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlAnchor, HtmlDivision, HtmlPage}
import scala.io.Source
import puzzles.Puzzle

/**
 * User: jconley
 * Date: 2/6/14
 */
object Movies {
  def list: Seq[String] = {
    lazy val webClient = new WebClient()
    lazy val opts = webClient.getOptions
    opts.setCssEnabled(false)
    opts.setJavaScriptEnabled(false)

  //    webClient.setRefreshHandler(new WaitingRefreshHandler());

    val suffixes = Seq("_numbers", "_A", "_B", "_C", "_D", "_E", "_F", "_G", "_H", "_I", "_J-K", "_L", "_M", "_N-O", "_P", "_Q-R", "_S", "_T", "_U-W", "_X-Z")
    suffixes.map{ s =>
      val url = "http://en.wikipedia.org/wiki/List_of_films:" + s
      val page: HtmlPage = webClient.getPage(url)
      page.getByXPath("//li/i/a").toArray.map(anchor => anchor.asInstanceOf[HtmlAnchor].asText()).sorted.toSet
    }.flatten
  }

  def write = {
    val output = Resource.fromFile("movies.txt")
    Movies.list.sorted.foreach{ movie =>
      output.write(movie + "\n")
    }
  }

  def read: Seq[String] = Source.fromFile("src\\main\\resources\\movies.txt").getLines.toSeq

  /*
  *
 * http://puzzles.blainesville.com/2013/06/npr-sunday-puzzle-jun-9-2013-name-that.html
 *
 * Q: Name a movie in two words — five letters in each word. Both words start with vowels.
 *    Take one letter in the first word, move it two spaces later in the alphabet, and rearrange the result.
 *    You'll get the second word in the movie's title. What movie is it?

   */

  lazy val vowels = Set('a', 'e', 'i', 'o', 'u')
  def june92013 = read.filter{ movie =>
    val words = movie.toLowerCase.split(" ")
    val firstWord = words.head.toCharArray

    val isQualified = words.length == 2 && words(0).length == 5 && words(1).length == 5 &&
      vowels.contains(words(0).head) && vowels.contains(words(1).head)

    if(isQualified){
      firstWord.indices.map{ i =>
        val shiftedChar = (firstWord(i) + 2).toChar
        val newWord = firstWord.clone()
        newWord.update(i, shiftedChar)

        Puzzle.isAnagram(new String(newWord), words(1))
      }.contains(true)
    }else{
      false
    }
  }.foreach(println)

  /*
  Q: Name a well-known movie of the past — two words, seven letters in total.
  These seven letters can be rearranged to spell the name of an animal plus the sound it makes.
  What animal is it?
   */
  def aug102014 = read.filter { movie =>
    val words = movie.toLowerCase.split(" ")
    val animals = Seq("dogwoof", "pigoink", "catmeow", "owlhoot", "beebuzz", "lambbaa")

    words.length == 2 &&
      ( (words(0).size + words(1).size) == 7) &&
      movie.matches("[a-zA-Z ]*") &&
      animals.exists(a => Puzzle.isAnagram(a, movie.replace(" ", "").toLowerCase))
  }
}

object MovieApp extends App{
  val matches = Movies.aug102014
  matches.foreach(println)
  println(matches.size)
}