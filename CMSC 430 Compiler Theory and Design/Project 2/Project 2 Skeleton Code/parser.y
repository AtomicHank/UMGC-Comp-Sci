/* CMSC 430 Compiler Theory and Design
   Project 2
   Student: Joe Merrill
   Date: March 7, 2026
   File: parser.y
   Description: Defines the Project 2 grammar and syntax-error recovery rules. */

%{
#include <string>

#include "listing.h"

int yylex();
void yyerror(const char* message);
%}

%define parse.error verbose

%token IDENTIFIER INT_LITERAL REAL_LITERAL CHAR_LITERAL
%token ADDOP MULOP MODOP EXPOP NEGOP ANDOP OROP NOTOP RELOP ARROW
%token BEGIN_ CASE CHARACTER ELSE ELSIF END ENDIF ENDFOLD ENDSWITCH FOLD FUNCTION
%token IF INTEGER IS LEFT LIST OF OTHERS REAL RETURNS RIGHT SWITCH THEN WHEN

%%

function:
	function_header variables body
	;

function_header:
	FUNCTION IDENTIFIER optional_parameters RETURNS type ';'
	| FUNCTION IDENTIFIER error ';' { yyerrok; }
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
	IDENTIFIER ':' type
	;

variables:
	variables variable
	| %empty
	;

variable:
	IDENTIFIER ':' type IS statement_
	| IDENTIFIER ':' LIST OF type IS list ';'
	| IDENTIFIER error IS statement_ { yyerrok; }
	;

type:
	INTEGER
	| REAL
	| CHARACTER
	;

list:
	'(' expressions ')'
	;

expressions:
	expressions ',' expression
	| expression
	;

body:
	BEGIN_ statement_ END ';'
	;

statement_:
	statement ';'
	| error ';' { yyerrok; }
	;

statement:
	expression
	| WHEN condition ',' expression ':' expression
	| WHEN error ',' expression ':' expression { yyerrok; }
	| WHEN condition ',' error ':' expression { yyerrok; }
	| WHEN condition ',' expression ':' error { yyerrok; }
	| WHEN condition error expression ':' expression { yyerrok; }
	| WHEN condition ',' expression error { yyerrok; }
	| WHEN error { yyerrok; }
	| SWITCH expression IS cases OTHERS ARROW statement_ ENDSWITCH
	| IF condition THEN statement_ elsif_clauses ELSE statement_ ENDIF
	| FOLD direction operator list_choice ENDFOLD
	;

elsif_clauses:
	elsif_clauses ELSIF condition THEN statement_
	| %empty
	;

cases:
	cases case
	| %empty
	;

case:
	CASE INT_LITERAL ARROW statement_
	| CASE error ARROW statement_ { yyerrok; }
	;

direction:
	LEFT
	| RIGHT
	;

operator:
	ADDOP
	| MULOP
	;

list_choice:
	list
	| IDENTIFIER
	;

condition:
	or_condition
	;

or_condition:
	or_condition OROP and_condition
	| and_condition
	;

and_condition:
	and_condition ANDOP not_condition
	| not_condition
	;

not_condition:
	NOTOP not_condition
	| relation
	;

relation:
	'(' condition ')'
	| expression RELOP expression
	;

expression:
	expression ADDOP term
	| term
	;

term:
	term MULOP power
	| term MODOP power
	| power
	;

power:
	unary EXPOP power
	| unary
	;

unary:
	NEGOP unary
	| primary
	;

primary:
	'(' expression ')'
	| INT_LITERAL
	| REAL_LITERAL
	| CHAR_LITERAL
	| IDENTIFIER '(' expression ')'
	| IDENTIFIER
	;

%%

void yyerror(const char* message) {
	std::string formatted = message;
	size_t unexpected = formatted.find("unexpected ");
	if (unexpected != std::string::npos)
		formatted = formatted.substr(unexpected);
	appendError(SYNTAX, formatted);
}

int main(int argc, char *argv[]) {
	firstLine();
	yyparse();
	return lastLine();
}
