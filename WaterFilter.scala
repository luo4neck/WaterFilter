// this is the main program..


object WaterFilter
{
	def MovieLinkParser( args: Array[String] )
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
			println( "Going to analyze movie subject: " + UrlSep(4) )
		}
		else
		{
			println("Wrong input! Please input like:")
			println("$ scala WaterFilter http://movie.douban.com/subject/1234567/")
			throw new Exception("CustomerException: WrongUrlInput")
		}

		UrlSep(4)
	}


	def main(args: Array[String])
	{
		//println("this is a scala test")
		
		MovieLinkParser(args)

		// the rating API is not useful..
		// the rater API is??
		

	}
}
