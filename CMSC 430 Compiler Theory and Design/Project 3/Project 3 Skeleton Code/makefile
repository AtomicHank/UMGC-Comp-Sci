# CMSC 430 Compiler Theory and Design
# Project 3
# Author: Joseph Merrill
# Date: 2026-04-06
# Description: Builds the Project 3 interpreter from the Flex scanner, Bison
# grammar, and supporting C++ source files.

CXX ?= g++
BISON ?= win_bison
FLEX ?= win_flex

compile: scanner.o parser.o listing.o values.o
	$(CXX) -o compile scanner.o parser.o listing.o values.o

scanner.o: lex.yy.c values.h listing.h parser.tab.h
	$(CXX) -c lex.yy.c -o scanner.o

lex.yy.c: scanner.l parser.tab.h
	$(FLEX) scanner.l

parser.o: parser.tab.c values.h listing.h symbols.h
	$(CXX) -c parser.tab.c -o parser.o

parser.tab.c parser.tab.h: parser.y
	$(BISON) -d -v parser.y

listing.o: listing.cc listing.h
	$(CXX) -c listing.cc -o listing.o

values.o: values.cc values.h
	$(CXX) -c values.cc -o values.o

clean:
	del /Q compile.exe scanner.o parser.o listing.o values.o lex.yy.c parser.tab.c parser.tab.h parser.output 2>nul
