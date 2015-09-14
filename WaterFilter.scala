// this is the main program..

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern
import java.net.URL
import java.net.HttpURLConnection

class ContentAnalyze
{
	def MainContentGet(url: String): Array[String] = 
	{
		val uri = new URL(url)
		val conn = uri.openConnection().asInstanceOf[HttpURLConnection]

		conn.setConnectTimeout(100000)
		conn.setReadTimeout(1000000)

		val respcode = conn.getResponseCode()
		var result = new Array[String](0)

		if( respcode == 200 )
		{
			val reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"))
			// have to be UTF-8 but not GBK..
		
			var line = new String
			do
			{
				line = reader.readLine() 
				if(line != "" ) result = result :+ line
			} while( line != "<div class=\"side-info\">" )
			// because </html> is the end of a html file..
	   		
			reader.close
			conn.disconnect
			return result
		}
		else
		{
			conn.disconnect
			throw new Exception("CustomerException: CannotAnalyseUrl")
		}
	}
}


class UrlAnalyze
{
	def MovieLinkParser( args: Array[String] ) =
	//def MovieLinkParser( args: Array[String] ): String =
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
		
		println( "Movie url successfully analyzed, subject number: " + UrlSep(4) )
		UrlSep(4)
	}

	// p is 0, 20, 40..
	def RateRatingNo(p: Int) = "http://movie.douban.com/subject/26235315/collections?start=" + p.toString
	
	// r is 0, 1, 2, 4
	def RatePageNo(r: Int) = "http://movie.douban.com/subject/26235315/collections?start=" + ( r * 20 ).toString

	def UserMoviepageToId(url: String) = url.split("/")(4)

	def UserIdToMoviepage(id: String) = "http://movie.douban.com/people/" + id

	def UserIdToHomepage(id: String) = "http://www.douban.com/people/" + id
}


object WaterFilter
{
	def main(args: Array[String])
	{
		val urlana = new UrlAnalyze
		println( urlana.MovieLinkParser(args) )
		// ## 能不能直接调用方法？
		
		val userurl = "http://movie.douban.com/people/57217550/"
		println( urlana.UserMoviepageToId(userurl) )
		println( urlana.UserIdToHomepage( urlana.UserMoviepageToId( userurl ) ) )
		
		println( urlana.RatePageNo(0) )
		println( urlana.RatePageNo(1) )
		// the rating API is not useful..
		// the rater API is??
		
		val conana = new ContentAnalyze
		val content = conana.MainContentGet( userurl )
	
		for( line <- content)
		println(line)
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
f
f
f
*/
