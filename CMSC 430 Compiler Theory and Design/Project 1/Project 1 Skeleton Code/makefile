## CMSC 430 Compiler Theory and Design
## Project 1
## Student: Joe Merrill
## Date: 2026-03-07
## Description: Builds the scanner using flex or win_flex when available, otherwise uses scanner.cpp.

FLEX_FOUND := $(shell where flex >NUL 2>NUL && echo yes)
WIN_FLEX_FOUND := $(shell where win_flex >NUL 2>NUL && echo yes)

ifeq ($(FLEX_FOUND),yes)
FLEX_CMD = flex
SCANNER_SRC = scanner.c
else ifeq ($(WIN_FLEX_FOUND),yes)
FLEX_CMD = win_flex
SCANNER_SRC = scanner.c
else
FLEX_CMD =
SCANNER_SRC = scanner.cpp
endif

compile: scanner.o listing.o
	g++ -std=c++17 -o compile scanner.o listing.o

scanner.o: $(SCANNER_SRC) listing.h tokens.h scanner.l
	g++ -std=c++17 -c $(SCANNER_SRC) -o scanner.o

scanner.c: scanner.l
	$(FLEX_CMD) -o scanner.c scanner.l

listing.o: listing.cc listing.h
	g++ -std=c++17 -c listing.cc
