/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Defines the Project 3 grammar and semantic actions that
   evaluate expressions, statements, variables, lists, folds, and function
   parameters. */

%{

#include <cmath>
#include <cstdlib>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

#include "values.h"
#include "listing.h"
#include "symbols.h"

int yylex();
void yyerror(const char* message);
double extract_element(CharPtr list_name, double subscript);
double lookup_scalar(CharPtr identifier);
vector<double>* lookup_list(CharPtr identifier);
void insert_scalar(CharPtr identifier, double value);
void insert_list(CharPtr identifier, vector<double>* value);
double parse_parameter();
double parse_command_line_argument(const char* text);

Symbols<double> scalars;
Symbols<vector<double>*> lists;
vector<double> switchValues;
double result;
int parameterIndex;
int argumentCount;
char** argumentValues;

%}

%define parse.error verbose

%code requires {
#include <vector>
#include "values.h"
}

%union {
	CharPtr iden;
	Operators oper;
	Directions direction;
	double value;
	vector<double>* list;
}

%token <iden> IDENTIFIER
%token <value> INT_LITERAL REAL_LITERAL CHAR_LITERAL
%token <oper> ADDOP MULOP MODOP EXPOP NEGOP ANDOP OROP NOTOP RELOP
%token ARROW
%token BEGIN_ CASE CHARACTER ELSE ELSIF END ENDIF ENDFOLD ENDSWITCH FOLD FUNCTION
%token IF INTEGER IS LEFT LIST OF OTHERS REAL RETURNS RIGHT SWITCH THEN WHEN

%type <value> function body statement_ statement cases case condition or_condition and_condition
	 not_condition relation expression term power unary primary elsif_clauses
%type <list> list expressions list_choice
%type <direction> direction
%type <oper> operator

%%

function:
	function_header variables body ';' { result = $3; }
	;

function_header:
	FUNCTION IDENTIFIER optional_parameters RETURNS type ';'
	;

optional_parameters:
	parameters
	| %empty
	;

parameters:
	parameters ',' parameter
	| parameter
	;

parameter:
	IDENTIFIER ':' type { insert_scalar($1, parse_parameter()); }
	;

variables:
	variables variable
	| %empty
	;

variable:
	IDENTIFIER ':' type IS statement_ { insert_scalar($1, $5); }
	| IDENTIFIER ':' LIST OF type IS list ';' { insert_list($1, $7); }
	;

type:
	INTEGER
	| REAL
	| CHARACTER
	;

list:
	'(' expressions ')' { $$ = $2; }
	;

expressions:
	expressions ',' expression { $1->push_back($3); $$ = $1; }
	| expression { $$ = new vector<double>(); $$->push_back($1); }
	;

body:
	BEGIN_ statement_ END { $$ = $2; }
	;

statement_:
	statement ';' { $$ = $1; }
	| error ';' { $$ = NAN; yyerrok; }
	;

statement:
	expression { $$ = $1; }
	| WHEN condition ',' expression ':' expression { $$ = $2 ? $4 : $6; }
	| SWITCH expression IS { switchValues.push_back($2); } cases OTHERS ARROW statement_ ENDSWITCH
		{
			switchValues.pop_back();
			$$ = !isnan($5) ? $5 : $8;
		}
	| IF condition THEN statement_ elsif_clauses ELSE statement_ ENDIF
		{ $$ = $2 ? $4 : (!isnan($5) ? $5 : $7); }
	| FOLD direction operator list_choice ENDFOLD
		{
			if ($4 == nullptr) {
				$$ = NAN;
			} else {
				$$ = $2 == LEFT_FOLD ? foldLeft($3, *$4) : foldRight($3, *$4);
			}
		}
	;

elsif_clauses:
	elsif_clauses ELSIF condition THEN statement_
		{ $$ = !isnan($1) ? $1 : ($3 ? $5 : NAN); }
	| %empty { $$ = NAN; }
	;

cases:
	cases case { $$ = !isnan($1) ? $1 : $2; }
	| %empty { $$ = NAN; }
	;

case:
	CASE INT_LITERAL ARROW statement_ { $$ = switchValues.back() == $2 ? $4 : NAN; }
	;

direction:
	LEFT { $$ = LEFT_FOLD; }
	| RIGHT { $$ = RIGHT_FOLD; }
	;

operator:
	ADDOP { $$ = $1; }
	| MULOP { $$ = $1; }
	| MODOP { $$ = $1; }
	| EXPOP { $$ = $1; }
	;

list_choice:
	list { $$ = $1; }
	| IDENTIFIER { $$ = lookup_list($1); }
	;

condition:
	or_condition { $$ = $1; }
	;

or_condition:
	or_condition OROP and_condition { $$ = evaluateLogical($1, $2, $3); }
	| and_condition { $$ = $1; }
	;

and_condition:
	and_condition ANDOP not_condition { $$ = evaluateLogical($1, $2, $3); }
	| not_condition { $$ = $1; }
	;

not_condition:
	NOTOP not_condition { $$ = evaluateUnary($1, $2); }
	| relation { $$ = $1; }
	;

