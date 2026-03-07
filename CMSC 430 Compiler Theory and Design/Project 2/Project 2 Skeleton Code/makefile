# CMSC 430 Compiler Theory and Design
# Project 2
# Student: Joe Merrill
# Date: March 7, 2026
# File: makefile
# Description: Builds the scanner, parser, listing module, and compiler executable.

CXX = g++
BISON ?= win_bison
FLEX ?= win_flex

compile: scanner.o parser.o listing.o
	$(CXX) -o compile scanner.o parser.o listing.o

scanner.o: lex.yy.c listing.h parser.tab.h
	$(CXX) -c lex.yy.c -o scanner.o

lex.yy.c: scanner.l parser.tab.h
	$(FLEX) scanner.l

parser.o: parser.tab.c listing.h
	$(CXX) -c parser.tab.c -o parser.o

parser.tab.c parser.tab.h: parser.y
	$(BISON) -d -v parser.y

listing.o: listing.cc listing.h
	$(CXX) -c listing.cc -o listing.o
