all: *.scala
	fsc *.scala

clean:
	rm *.class

test: all
	scala WaterFilter http://movie.douban.com/subject/3338862/
	echo "\n\n" 
	scala WaterFilter http://movie.douban.com/subject/3338862/ fuck

#all: main.cpp class_Star.hpp store.hpp
#	g++ -Wall -c -fPIC main.cpp -o main.o -I /usr/include/python2.7/
