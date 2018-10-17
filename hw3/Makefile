all: compile link

compile: 
	gcc -c src/simlib.c src/invsim.c

link: 
	mv simlib.o src/simlib.o 
	mv invsim.o src/invsim.o
	gcc src/simlib.o src/invsim.o -o src/invsim -lm

clean: 
	rm src/simlib.o src/invsim.o src/invsim

run:
	src/./invsim