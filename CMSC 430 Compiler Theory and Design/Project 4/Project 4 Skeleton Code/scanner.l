/*
 * Student: Joe Merrill
 * Course: CMSC 430 Compiler Theory and Design
 * Project: Project 4
 * Date: 2026-04-06
 * File: scanner.l
 * Description: Tokenizes the Project 4 source language, including numeric,
 * character, identifier, keyword, and operator tokens for the parser.
 */

%{
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include "types.h"
#include "listing.h"
#include "parser.tab.h"
%}

%option noyywrap

ws              [ \t\r]+
line            \n
comment         ("//"|"--")[^\n]*\n
id              [A-Za-z][A-Za-z0-9_]*
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
character       { ECHO; yylval.type = CHAR_TYPE; return CHARACTER; }
else            { ECHO; return ELSE; }
elsif           { ECHO; return ELSIF; }
end             { ECHO; return END; }
endif           { ECHO; return ENDIF; }
endfold         { ECHO; return ENDFOLD; }
endswitch       { ECHO; return ENDSWITCH; }
fold            { ECHO; return FOLD; }
function        { ECHO; return FUNCTION; }
if              { ECHO; return IF; }
integer         { ECHO; yylval.type = INT_TYPE; return INTEGER; }
is              { ECHO; return IS; }
left            { ECHO; return LEFT; }
list            { ECHO; return LIST; }
of              { ECHO; return OF; }
others          { ECHO; return OTHERS; }
real            { ECHO; yylval.type = REAL_TYPE; return REAL; }
returns         { ECHO; return RETURNS; }
right           { ECHO; return RIGHT; }
switch          { ECHO; return SWITCH; }
then            { ECHO; return THEN; }
when            { ECHO; return WHEN; }

{id}            { ECHO; yylval.iden = (CharPtr) malloc(yyleng + 1); strcpy(yylval.iden, yytext); return IDENTIFIER; }
{real}          { ECHO; yylval.type = REAL_TYPE; return REAL_LITERAL; }
{int}           { ECHO; yylval.type = INT_TYPE; return INT_LITERAL; }
{char}          { ECHO; yylval.type = CHAR_TYPE; return CHAR_LITERAL; }
{punc}          { ECHO; return yytext[0]; }
.               { ECHO; appendError(LEXICAL, yytext); }
%%
