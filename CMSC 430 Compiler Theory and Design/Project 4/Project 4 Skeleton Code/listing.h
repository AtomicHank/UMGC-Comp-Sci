/*
 * Student: Joe Merrill
 * Course: CMSC 430 Compiler Theory and Design
 * Project: Project 4
 * Date: 2026-04-06
 * File: listing.h
 * Description: Declares the listing and error-reporting helpers that format
 * compiler output with lexical, syntax, and semantic diagnostics.
 */

#pragma once

#include <string>

using namespace std;

enum ErrorCategories { LEXICAL, SYNTAX, GENERAL_SEMANTIC, DUPLICATE_IDENTIFIER, UNDECLARED };

void firstLine();
void nextLine();
int lastLine();
void appendError(ErrorCategories errorCategory, string message);
