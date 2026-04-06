/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Implements arithmetic, relational, logical, unary, and fold
   evaluation helpers used by the Project 3 interpreter. */

#include <cmath>
#include <vector>

using namespace std;

#include "values.h"

double evaluateArithmetic(double left, Operators operator_, double right) {
	switch (operator_) {
		case ADD:
			return left + right;
		case SUBTRACT:
			return left - right;
		case MULTIPLY:
			return left * right;
		case DIVIDE:
			return left / right;
		case REMAINDER:
			return fmod(left, right);
		case EXPONENT:
			return pow(left, right);
		default:
			return NAN;
	}
}

double evaluateRelational(double left, Operators operator_, double right) {
	switch (operator_) {
		case LESS:
			return left < right;
		case GREATER:
			return left > right;
		case LESS_EQUAL:
			return left <= right;
		case GREATER_EQUAL:
			return left >= right;
		case EQUAL:
			return left == right;
		case NOT_EQUAL:
			return left != right;
		default:
			return NAN;
	}
}

double evaluateLogical(double left, Operators operator_, double right) {
	switch (operator_) {
		case AND:
			return left && right;
		case OR:
			return left || right;
		default:
			return NAN;
	}
}

double evaluateUnary(Operators operator_, double value) {
	switch (operator_) {
		case NEGATE:
			return -value;
		case NOT:
			return !value;
		default:
			return NAN;
	}
}

double foldLeft(Operators operator_, const vector<double>& values) {
	if (values.empty()) {
		return NAN;
	}
	double result = values.front();
	for (size_t index = 1; index < values.size(); index++) {
		result = evaluateArithmetic(result, operator_, values[index]);
	}
	return result;
}

static double foldRightRecursive(Operators operator_, const vector<double>& values, size_t index) {
	if (index == values.size() - 1) {
		return values[index];
	}
	return evaluateArithmetic(values[index], operator_, foldRightRecursive(operator_, values, index + 1));
}

double foldRight(Operators operator_, const vector<double>& values) {
	if (values.empty()) {
		return NAN;
	}
	return foldRightRecursive(operator_, values, 0);
}
