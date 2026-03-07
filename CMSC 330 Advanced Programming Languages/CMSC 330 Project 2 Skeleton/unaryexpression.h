// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the UnaryExpression class for postfix unary operations.
// CMSC 330 Advanced Programming Languages
// Project 2
//
// This file contains the definition of the UnaryExpression class used to evaluate
// postfix unary expressions.

class UnaryExpression: public Expression {
public:
    UnaryExpression(Expression* operand, char operation);
    double evaluate();

private:
    Expression* operand;
    char operation;
};

