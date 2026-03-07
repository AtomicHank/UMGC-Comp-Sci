// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements the UnaryExpression class for postfix unary operations.

#include "expression.h"
#include "unaryexpression.h"

UnaryExpression::UnaryExpression(Expression* operand, char operation) {
    this->operand = operand;
    this->operation = operation;
}

double UnaryExpression::evaluate() {
    if (operation == '~')
        return -operand->evaluate();
    return 0;
}
