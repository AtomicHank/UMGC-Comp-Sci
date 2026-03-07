// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements token parsing functions for variable names and numeric literals.
// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023

// This file contains the body of the function parseName. That function consumes all alphanumeric 
// characters until the next whitespace and returns the name that those characters form.

#include <cctype>
#include <sstream>
#include <string>
using namespace std;

#include "parse.h"

string parseName(stringstream& in) {
    char current;
    string name = "";

    in >> ws;
    if (isalpha(in.peek())) {
        in >> current;
        name += current;
    }
    while (isalnum(in.peek()) || in.peek() == '_') {
        in >> current;
        name += current;
    }
    return name;
}

double parseLiteral(stringstream& in) {
    double value;
    in >> value;
    return value;
}

