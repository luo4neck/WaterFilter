// this is the main program..


object WaterFilter
{
	def MovieLinkParser( args: Array[String] )
	{
		if ( args.length != 1 )
		{
			println("Wrong input! Please input like:")
			println("$ scala WaterFilter http://movie.douban.com/subject/1234567/")
			// exception..
		}
		else
		{
			println( "going to get a subject number" )
		}
	}


	def main(args: Array[String])
	{
		println("this is a scala test")
		
		MovieLinkParser(args)
		// parse the url and if there is wrong input; return a useful url.

		// the rating API is not useful..
		// the rater API is??
		

	}
}
