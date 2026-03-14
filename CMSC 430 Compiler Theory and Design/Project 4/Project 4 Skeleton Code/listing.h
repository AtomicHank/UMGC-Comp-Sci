// CMSC 430 Compiler Theory and Design
// Project 4

#pragma once

#include <string>

using namespace std;

enum ErrorCategories { LEXICAL, SYNTAX, GENERAL_SEMANTIC, DUPLICATE_IDENTIFIER, UNDECLARED };

void firstLine();
void nextLine();
int lastLine();
void appendError(ErrorCategories errorCategory, string message);
