// CMSC 430 Compiler Theory and Design
// Project 4

#pragma once

#include <string>

using namespace std;

typedef char* CharPtr;

enum Types { MISMATCH, INT_TYPE, REAL_TYPE, CHAR_TYPE, NONE };

void checkAssignment(Types left, Types right, string context);
Types checkWhen(Types trueType, Types falseType);
Types checkSwitch(Types expressionType, Types casesType, Types othersType);
Types checkCases(Types left, Types right);
Types checkArithmetic(Types left, Types right);
Types checkRemainder(Types left, Types right);
Types checkUnaryNumeric(Types type);
Types checkRelational(Types left, Types right);
Types checkIf(Types thenType, Types elsifType, Types elseType);
Types checkListElements(Types left, Types right);
void checkListType(Types declaredType, Types elementType);
Types checkListSubscript(Types subscriptType, Types elementType);
Types checkFold(Types listType);
