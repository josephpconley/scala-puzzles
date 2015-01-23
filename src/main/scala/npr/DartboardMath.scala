package npr

/**
 * User: jconley
 * Date: 8/19/2014
 */
object DartboardMath extends App{
  for{
    a <- 0 to 6
    b <- 0 to 5
    c <- 0 to 4
    d <- 0 to 4
    e <- 0 to 2
    f <- 0 to 2
  } yield {
    val sum = 16*a + 17*b + 23*c + 24*d + 39*e + 40*f
    if(sum == 100) println(a + " " + b + " " + c + " " + d + " " + e + " " + f)
  }
}
