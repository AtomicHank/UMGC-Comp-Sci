/* CMSC 430 Compiler Theory and Design
   Project 2
   Student: Joe Merrill
   Date: March 7, 2026
   File: scanner.l
   Description: Defines the lexical analyzer for the Project 2 language. */

%{
#include <cstdio>

#include "listing.h"
#include "parser.tab.h"
%}

%option noyywrap

ws              [ \t\r]+
line            \n
comment         ("//"|"--")[^\n]*\n
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
{comment}       { ECHO; nextLine(); }
{line}          { ECHO; nextLine(); }
"=>"            { ECHO; return ARROW; }
"<="|">="|"<>"|"<"|">"|"=" { ECHO; return RELOP; }
"+"|"-"         { ECHO; return ADDOP; }
"*"|"/"         { ECHO; return MULOP; }
"%"             { ECHO; return MODOP; }
"^"             { ECHO; return EXPOP; }
"~"             { ECHO; return NEGOP; }
"&"             { ECHO; return ANDOP; }
"|"             { ECHO; return OROP; }
"!"             { ECHO; return NOTOP; }
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

{id}            { ECHO; return IDENTIFIER; }
{real}          { ECHO; return REAL_LITERAL; }
{int}           { ECHO; return INT_LITERAL; }
{char}          { ECHO; return CHAR_LITERAL; }
{punc}          { ECHO; return yytext[0]; }
.               { ECHO; appendError(LEXICAL, yytext); }
%%
