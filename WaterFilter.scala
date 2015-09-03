// this is the main program..

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
		val parser = new UrlAnalyze
		println( parser.MovieLinkParser(args) )
		// ## 能不能直接调用方法？
		
		val userurl = "http://movie.douban.com/people/57217550/"
		println( parser.UserMoviepageToId(userurl) )
		println( parser.UserIdToHomepage( parser.UserMoviepageToId( userurl ) ) )
		
		println( parser.RatePageNo(0) )
		println( parser.RatePageNo(1) )
		// the rating API is not useful..
		// the rater API is??
	}
}
