# Student: Joe Merrill
# Course: CMSC 430 Compiler Theory and Design
# Project: Project 4
# Date: 2026-04-06
# File: makefile
# Description: Rebuilds the Project 4 compiler from the Flex, Bison, and
# handwritten support files.

CXX ?= g++
BISON ?= win_bison
FLEX ?= win_flex

compile: scanner.o parser.o listing.o types.o
	$(CXX) -o compile scanner.o parser.o listing.o types.o

scanner.o: lex.yy.c types.h listing.h parser.tab.h
	$(CXX) -c lex.yy.c -o scanner.o

lex.yy.c: scanner.l parser.tab.h
	$(FLEX) scanner.l

parser.o: parser.tab.c types.h listing.h symbols.h
	$(CXX) -c parser.tab.c -o parser.o

parser.tab.c parser.tab.h: parser.y
	$(BISON) -d -v parser.y

listing.o: listing.cc listing.h
	$(CXX) -c listing.cc -o listing.o

types.o: types.cc types.h
	$(CXX) -c types.cc -o types.o

clean:
	del /Q compile.exe scanner.o parser.o listing.o types.o lex.yy.c parser.tab.c parser.tab.h parser.output 2>nul
