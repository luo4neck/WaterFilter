all: *.scala
	fsc *.scala

clean:
	rm *.class

test1: all
	scala WaterFilter http://movie.douban.com/subject/3338862/

test2: all
	scala WaterFilter http://movie.douban.com/subject/3338862asd/
#all: main.cpp class_Star.hpp store.hpp
#	g++ -Wall -c -fPIC main.cpp -o main.o -I /usr/include/python2.7/
