// CMSC 430 Compiler Theory and Design
// Project 2
// Student: Joe Merrill
// Date: March 7, 2026
// File: listing.h
// Description: Declares the compilation listing and error-reporting helpers.

#pragma once

#include <string>

enum ErrorCategories {
	LEXICAL,
	SYNTAX,
	GENERAL_SEMANTIC,
	DUPLICATE_IDENTIFIER,
	UNDECLARED
};

void firstLine();
void nextLine();
int lastLine();
void appendError(ErrorCategories errorCategory, const std::string& message);

