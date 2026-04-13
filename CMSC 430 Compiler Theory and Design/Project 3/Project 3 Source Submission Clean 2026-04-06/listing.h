/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Declares the listing and error-reporting functions used by
   the Project 3 interpreter. */

#pragma once

#include <string>

using namespace std;

enum ErrorCategories {LEXICAL, SYNTAX, GENERAL_SEMANTIC, DUPLICATE_IDENTIFIER,
	UNDECLARED};

void firstLine();
void nextLine();
int lastLine();
void appendError(ErrorCategories errorCategory, string message);

