// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements the BinaryExpression class for supported binary operators.

#include <cmath>

#include "expression.h"
#include "subexpression.h"
#include "binaryexpression.h"

BinaryExpression::BinaryExpression(Expression* left, Expression* right, char operation): SubExpression(left, right) {
    this->operation = operation;
}

double BinaryExpression::evaluate() {
    double leftValue = left->evaluate();
    double rightValue = right->evaluate();

    switch (operation) {
        case '+':
            return leftValue + rightValue;
        case '-':
            return leftValue - rightValue;
        case '*':
            return leftValue * rightValue;
        case '/':
            return leftValue / rightValue;
        case '%':
            return fmod(leftValue, rightValue);
        case '^':
            return pow(leftValue, rightValue);
        case '<':
            return (leftValue < rightValue) ? leftValue : rightValue;
        case '>':
            return (leftValue > rightValue) ? leftValue : rightValue;
        case '&':
            return (leftValue + rightValue) / 2.0;
    }
    return 0;
}
