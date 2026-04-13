/* CMSC 430 Compiler Theory and Design
   Project 3
   Author: Joseph Merrill
   Date: 2026-04-06
   Description: Defines the lexical analyzer for Project 3, including
   operators, keywords, literals, identifiers, and source listing output. */

%{
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include "listing.h"
#include "values.h"
#include "parser.tab.h"

static double parseIntegerLiteral(const char* text);
static double parseCharacterLiteral(const char* text);
%}

%option noyywrap

ws              [ \t\r]+
line            \n
comment         ("//"|"--")[^\n]*(\n|$)
id              [A-Za-z][A-Za-z0-9]*
digit           [0-9]
int             ({digit}+|#[0-9A-Fa-f]+)
exp             [eE][+-]?{digit}+
real            (({digit}+\.{digit}*|\.{digit}+){exp}?|{digit}+{exp})
escape          \\[btnrf\\'"]
char            \'([^\\\n]|{escape})\'
punc            [\(\),:;]
%%

{ws}            { ECHO; }
{comment}       { ECHO; if (yytext[yyleng - 1] == '\n') nextLine(); }
{line}          { ECHO; nextLine(); }
"=>"            { ECHO; return ARROW; }
"<="            { ECHO; yylval.oper = LESS_EQUAL; return RELOP; }
">="            { ECHO; yylval.oper = GREATER_EQUAL; return RELOP; }
"<>"            { ECHO; yylval.oper = NOT_EQUAL; return RELOP; }
"<"             { ECHO; yylval.oper = LESS; return RELOP; }
">"             { ECHO; yylval.oper = GREATER; return RELOP; }
"="             { ECHO; yylval.oper = EQUAL; return RELOP; }
"+"             { ECHO; yylval.oper = ADD; return ADDOP; }
"-"             { ECHO; yylval.oper = SUBTRACT; return ADDOP; }
"*"             { ECHO; yylval.oper = MULTIPLY; return MULOP; }
"/"             { ECHO; yylval.oper = DIVIDE; return MULOP; }
"%"             { ECHO; yylval.oper = REMAINDER; return MODOP; }
"^"             { ECHO; yylval.oper = EXPONENT; return EXPOP; }
"~"             { ECHO; yylval.oper = NEGATE; return NEGOP; }
"&"             { ECHO; yylval.oper = AND; return ANDOP; }
"|"             { ECHO; yylval.oper = OR; return OROP; }
"!"             { ECHO; yylval.oper = NOT; return NOTOP; }
begin           { ECHO; return BEGIN_; }
case            { ECHO; return CASE; }
character       { ECHO; return CHARACTER; }
else            { ECHO; return ELSE; }
elsif           { ECHO; return ELSIF; }
end             { ECHO; return END; }
endif           { ECHO; return ENDIF; }
endfold         { ECHO; return ENDFOLD; }
endswitch       { ECHO; return ENDSWITCH; }
fold            { ECHO; return FOLD; }
function        { ECHO; return FUNCTION; }
if              { ECHO; return IF; }
integer         { ECHO; return INTEGER; }
is              { ECHO; return IS; }
left            { ECHO; return LEFT; }
list            { ECHO; return LIST; }
of              { ECHO; return OF; }
others          { ECHO; return OTHERS; }
real            { ECHO; return REAL; }
returns         { ECHO; return RETURNS; }
right           { ECHO; return RIGHT; }
switch          { ECHO; return SWITCH; }
then            { ECHO; return THEN; }
when            { ECHO; return WHEN; }

{id}            { ECHO; yylval.iden = (CharPtr) malloc(yyleng + 1); strcpy(yylval.iden, yytext); return IDENTIFIER; }
{real}          { ECHO; yylval.value = atof(yytext); return REAL_LITERAL; }
{int}           { ECHO; yylval.value = parseIntegerLiteral(yytext); return INT_LITERAL; }
{char}          { ECHO; yylval.value = parseCharacterLiteral(yytext); return CHAR_LITERAL; }
{punc}          { ECHO; return yytext[0]; }
.               { ECHO; appendError(LEXICAL, yytext); }
%%

static double parseIntegerLiteral(const char* text) {
	if (text[0] == '#') {
		return strtol(text + 1, nullptr, 16);
	}
	return atoi(text);
}

static double parseCharacterLiteral(const char* text) {
	if (text[1] != '\\') {
		return text[1];
	}
	switch (text[2]) {
		case 'b':
			return '\b';
		case 't':
			return '\t';
		case 'n':
			return '\n';
		case 'r':
			return '\r';
		case 'f':
			return '\f';
		case '\\':
			return '\\';
		case '\'':
			return '\'';
		case '"':
			return '"';
		default:
			return text[2];
	}
}
