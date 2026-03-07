// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements the TernaryExpression class for the ? conditional operator.

#include "expression.h"
#include "ternaryexpression.h"

TernaryExpression::TernaryExpression(Expression* condition, Expression* trueExpression, Expression* falseExpression) {
    this->condition = condition;
    this->trueExpression = trueExpression;
    this->falseExpression = falseExpression;
}

double TernaryExpression::evaluate() {
    if (condition->evaluate() != 0)
        return trueExpression->evaluate();
    return falseExpression->evaluate();
}
