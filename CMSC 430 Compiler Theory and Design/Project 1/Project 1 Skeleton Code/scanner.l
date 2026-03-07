/* CMSC 430 Compiler Theory and Design
   Project 1
   Student: Joe Merrill
   Date: 2026-03-07
   Description: Flex scanner definition for the project lexical analyzer. */

%{
#include <cstdio>
#include <string>

using namespace std;

#include "listing.h"
#include "tokens.h"

FILE* lexemeFile;

static void writeToken(int token) {
	fprintf(lexemeFile, "%d %s\n", token, yytext);
}
%}

%option noyywrap

ws		[ \t\r]+
comment		("//"|"--").*\n
line		[\n]
id		[A-Za-z]([A-Za-z0-9]|_[A-Za-z0-9]|__[A-Za-z0-9])*
digit		[0-9]
hex		\#[0-9A-Fa-f]+
real		({digit}*"."{digit}+([eE][+-]?{digit}+)?)
dec		{digit}+
char		\'([^\\\n]|\\[btnrf])\'
punc		[\(\),:;]
%%

{ws}		{ ECHO; }
{comment}	{ ECHO; nextLine(); }
{line}		{ ECHO; nextLine(); }
"|"		{ ECHO; writeToken(OROP); return(OROP); }
"!"		{ ECHO; writeToken(NOTOP); return(NOTOP); }
"="|"<>"|">"|">="|"<"|"<="	{ ECHO; writeToken(RELOP); return(RELOP); }
"+"|"-"		{ ECHO; writeToken(ADDOP); return(ADDOP); }
"*"|"/"		{ ECHO; writeToken(MULOP); return(MULOP); }
"%"		{ ECHO; writeToken(REMOP); return(REMOP); }
"^"		{ ECHO; writeToken(EXPOP); return(EXPOP); }
"~"		{ ECHO; writeToken(NEGOP); return(NEGOP); }
"&"		{ ECHO; writeToken(ANDOP); return(ANDOP); }
"=>"		{ ECHO; writeToken(ARROW); return(ARROW); }
begin		{ ECHO; writeToken(BEGIN_); return(BEGIN_); }
case		{ ECHO; writeToken(CASE); return(CASE); }
character	{ ECHO; writeToken(CHARACTER); return(CHARACTER); }
else		{ ECHO; writeToken(ELSE_); return(ELSE_); }
elsif		{ ECHO; writeToken(ELSIF); return(ELSIF); }
end		{ ECHO; writeToken(END); return(END); }
endif		{ ECHO; writeToken(ENDIF); return(ENDIF); }
endfold		{ ECHO; writeToken(ENDFOLD); return(ENDFOLD); }
endswitch	{ ECHO; writeToken(ENDSWITCH); return(ENDSWITCH); }
fold		{ ECHO; writeToken(FOLD); return(FOLD); }
function	{ ECHO; writeToken(FUNCTION); return(FUNCTION); }
if		{ ECHO; writeToken(IF); return(IF); }
integer		{ ECHO; writeToken(INTEGER); return(INTEGER); }
is		{ ECHO; writeToken(IS); return(IS); }
left		{ ECHO; writeToken(LEFT); return(LEFT); }
list		{ ECHO; writeToken(LIST); return(LIST); }
of		{ ECHO; writeToken(OF); return(OF); }
others		{ ECHO; writeToken(OTHERS); return(OTHERS); }
real		{ ECHO; writeToken(REAL); return(REAL); }
returns		{ ECHO; writeToken(RETURNS); return(RETURNS); }
right		{ ECHO; writeToken(RIGHT); return(RIGHT); }
switch		{ ECHO; writeToken(SWITCH); return(SWITCH); }
then		{ ECHO; writeToken(THEN); return(THEN); }
when		{ ECHO; writeToken(WHEN); return(WHEN); }

{id}		{ ECHO; writeToken(IDENTIFIER); return(IDENTIFIER);}
{hex}		{ ECHO; writeToken(INT_LITERAL); return(INT_LITERAL); }
{real}		{ ECHO; writeToken(REAL_LITERAL); return(REAL_LITERAL); }
{dec}		{ ECHO; writeToken(INT_LITERAL); return(INT_LITERAL); }
{char}		{ ECHO; writeToken(CHAR_LITERAL); return(CHAR_LITERAL); }
{punc}		{ ECHO; writeToken(yytext[0]); return(yytext[0]); }
.		{ ECHO; appendError(LEXICAL, yytext); }
%%

int main() {
	firstLine();
	
	lexemeFile = fopen("lexemes.txt", "w"); 
	int token = yylex();
	while (token) {
		token = yylex();
	}
	lastLine();
	fclose(lexemeFile);
	return 0;
}
