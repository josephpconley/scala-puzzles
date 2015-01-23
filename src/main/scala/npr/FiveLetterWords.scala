package npr

import scala.io.{Codec, Source}
import java.nio.charset.CodingErrorAction
import java.io.File

/**
 * User: jconley
 * Date: 10/25/13
 *
 * http://puzzles.blainesville.com/2012/11/npr-sunday-puzzle-nov-18-2012-common.html
 *
 * Think of a familiar five-letter word in two syllables.
 * Change the middle letter to the preceding letter of the alphabet, and you'll get a familiar five-letter word in three syllables.
 * What words are these?
 *
 * A: (alpha,aloha)
 */

object FiveLetterWords extends App{
//  val words = Source.fromInputStream(getClass.getResourceAsStream("/words.csv")).mkString("\n\r")
  implicit val codec = Codec("UTF-8")
  codec.onMalformedInput(CodingErrorAction.REPLACE)
  codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

//  val file = new File("test.txt")
//  println(file.getAbsolutePath)

  val words = Source.fromFile("src\\main\\resources\\words.csv").getLines.toSeq.map(_.split(",")(1))
  val fiveLetterWords = words.filter(_.length == 5)
  val transforms = fiveLetterWords.map{ word =>
    word -> (word.substring(0,2) + (word.charAt(2) - 1).asInstanceOf[Char] + word.substring(3,5))
  }

  val matches = transforms.filter(t => fiveLetterWords.contains(t._2))
  println(matches.size)
  matches.foreach(println)
}

object WordUtil{
  def getWords = {
//      final WebClient webClient = new WebClient();
//      webClient.setRefreshHandler(new WaitingRefreshHandler());
//      webClient.setCssEnabled(false);
//      webClient.setJavaScriptEnabled(false);
//
//      String[] suffixes = new String[]{"1-1000", "1001-2000", "2001-3000", "3001-4000", "4001-5000", "5001-6000", "6001-7000", "7001-8000", "8001-9000", "9001-10000",
//        "10001-12000", "12001-14000", "14001-16000", "16001-18000", "18001-20000", "20001-22000", "22001-24000", "24001-26000", "26001-28000", "28001-30000",
//        "30001-32000", "32001-34000", "34001-36000", "36001-38000", "38001-40000"};
//
//      List<String> words = new ArrayList<String>();
//      for(String s : suffixes){
//        String url = "http://en.wiktionary.org/wiki/Wiktionary:Frequency_lists/TV/2006/" + s;
//        HtmlPage page = (HtmlPage) webClient.getPage(url);
//        HtmlTable table = (HtmlTable)page.getByXPath("//table").get(0);
//        for(HtmlTableRow tr : table.getBodies().get(0).getRows()){
//          List<String> row = new ArrayList<String>();
//          for(HtmlTableCell td : tr.getCells()){
//            row.add(td.asText());
//          }
//          words.add(StringUtils.join(row, ","));
//        }
//      }
//
//      File f = new File("words.csv");
//      IOUtils.writeLines(words, null, new FileOutputStream(f));
  }
}