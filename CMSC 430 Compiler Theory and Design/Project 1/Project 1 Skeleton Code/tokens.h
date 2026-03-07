// CMSC 430 Compiler Theory and Design
// Project 1
// Student: Joe Merrill
// Date: 2026-03-07
// Description: Enumerated token definitions for the lexical analyzer.

enum Tokens {
     RELOP = 256,
     ADDOP,
     MULOP,
     REMOP,
     EXPOP,
     NEGOP,
     ANDOP,
     OROP,
     NOTOP,
     ARROW,
     BEGIN_,
     CASE,
     CHARACTER,
     ELSE_,
     ELSIF,
     END,
     ENDIF,
     ENDFOLD,
     ENDSWITCH,
     FOLD,
     FUNCTION,
     IF,
     INTEGER,
     IS,
     LEFT,
     LIST,
     OF,
     OTHERS,
     REAL,
     RETURNS,
     RIGHT,
     SWITCH,
     THEN,
     WHEN,
     IDENTIFIER,
     INT_LITERAL,
     CHAR_LITERAL,
     REAL_LITERAL
};
