/*
 * Student: Joe Merrill
 * Course: CMSC 430 Compiler Theory and Design
 * Project: Project 4
 * Date: 2026-04-06
 * File: types.h
 * Description: Declares the semantic type-checking helpers used by the
 * parser to validate assignments, control flow, arithmetic, and list usage.
 */

#pragma once

#include <string>

using namespace std;

typedef char* CharPtr;

enum Types { MISMATCH, INT_TYPE, REAL_TYPE, CHAR_TYPE, NONE };

void checkAssignment(Types left, Types right, string context);
Types checkWhen(Types trueType, Types falseType);
Types checkSwitch(Types expressionType, Types casesType, Types othersType);
Types checkCases(Types left, Types right);
Types checkIfBranches(Types left, Types right);
Types checkArithmetic(Types left, Types right);
Types checkRemainder(Types left, Types right);
Types checkUnaryNumeric(Types type);
Types checkRelational(Types left, Types right);
Types checkIf(Types thenType, Types elsifType, Types elseType);
Types checkListElements(Types left, Types right);
void checkListType(Types declaredType, Types elementType);
Types checkListSubscript(Types subscriptType, Types elementType);
Types checkFold(Types listType);
