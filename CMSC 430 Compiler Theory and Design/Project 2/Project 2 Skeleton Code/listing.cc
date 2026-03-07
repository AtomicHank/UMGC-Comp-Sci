// CMSC 430 Compiler Theory and Design
// Project 2
// Student: Joe Merrill
// Date: March 7, 2026
// File: listing.cc
// Description: Implements the compilation listing and categorized error totals.

#include <cstdio>
#include <string>

#include "listing.h"

using namespace std;

static int lineNumber;
static string errors;
static int lexicalErrors;
static int syntaxErrors;
static int semanticErrors;

static void displayErrors();

void firstLine() {
	lineNumber = 1;
	errors.clear();
	lexicalErrors = 0;
	syntaxErrors = 0;
	semanticErrors = 0;
	printf("\n%4d  ", lineNumber);
}

void nextLine() {
	displayErrors();
	lineNumber++;
	printf("%4d  ", lineNumber);
}

int lastLine() {
	printf("\r");
	displayErrors();
	printf("\n");
	if (lexicalErrors == 0 && syntaxErrors == 0 && semanticErrors == 0)
		printf("Compiled Successfully\n");
	else {
		printf("Lexical Errors %d\n", lexicalErrors);
		printf("Syntax Errors %d\n", syntaxErrors);
		printf("Semantic Errors %d\n", semanticErrors);
	}
	return lexicalErrors + syntaxErrors + semanticErrors;
}

void appendError(ErrorCategories errorCategory, const string& message) {
	string prefix;
	switch (errorCategory) {
		case LEXICAL:
			prefix = "Lexical Error, Invalid Character ";
			lexicalErrors++;
			break;
		case SYNTAX:
			prefix = "Syntax Error, ";
			syntaxErrors++;
			break;
		default:
			prefix = "Semantic Error, ";
			semanticErrors++;
			break;
	}

	if (!errors.empty())
		errors += "\n";
	errors += prefix + message;
}

void displayErrors() {
	if (!errors.empty())
		printf("%s\n", errors.c_str());
	errors.clear();
}
