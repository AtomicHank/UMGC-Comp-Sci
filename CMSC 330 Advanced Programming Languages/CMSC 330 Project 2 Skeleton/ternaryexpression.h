// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the TernaryExpression class for the ? conditional operator.
// CMSC 330 Advanced Programming Languages
// Project 2
//
// This file contains the definition of the TernaryExpression class used to evaluate
// conditional expressions with the ? operator.

class TernaryExpression: public Expression {
public:
    TernaryExpression(Expression* condition, Expression* trueExpression, Expression* falseExpression);
    double evaluate();

private:
    Expression* condition;
    Expression* trueExpression;
    Expression* falseExpression;
};

