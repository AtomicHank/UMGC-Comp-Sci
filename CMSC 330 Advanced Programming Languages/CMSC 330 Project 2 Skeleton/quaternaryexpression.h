// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the QuaternaryExpression class for the # conditional operator.
// CMSC 330 Advanced Programming Languages
// Project 2
//
// This file contains the definition of the QuaternaryExpression class used to evaluate
// conditional expressions with the # operator.

class QuaternaryExpression: public Expression {
public:
    QuaternaryExpression(Expression* valueExpression, Expression* negativeExpression,
        Expression* zeroExpression, Expression* positiveExpression);
    double evaluate();

private:
    Expression* valueExpression;
    Expression* negativeExpression;
    Expression* zeroExpression;
    Expression* positiveExpression;
};