relation:
	'(' condition ')' { $$ = $2; }
	| expression RELOP expression { $$ = evaluateRelational($1, $2, $3); }
	;

expression:
	expression ADDOP term { $$ = evaluateArithmetic($1, $2, $3); }
	| term { $$ = $1; }
	;

term:
	term MULOP power { $$ = evaluateArithmetic($1, $2, $3); }
	| term MODOP power { $$ = evaluateArithmetic($1, $2, $3); }
	| power { $$ = $1; }
	;

power:
	unary EXPOP power { $$ = evaluateArithmetic($1, $2, $3); }
	| unary { $$ = $1; }
	;

unary:
	NEGOP unary { $$ = evaluateUnary($1, $2); }
	| primary { $$ = $1; }
	;

primary:
	'(' expression ')' { $$ = $2; }
	| INT_LITERAL { $$ = $1; }
	| REAL_LITERAL { $$ = $1; }
	| CHAR_LITERAL { $$ = $1; }
	| IDENTIFIER '(' expression ')' { $$ = extract_element($1, $3); }
	| IDENTIFIER { $$ = lookup_scalar($1); }
	;

%%

void yyerror(const char* message) {
	string formatted = message;
	size_t unexpected = formatted.find("unexpected ");
	if (unexpected != string::npos) {
		formatted = formatted.substr(unexpected);
	}
	appendError(SYNTAX, formatted);
}

double extract_element(CharPtr list_name, double subscript) {
	vector<double>* list = lookup_list(list_name);
	if (isnan(subscript) || list == nullptr) {
		return NAN;
	}
	int index = static_cast<int>(subscript);
	if (index < 0 || index >= static_cast<int>(list->size())) {
		appendError(GENERAL_SEMANTIC, "List subscript out of bounds");
		return NAN;
	}
	return (*list)[index];
}

double lookup_scalar(CharPtr identifier) {
	double value;
	if (!scalars.find(identifier, value)) {
		appendError(UNDECLARED, identifier);
		return NAN;
	}
	return value;
}

vector<double>* lookup_list(CharPtr identifier) {
	vector<double>* value;
	if (!lists.find(identifier, value)) {
		appendError(UNDECLARED, identifier);
		return nullptr;
	}
	return value;
}

void insert_scalar(CharPtr identifier, double value) {
	double scalarValue;
	vector<double>* listValue;
	if (scalars.find(identifier, scalarValue) || lists.find(identifier, listValue)) {
		appendError(DUPLICATE_IDENTIFIER, identifier);
		return;
	}
	scalars.insert(identifier, value);
}

void insert_list(CharPtr identifier, vector<double>* value) {
	double scalarValue;
	vector<double>* listValue;
	if (scalars.find(identifier, scalarValue) || lists.find(identifier, listValue)) {
		appendError(DUPLICATE_IDENTIFIER, identifier);
		return;
	}
	lists.insert(identifier, value);
}

double parse_parameter() {
	if (parameterIndex >= argumentCount) {
		appendError(GENERAL_SEMANTIC, "Missing command line argument");
		return NAN;
	}
	return parse_command_line_argument(argumentValues[parameterIndex++]);
}

double parse_command_line_argument(const char* text) {
	if (text == nullptr || *text == '\0') {
		appendError(GENERAL_SEMANTIC, "Invalid command line argument");
		return NAN;
	}

	string argument(text);
	size_t offset = 0;
	double sign = 1;
	if (argument[offset] == '+' || argument[offset] == '-') {
		sign = argument[offset] == '-' ? -1 : 1;
		offset++;
	}

	if (offset < argument.length() && argument[offset] == '#') {
		char* end = nullptr;
		long value = strtol(argument.c_str() + offset + 1, &end, 16);
		if (end != nullptr && *end == '\0') {
			return sign * value;
		}
	}

	if (offset < argument.length() && argument[offset] == '\'') {
		double value = NAN;
		size_t remaining = argument.length() - offset;
		if (remaining == 3 && argument[offset + 2] == '\'') {
			value = argument[offset + 1];
		} else if (remaining == 4 && argument[offset + 1] == '\\' &&
				argument[offset + 3] == '\'') {
			switch (argument[offset + 2]) {
				case 'b':
					value = '\b';
					break;
				case 't':
					value = '\t';
					break;
				case 'n':
					value = '\n';
					break;
				case 'r':
					value = '\r';
					break;
				case 'f':
					value = '\f';
					break;
				case '\\':
					value = '\\';
					break;
				case '\'':
					value = '\'';
					break;
				case '"':
					value = '"';
					break;
				default:
					value = argument[offset + 2];
			}
		}
		if (!isnan(value)) {
			return sign * value;
		}
	}

	char* end = nullptr;
	double value = strtod(text, &end);
	if (end != nullptr && *end == '\0') {
		return value;
	}

	appendError(GENERAL_SEMANTIC, "Invalid command line argument: " + argument);
	return NAN;
}

int main(int argc, char *argv[]) {
	parameterIndex = 1;
	argumentCount = argc;
	argumentValues = argv;
	firstLine();
	yyparse();
	if (lastLine() == 0) {
		cout << "Result = " << result << endl;
	}
	return 0;
}
