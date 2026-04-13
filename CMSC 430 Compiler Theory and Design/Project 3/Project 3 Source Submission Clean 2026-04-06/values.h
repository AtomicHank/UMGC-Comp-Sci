/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Declares the operators, fold directions, and evaluation
   helpers shared by the Project 3 interpreter. */

#pragma once

#include <vector>

typedef char* CharPtr;

enum Operators {
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	REMAINDER,
	EXPONENT,
	NEGATE,
	LESS,
	GREATER,
	LESS_EQUAL,
	GREATER_EQUAL,
	EQUAL,
	NOT_EQUAL,
	AND,
	OR,
	NOT
};

enum Directions { LEFT_FOLD, RIGHT_FOLD };

double evaluateArithmetic(double left, Operators operator_, double right);
double evaluateRelational(double left, Operators operator_, double right);
double evaluateLogical(double left, Operators operator_, double right);
double evaluateUnary(Operators operator_, double value);
double foldLeft(Operators operator_, const std::vector<double>& values);
double foldRight(Operators operator_, const std::vector<double>& values);
