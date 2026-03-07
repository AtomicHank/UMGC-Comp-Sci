// CMSC 430 Compiler Theory and Design
// Project 1
// Student: Joe Merrill
// Date: 2026-03-07
// Description: Function prototypes for the compilation listing module.

#include <string>

enum ErrorCategories {LEXICAL, SYNTAX, GENERAL_SEMANTIC, DUPLICATE_IDENTIFIER,
	UNDECLARED};

void firstLine();
void nextLine();
int lastLine();
void appendError(ErrorCategories errorCategory, std::string message);

