// this is the main program..

class Parser
{
	def MovieLinkParser( args: Array[String] ): String =
	{
		def isAllDigits(x: String) = x forall Character.isDigit
		val UrlSep = args(0).split("/")
		
		if  (
			 args.length == 1 && UrlSep.length == 5 && 
			 UrlSep(0) == "http:" && UrlSep(1) == "" && 
			 UrlSep(2) == "movie.douban.com" && 
			 UrlSep(3) == "subject" && 
			 isAllDigits( UrlSep(4) ) 
			) 
		{
			println( "Movie url successfully analyzed, subject number: " + UrlSep(4) )
		}
		else // throw new exception..
		{
			println("Wrong input! Please input like:")
			println("$ scala WaterFilter http://movie.douban.com/subject/1234567/")
			throw new Exception("CustomerException: WrongUrlInput")
		}
		UrlSep(4)
	}

	def UserUrlToId(url: String): String = 
		url.split("/")(4)
}

object WaterFilter
{
	def main(args: Array[String])
	{
		val parser = new Parser
		println( parser.MovieLinkParser(args) )
		// ## 能不能直接调用方法？
		
		val userurl = "http://movie.douban.com/people/57217550/"
		println( parser.UserUrlToId(userurl) )

		//while(true)
		{
		
		}

		// the rating API is not useful..
		// the rater API is??
		
	}
}
