/*
 * Student: Joe Merrill
 * Course: CMSC 430 Compiler Theory and Design
 * Project: Project 4
 * Date: 2026-04-06
 * File: parser.y
 * Description: Defines the grammar and semantic actions for Project 4,
 * including symbol-table management and semantic error reporting.
 */

%{
#include <string>

using namespace std;

#include "types.h"
#include "listing.h"
#include "symbols.h"

int yylex();
void yyerror(const char* message);
Types find(Symbols<Types>& table, CharPtr identifier, string kind);
void insertScalar(CharPtr identifier, Types type);
void insertList(CharPtr identifier, Types type);

Symbols<Types> scalars;
Symbols<Types> lists;
Types functionReturnType;
%}

%define parse.error verbose

%union {
	CharPtr iden;
	Types type;
}

%token <iden> IDENTIFIER
%token <type> INT_LITERAL REAL_LITERAL CHAR_LITERAL
%token ADDOP MULOP MODOP EXPOP NEGOP ANDOP OROP NOTOP RELOP ARROW
%token BEGIN_ CASE CHARACTER ELSE ELSIF END ENDIF ENDFOLD ENDSWITCH FOLD FUNCTION
%token IF INTEGER IS LEFT LIST OF OTHERS REAL RETURNS RIGHT SWITCH THEN WHEN

%type <type> function body type optional_parameters parameters parameter variables variable list
	 expressions statement_ statement elsif_clauses cases case condition or_condition and_condition
	 not_condition relation expression term power unary primary direction operator list_choice

%%

function:
	function_header variables body { checkAssignment(functionReturnType, $3, "Function Return"); }
	;

function_header:
	FUNCTION IDENTIFIER optional_parameters RETURNS type ';' { functionReturnType = $5; }
	| FUNCTION IDENTIFIER error ';' { functionReturnType = MISMATCH; yyerrok; }
	;

optional_parameters:
	parameters
	| %empty { $$ = NONE; }
	;

parameters:
	parameters ',' parameter { $$ = NONE; }
	| parameter { $$ = NONE; }
	;

parameter:
	IDENTIFIER ':' type { insertScalar($1, $3); $$ = $3; }
	;

variables:
	variables variable { $$ = NONE; }
	| %empty { $$ = NONE; }
	;

variable:
	IDENTIFIER ':' type IS statement_ {
		checkAssignment($3, $5, "Variable Initialization");
		insertScalar($1, $3);
		$$ = $3;
	}
	| IDENTIFIER ':' LIST OF type IS list ';' {
		checkListType($5, $7);
		insertList($1, $5);
		$$ = NONE;
	}
	| IDENTIFIER error IS statement_ { $$ = MISMATCH; yyerrok; }
	;

type:
	INTEGER { $$ = INT_TYPE; }
	| REAL { $$ = REAL_TYPE; }
	| CHARACTER { $$ = CHAR_TYPE; }
	;

list:
	'(' expressions ')' { $$ = $2; }
	;

expressions:
	expressions ',' expression { $$ = checkListElements($1, $3); }
	| expression { $$ = $1; }
	;

body:
	BEGIN_ statement_ END ';' { $$ = $2; }
	;

statement_:
	statement ';' { $$ = $1; }
	| error ';' { $$ = MISMATCH; yyerrok; }
	;

statement:
	expression { $$ = $1; }
	| WHEN condition ',' expression ':' expression { $$ = checkWhen($4, $6); }
	| WHEN error ';' { $$ = MISMATCH; yyerrok; }
	| SWITCH expression IS cases OTHERS ARROW statement_ ENDSWITCH { $$ = checkSwitch($2, $4, $7); }
	| IF condition THEN statement_ elsif_clauses ELSE statement_ ENDIF { $$ = checkIf($4, $5, $7); }
	| FOLD direction operator list_choice ENDFOLD { $$ = checkFold($4); }
	;

elsif_clauses:
	elsif_clauses ELSIF condition THEN statement_ { $$ = checkIfBranches($1, $5); }
	| %empty { $$ = NONE; }
	;

cases:
	cases case { $$ = checkCases($1, $2); }
	| %empty { $$ = NONE; }
	;

case:
	CASE INT_LITERAL ARROW statement_ { $$ = $4; }
	| CASE error ARROW statement_ { $$ = MISMATCH; yyerrok; }
	;

direction:
	LEFT { $$ = NONE; }
	| RIGHT { $$ = NONE; }
	;

operator:
	ADDOP { $$ = NONE; }
	| MULOP { $$ = NONE; }
	;

list_choice:
	list { $$ = $1; }
	| IDENTIFIER { $$ = find(lists, $1, "List "); }
	;

condition:
	or_condition { $$ = $1; }
	;

or_condition:
	or_condition OROP and_condition { $$ = INT_TYPE; }
	| and_condition { $$ = $1; }
	;

and_condition:
	and_condition ANDOP not_condition { $$ = INT_TYPE; }
	| not_condition { $$ = $1; }
	;

not_condition:
	NOTOP not_condition { $$ = $2; }
	| relation { $$ = $1; }
	;

relation:
	'(' condition ')' { $$ = $2; }
	| expression RELOP expression { $$ = checkRelational($1, $3); }
	;

expression:
	expression ADDOP term { $$ = checkArithmetic($1, $3); }
	| term { $$ = $1; }
	;

term:
	term MULOP power { $$ = checkArithmetic($1, $3); }
	| term MODOP power { $$ = checkRemainder($1, $3); }
	| power { $$ = $1; }
	;

power:
	unary EXPOP power { $$ = checkArithmetic($1, $3); }
	| unary { $$ = $1; }
	;

unary:
	NEGOP unary { $$ = checkUnaryNumeric($2); }
	| primary { $$ = $1; }
	;

primary:
	'(' expression ')' { $$ = $2; }
	| INT_LITERAL { $$ = $1; }
	| REAL_LITERAL { $$ = $1; }
	| CHAR_LITERAL { $$ = $1; }
	| IDENTIFIER '(' expression ')' { $$ = checkListSubscript($3, find(lists, $1, "List ")); }
	| IDENTIFIER { $$ = find(scalars, $1, "Scalar "); }
	;

%%

Types find(Symbols<Types>& table, CharPtr identifier, string kind) {
	Types type;
	if (!table.find(identifier, type)) {
		appendError(UNDECLARED, kind + identifier);
		return MISMATCH;
	}
	return type;
}

void insertScalar(CharPtr identifier, Types type) {
	Types existing;
	if (scalars.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "Scalar " + string(identifier));
		return;
	}
	if (lists.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "Scalar " + string(identifier));
		return;
	}
	scalars.insert(identifier, type);
}

void insertList(CharPtr identifier, Types type) {
	Types existing;
	if (lists.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "List " + string(identifier));
		return;
	}
	if (scalars.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "List " + string(identifier));
		return;
	}
	lists.insert(identifier, type);
}

void yyerror(const char* message) {
	string formatted = message;
	size_t unexpected = formatted.find("unexpected ");
	if (unexpected != string::npos) {
		formatted = formatted.substr(unexpected);
	}
	appendError(SYNTAX, formatted);
}

int main(int argc, char *argv[]) {
	firstLine();
	yyparse();
	lastLine();
	return 0;
}
