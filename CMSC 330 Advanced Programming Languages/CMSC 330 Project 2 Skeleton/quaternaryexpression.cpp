// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements the QuaternaryExpression class for the # conditional operator.

#include "expression.h"
#include "quaternaryexpression.h"

QuaternaryExpression::QuaternaryExpression(Expression* valueExpression, Expression* negativeExpression,
    Expression* zeroExpression, Expression* positiveExpression) {
    this->valueExpression = valueExpression;
    this->negativeExpression = negativeExpression;
    this->zeroExpression = zeroExpression;
    this->positiveExpression = positiveExpression;
}

double QuaternaryExpression::evaluate() {
    double value = valueExpression->evaluate();
    if (value < 0)
        return negativeExpression->evaluate();
    if (value == 0)
        return zeroExpression->evaluate();
    return positiveExpression->evaluate();
}
