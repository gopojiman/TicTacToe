# Theophanis Fox, March 2019
#
# Makefile for TicTacToe program

TTT: *.class
	echo Main-class: TicTacToe > Manifest
	jar cvfm TTT Manifest *.class
	rm Manifest
	chmod +x TTT

*.class: *.java
	javac -Xlint *.java

clean:
	rm *.class


