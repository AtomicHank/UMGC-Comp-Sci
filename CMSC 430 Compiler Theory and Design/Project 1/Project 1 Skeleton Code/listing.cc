// CMSC 430 Compiler Theory and Design
// Project 1
// Student: Joe Merrill
// Date: 2026-03-07
// Description: Bodies of the functions that produce the compilation listing.

#include <cstdio>
#include <string>
#include <vector>

using namespace std;

#include "listing.h"

static int lineNumber;
static vector<string> errors;
static int lexicalErrors = 0;
static int syntaxErrors = 0;
static int semanticErrors = 0;

static void displayErrors();

void firstLine()
{
	lineNumber = 1;
	errors.clear();
	lexicalErrors = 0;
	syntaxErrors = 0;
	semanticErrors = 0;
	printf("\n%4d  ",lineNumber);
}

void nextLine()
{
	displayErrors();
	lineNumber++;
	printf("%4d  ",lineNumber);
}

int lastLine()
{
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
    
void appendError(ErrorCategories errorCategory, string message)
{
	string messages[] = { "Lexical Error, Invalid Character ", "",
		"Semantic Error, ", "Semantic Error, Duplicate ",
		"Semantic Error, Undeclared " };

	errors.push_back(messages[errorCategory] + message);
	if (errorCategory == LEXICAL)
		lexicalErrors++;
	else if (errorCategory == SYNTAX)
		syntaxErrors++;
	else
		semanticErrors++;
}

void displayErrors()
{
	for (const string& error : errors)
		printf("%s\n", error.c_str());
	errors.clear();
}
