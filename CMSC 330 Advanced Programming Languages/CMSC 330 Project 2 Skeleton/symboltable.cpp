// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements symbol table insertion, lookup, duplicate detection, and reset behavior.
// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023

// This file contains the body of the functions contained in The SymbolTable class. The insert function 
// inserts a new variable symbol and its value into the symbol table and the lookUp function returns
// that value of the supplied variable symbol name.

#include <string>
#include <vector>
using namespace std;

#include "symboltable.h"
#include "evaluationerror.h"

void SymbolTable::clear() {
    elements.clear();
}

void SymbolTable::insert(string variable, double value) {
    for (int i = 0; i < elements.size(); i++) {
        if (elements[i].variable == variable)
            throw EvaluationError("Duplicate initialization of variable " + variable);
    }
    const Symbol& symbol = Symbol(variable, value);
    elements.push_back(symbol);
}

double SymbolTable::lookUp(string variable) const {
    for (int i = 0; i < elements.size(); i++)
        if (elements[i].variable == variable)
             return elements[i].value;
    throw EvaluationError("Uninitialized variable " + variable);
}


