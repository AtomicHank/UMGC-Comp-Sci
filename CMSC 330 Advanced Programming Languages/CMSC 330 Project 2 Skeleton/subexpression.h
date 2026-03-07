// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the SubExpression abstract class and parse entry point for parenthesized expressions.
// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023

// This file contains the class definition of the SubExpression class, which is a subclass of Expression.
// Because it does not implement the abstract function evalauate, it is an abstract class. SubExpression
// objects are represented with the left and right subexpressions. The body of its constructor and the
// static (class) function parse are defined in subexpression.cpp. 

#include <sstream>

class SubExpression: public Expression {
public:
    SubExpression(Expression* left, Expression* right);
    static Expression* parse(std::stringstream& in);
    virtual double evaluate() = 0;
protected: 
    Expression* left;
    Expression* right;
};    

