// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the BinaryExpression class and evaluates all supported binary operators.
// CMSC 330 Advanced Programming Languages
// Project 2
//
// This file contains the definition of the BinaryExpression class used to evaluate
// binary expressions.

class BinaryExpression: public SubExpression {
public:
    BinaryExpression(Expression* left, Expression* right, char operation);
    double evaluate();

private:
    char operation;
};

