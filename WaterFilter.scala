// this is the main program..

import java.util.regex.Pattern
import scala.io.Source
import scala.collection.mutable._

class ContentAnalyze
{
	def ContentGet(url: String): Array[String] = 
	{
		val con = Source.fromURL(url).mkString.split("\n")
	/*	for
		{
			 line <- con 
			 if(line != "")
		} yield line */
		con
	}
}


class UrlAnalyze
{
	var MovieId = ""

	def MovieLinkParser( args: Array[String] ) =
	// returns id of the movie..
	{
		def isAllDigits(x: String) = x forall Character.isDigit
		val UrlSep = args(0).split("/")
		
		if( args.length != 1 || UrlSep.length != 5 || 
			UrlSep(0) != "http:" || UrlSep(1) != "" || 
			UrlSep(2) != "movie.douban.com" || 
			UrlSep(3) != "subject" || !isAllDigits( UrlSep(4) ) ) 
		{
			println("Wrong input! Please input like:")
			println("$ scala WaterFilter http://movie.douban.com/subject/1234567/")
			throw new Exception("CustomerException: WrongUrlInput")
		}
		
		MovieId = UrlSep(4)
		println( "Movie url successfully analyzed, subject number: " + UrlSep(4) )
	}

	// p is 0, 20, 40..
	def RateRatingNo(p: Int) = "http://movie.douban.com/subject/" + MovieId + "/collections?start=" + p.toString
	
	// r is 0, 1, 2, 4
	def RatePageNo(r: Int) = "http://movie.douban.com/subject/" + MovieId + "/collections?start=" + ( r * 20 ).toString

	def UserMoviepageToId(url: String) = url.split("/")(4)

	def UserIdToMoviepage(id: String) = "http://movie.douban.com/people/" + id

	def UserIdToHomepage(id: String) = "http://www.douban.com/people/" + id
}


object WaterFilter
{
	def main(args: Array[String])
	{
		val urlana = new UrlAnalyze
		//println( urlana.MovieLinkParser(args) )
		// ## 能不能直接调用方法？
		
		urlana.MovieLinkParser(args) 
		
		// the rating API is not useful..
		// the rater API is??
		
		val conana = new ContentAnalyze

		//val AllRates = new Array[ HashSet[String] ] (5)
		//could be used in intpre, but not in code..

		//val AllRates = (star, star, star, star, star, star)
		//val AllRates = Tuple( HashSet[String], HashSet[String], HashSet[String], HashSet[String], HashSet[String], HashSet[String] )
		//AllRates._1 += "ff"
		

		//0 is no-rating, 1-5 is 1 star-5 star
		{
			val content = conana.ContentGet( urlana.RatePageNo(0) ) 
			content.foreach(println)

			println(content.length)
		}
	}
}


/*
scala> val u = "f"
u: String = f

scala> val all = u.trim.r.findAllIn("fsfsfs")
all: scala.util.matching.Regex.MatchIterator = non-empty iterator

scala> all.for
forall    foreach   

scala> all.foreach(println)
*/
