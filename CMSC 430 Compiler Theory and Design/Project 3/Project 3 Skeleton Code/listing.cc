/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Implements the compiler listing output and error reporting
   helpers used during parsing and interpretation. */

#include <cstdio>
#include <string>
#include <vector>

using namespace std;

#include "listing.h"

static int lineNumber;
static int totalErrors = 0;
static vector<string> errors;

static void displayErrors();

void firstLine()
{
	lineNumber = 1;
	totalErrors = 0;
	errors.clear();
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
	printf("     \n");
	return totalErrors;
}
    
void appendError(ErrorCategories errorCategory, string message)
{
	string messages[] = { "Lexical Error, Invalid Character ", "",
		"Semantic Error, ", "Semantic Error, Duplicate ",
		"Semantic Error, Undeclared " };

	errors.push_back(messages[errorCategory] + message);
	totalErrors++;
}

void displayErrors()
{
	for (size_t index = 0; index < errors.size(); index++)
		printf("%s\n", errors[index].c_str());
	errors.clear();
}
