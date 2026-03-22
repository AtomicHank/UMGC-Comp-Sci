// CMSC 430 Compiler Theory and Design
// Project 4

#include <string>

using namespace std;

#include "types.h"
#include "listing.h"

static bool isNumeric(Types type) {
	return type == INT_TYPE || type == REAL_TYPE;
}

void checkAssignment(Types left, Types right, string context) {
	if (left == MISMATCH || right == MISMATCH) {
		return;
	}
	if (left == INT_TYPE && right == REAL_TYPE) {
		appendError(GENERAL_SEMANTIC, "Illegal Narrowing " + context);
		return;
	}
	if (left != right) {
		appendError(GENERAL_SEMANTIC, "Type Mismatch on " + context);
	}
}

Types checkWhen(Types trueType, Types falseType) {
	if (trueType == MISMATCH || falseType == MISMATCH) {
		return MISMATCH;
	}
	if (trueType != falseType) {
		appendError(GENERAL_SEMANTIC, "When Types Mismatch");
		return MISMATCH;
	}
	return trueType;
}

Types checkSwitch(Types expressionType, Types casesType, Types othersType) {
	if (expressionType != MISMATCH && expressionType != INT_TYPE) {
		appendError(GENERAL_SEMANTIC, "Switch Expression Not Integer");
	}
	return checkCases(casesType, othersType);
}

Types checkCases(Types left, Types right) {
	if (left == NONE) {
		return right;
	}
	if (right == NONE) {
		return left;
	}
	if (left == MISMATCH || right == MISMATCH) {
		return MISMATCH;
	}
	if (left != right) {
		appendError(GENERAL_SEMANTIC, "Case Types Mismatch");
		return MISMATCH;
	}
	return left;
}

Types checkArithmetic(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH) {
		return MISMATCH;
	}
	if (!isNumeric(left) || !isNumeric(right)) {
		appendError(GENERAL_SEMANTIC, "Arithmetic Operator Requires Numeric Types");
		return MISMATCH;
	}
	if (left == REAL_TYPE || right == REAL_TYPE) {
		return REAL_TYPE;
	}
	return INT_TYPE;
}

Types checkRemainder(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH) {
		return MISMATCH;
	}
	if (left != INT_TYPE || right != INT_TYPE) {
		appendError(GENERAL_SEMANTIC, "Remainder Operator Requires Integer Operands");
		return MISMATCH;
	}
	return INT_TYPE;
}

Types checkUnaryNumeric(Types type) {
	if (type == MISMATCH) {
		return MISMATCH;
	}
	if (!isNumeric(type)) {
		appendError(GENERAL_SEMANTIC, "Arithmetic Operator Requires Numeric Types");
		return MISMATCH;
	}
	return type;
}

Types checkRelational(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH) {
		return MISMATCH;
	}
	if (left == CHAR_TYPE && right == CHAR_TYPE) {
		return INT_TYPE;
	}
	if ((left == CHAR_TYPE && isNumeric(right)) || (right == CHAR_TYPE && isNumeric(left))) {
		appendError(GENERAL_SEMANTIC, "Character Literals Cannot be Compared to Numeric Expressions");
		return MISMATCH;
	}
	if (isNumeric(left) && isNumeric(right)) {
		return INT_TYPE;
	}
	appendError(GENERAL_SEMANTIC, "Type Mismatch on Function Return");
	return MISMATCH;
}

Types checkIf(Types thenType, Types elsifType, Types elseType) {
	Types combined = checkCases(thenType, elsifType == NONE ? thenType : elsifType);
	if (combined == MISMATCH || elseType == MISMATCH) {
		return MISMATCH;
	}
	if (combined != elseType) {
		appendError(GENERAL_SEMANTIC, "If-Elsif-Else Type Mismatch");
		return MISMATCH;
	}
	return combined;
}

Types checkListElements(Types left, Types right) {
	if (left == NONE) {
		return right;
	}
	if (right == NONE) {
		return left;
	}
	if (left == MISMATCH || right == MISMATCH) {
		return MISMATCH;
	}
	if (left != right) {
		appendError(GENERAL_SEMANTIC, "List Element Types Do Not Match");
		return MISMATCH;
	}
	return left;
}

void checkListType(Types declaredType, Types elementType) {
	if (declaredType == MISMATCH || elementType == MISMATCH) {
		return;
	}
	if (declaredType != elementType) {
		appendError(GENERAL_SEMANTIC, "List Type Does Not Match Element Types");
	}
}

Types checkListSubscript(Types subscriptType, Types elementType) {
	if (subscriptType == MISMATCH || elementType == MISMATCH) {
		return MISMATCH;
	}
	if (subscriptType != MISMATCH && subscriptType != INT_TYPE) {
		appendError(GENERAL_SEMANTIC, "List Subscript Must Be Integer");
	}
	return elementType;
}

Types checkFold(Types listType) {
	if (listType == MISMATCH) {
		return MISMATCH;
	}
	if (!isNumeric(listType)) {
		appendError(GENERAL_SEMANTIC, "Fold Requires A Numeric List");
		return MISMATCH;
	}
	return listType;
}
