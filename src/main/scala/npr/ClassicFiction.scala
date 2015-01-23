package npr

import scalax.io.Resource
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlSpan, HtmlAnchor, HtmlDivision, HtmlPage}
import java.util
import scala.collection.JavaConversions._
import scala.io.Source

/**
 * User: jconley
 * Date: 2/10/14
 *
 * http://puzzles.blainesville.com/2014/02/npr-sunday-puzzle-feb-9-2014-studying.html
 *
 * Q: Name a title character from a classic work of fiction, in 8 letters.
 * Change the third letter to an M. The result will be two consecutive words naming parts of the human body.
 * Who is the character, and what parts of the body are these?
 *
 * A: Gulliver
 * Gum
 * Liver
 */
object ClassicFiction {
  def list: Seq[String] = {
    lazy val webClient = new WebClient()
    lazy val opts = webClient.getOptions
    opts.setCssEnabled(false)
    opts.setJavaScriptEnabled(false)

  //    webClient.setRefreshHandler(new WaitingRefreshHandler());

    (1 to 113).map { p =>
      val url = "http://www.goodreads.com/list/show/264.Books_That_Everyone_Should_Read_At_Least_Once?page=" + p
      val page: HtmlPage = webClient.getPage(url)
      page.getByXPath("//a[@class='bookTitle']").asInstanceOf[util.ArrayList[HtmlAnchor]].toList.map{ a =>
        a.getFirstChild.getTextContent
      }
    }.flatten
  }
}

object ClassicFictionApp extends App{
//  val titles = ClassicFiction.list
//  val output = Resource.fromFile("fiction.txt")
//  titles.sorted.foreach{ book =>
//    output.write(book + "\n")
//  }

  def validWord(word:String) = (word.length == 8 || (word.length == 10 && word.endsWith("'s"))) && word.toLowerCase.toCharArray.apply(2) != 'm'

  val validWords = Source.fromFile("src\\main\\resources\\fiction.txt").getLines.toSeq.filter{ title =>
//    //give me any title with an eight letter word that does not have punctuation
    title.split(" ").exists(word => validWord(word))
  }.map{ title =>
    title.split(" ").filter(word => validWord(word)).map(_.toLowerCase)
  }.flatten.toSet[String].toSeq.map { word =>
    //replace the third letter with the letter m
    val newWord = word.toCharArray
    newWord.update(2, 'm')
    word -> new String(newWord)
  }

  validWords.sorted.foreach(println)
  println(validWords.size)
}