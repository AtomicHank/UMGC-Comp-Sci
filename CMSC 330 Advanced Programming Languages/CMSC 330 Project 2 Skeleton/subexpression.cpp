// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Implements subexpression parsing and construction for unary, binary, ternary, and quaternary expressions.
// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023

// This file contains the body of the functions contained in The SubExpression class, which includes
// the constructor that initializes the left and right subexpressions and the static function parse
// parses the subexpression. Addition and subtraction are the two operators that are implemented.

#include <iostream>
#include <sstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "unaryexpression.h"
#include "binaryexpression.h"
#include "ternaryexpression.h"
#include "quaternaryexpression.h"

SubExpression::SubExpression(Expression* left, Expression* right) {
    this->left = left;
    this->right = right;
}

Expression* SubExpression::parse(stringstream& in) {
    Expression* firstOperand;
    char operation;
    char paren;

    firstOperand = Operand::parse(in);
    in >> ws;
    operation = in.peek();

    if (operation == ')') {
        in >> paren;
        return firstOperand;
    }

    in >> operation;

    switch (operation) {
        case '~':
            in >> paren;
            return new UnaryExpression(firstOperand, operation);
        case '+':
        case '-':
        case '*':
        case '/':
        case '%':
        case '^':
        case '<':
        case '>':
        case '&': {
            Expression* secondOperand = Operand::parse(in);
            in >> paren;
            return new BinaryExpression(firstOperand, secondOperand, operation);
        }
        case '?': {
            Expression* trueExpression = Operand::parse(in);
            Expression* falseExpression = Operand::parse(in);
            in >> paren;
            return new TernaryExpression(firstOperand, trueExpression, falseExpression);
        }
        case '#': {
            Expression* negativeExpression = Operand::parse(in);
            Expression* zeroExpression = Operand::parse(in);
            Expression* positiveExpression = Operand::parse(in);
            in >> paren;
            return new QuaternaryExpression(firstOperand, negativeExpression, zeroExpression, positiveExpression);
        }
    }
    return 0;
}
        

