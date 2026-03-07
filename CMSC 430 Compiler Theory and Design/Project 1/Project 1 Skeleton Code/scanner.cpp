// CMSC 430 Compiler Theory and Design
// Project 1
// Student: Joe Merrill
// Date: 2026-03-07
// Description: Standalone scanner implementation used when Flex is unavailable.

#include <cctype>
#include <fstream>
#include <iostream>
#include <string>
#include <unordered_map>

#include "listing.h"
#include "tokens.h"

using namespace std;

namespace {

const unordered_map<string, int> kKeywords = {
    {"begin", BEGIN_},
    {"case", CASE},
    {"character", CHARACTER},
    {"else", ELSE_},
    {"elsif", ELSIF},
    {"end", END},
    {"endif", ENDIF},
    {"endfold", ENDFOLD},
    {"endswitch", ENDSWITCH},
    {"fold", FOLD},
    {"function", FUNCTION},
    {"if", IF},
    {"integer", INTEGER},
    {"is", IS},
    {"left", LEFT},
    {"list", LIST},
    {"of", OF},
    {"others", OTHERS},
    {"real", REAL},
    {"returns", RETURNS},
    {"right", RIGHT},
    {"switch", SWITCH},
    {"then", THEN},
    {"when", WHEN},
};

bool isDelimiter(char ch) {
    return isspace(static_cast<unsigned char>(ch)) || ch == ',' || ch == ':' ||
           ch == ';' || ch == '(' || ch == ')' || ch == '\'' || ch == '\0';
}

bool isHexDigitChar(char ch) {
    return isdigit(static_cast<unsigned char>(ch)) ||
           (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
}

void writeToken(ofstream& lexemes, int token, const string& lexeme) {
    lexemes << token << ' ' << lexeme << '\n';
}

void scanNumber(const string& line, size_t& index, ofstream& lexemes) {
    const size_t start = index;

    if (line[index] == '#') {
        index++;
        while (index < line.size() && isHexDigitChar(line[index])) {
            index++;
        }
        const string lexeme = line.substr(start, index - start);
        if (lexeme.size() > 1) {
            writeToken(lexemes, INT_LITERAL, lexeme);
        } else {
            appendError(LEXICAL, lexeme);
        }
        return;
    }

    bool sawLeadingDigits = false;
    while (index < line.size() && isdigit(static_cast<unsigned char>(line[index]))) {
        sawLeadingDigits = true;
        index++;
    }

    bool isReal = false;
    bool hasFractionDigits = false;
    if (index < line.size() && line[index] == '.') {
        isReal = true;
        index++;
        while (index < line.size() && isdigit(static_cast<unsigned char>(line[index]))) {
            hasFractionDigits = true;
            index++;
        }
    }

    bool valid = sawLeadingDigits || hasFractionDigits;
    if (index < line.size() && (line[index] == 'e' || line[index] == 'E')) {
        isReal = true;
        index++;
        if (index < line.size() && (line[index] == '+' || line[index] == '-')) {
            index++;
        }
        const size_t exponentStart = index;
        while (index < line.size() && isdigit(static_cast<unsigned char>(line[index]))) {
            index++;
        }
        valid = valid && exponentStart != index;
    }

    while (index < line.size() && !isDelimiter(line[index]) &&
           !ispunct(static_cast<unsigned char>(line[index]))) {
        index++;
        valid = false;
    }

    const string lexeme = line.substr(start, index - start);
    if (valid) {
        writeToken(lexemes, isReal ? REAL_LITERAL : INT_LITERAL, lexeme);
    } else {
        appendError(LEXICAL, lexeme);
    }
}

void scanCharacterLiteral(const string& line, size_t& index, ofstream& lexemes) {
    const size_t start = index;
    index++;

    bool valid = false;
    if (index < line.size()) {
        if (line[index] == '\\') {
            index++;
            if (index < line.size() &&
                (line[index] == 'b' || line[index] == 't' || line[index] == 'n' ||
                 line[index] == 'r' || line[index] == 'f')) {
                index++;
                valid = index < line.size() && line[index] == '\'';
            }
        } else if (line[index] != '\'' && line[index] != '\n') {
            index++;
            valid = index < line.size() && line[index] == '\'';
        }
    }

    if (valid) {
        index++;
        writeToken(lexemes, CHAR_LITERAL, line.substr(start, index - start));
        return;
    }

    while (index < line.size() && !isspace(static_cast<unsigned char>(line[index]))) {
        if (line[index] == '\'') {
            index++;
            break;
        }
        index++;
    }
    appendError(LEXICAL, line.substr(start, index - start));
}

void scanIdentifierOrKeyword(const string& line, size_t& index, ofstream& lexemes) {
    const size_t start = index;
    size_t cursor = index;
    size_t lastValidEnd = index;
    int underscoreCount = 0;

    while (cursor < line.size()) {
        const char ch = line[cursor];
        if (isalnum(static_cast<unsigned char>(ch))) {
            underscoreCount = 0;
            cursor++;
            lastValidEnd = cursor;
            continue;
        }
        if (ch != '_') {
            break;
        }
        underscoreCount++;
        if (underscoreCount > 2) {
            break;
        }
        cursor++;
    }

    if (lastValidEnd > start) {
        const string lexeme = line.substr(start, lastValidEnd - start);
        const auto keyword = kKeywords.find(lexeme);
        if (keyword != kKeywords.end()) {
            writeToken(lexemes, keyword->second, lexeme);
        } else {
            writeToken(lexemes, IDENTIFIER, lexeme);
        }
        index = lastValidEnd;
        return;
    }

    appendError(LEXICAL, string(1, line[index]));
    index++;
}

void scanLine(const string& line, ofstream& lexemes) {
    for (size_t index = 0; index < line.size();) {
        const char ch = line[index];

        if (isspace(static_cast<unsigned char>(ch))) {
            index++;
            continue;
        }

        if (index + 1 < line.size()) {
            const string two = line.substr(index, 2);
            if (two == "//" || two == "--") {
                return;
            }
            if (two == "=>" || two == "<>" || two == ">=" || two == "<=") {
                const int token = two == "=>" ? ARROW : RELOP;
                writeToken(lexemes, token, two);
                index += 2;
                continue;
            }
        }

        if (isalpha(static_cast<unsigned char>(ch))) {
            scanIdentifierOrKeyword(line, index, lexemes);
            continue;
        }

        if (ch == '_') {
            appendError(LEXICAL, string(1, ch));
            index++;
            continue;
        }

        if (isdigit(static_cast<unsigned char>(ch)) ||
            (ch == '.' && index + 1 < line.size() &&
             isdigit(static_cast<unsigned char>(line[index + 1]))) ||
            ch == '#') {
            scanNumber(line, index, lexemes);
            continue;
        }

        if (ch == '\'') {
            scanCharacterLiteral(line, index, lexemes);
            continue;
        }

        switch (ch) {
            case ',':
            case ':':
            case ';':
            case '(':
            case ')':
                writeToken(lexemes, static_cast<unsigned char>(ch), string(1, ch));
                index++;
                break;
            case '=':
            case '>':
            case '<':
                writeToken(lexemes, RELOP, string(1, ch));
                index++;
                break;
            case '+':
            case '-':
                writeToken(lexemes, ADDOP, string(1, ch));
                index++;
                break;
            case '*':
            case '/':
                writeToken(lexemes, MULOP, string(1, ch));
                index++;
                break;
            case '%':
                writeToken(lexemes, REMOP, string(1, ch));
                index++;
                break;
            case '^':
                writeToken(lexemes, EXPOP, string(1, ch));
                index++;
                break;
            case '~':
                writeToken(lexemes, NEGOP, string(1, ch));
                index++;
                break;
            case '&':
                writeToken(lexemes, ANDOP, string(1, ch));
                index++;
                break;
            case '|':
                writeToken(lexemes, OROP, string(1, ch));
                index++;
                break;
            case '!':
                writeToken(lexemes, NOTOP, string(1, ch));
                index++;
                break;
            default: {
                size_t end = index + 1;
                while (end < line.size() && !isspace(static_cast<unsigned char>(line[end])) &&
                       !isDelimiter(line[end])) {
                    end++;
                }
                appendError(LEXICAL, line.substr(index, end - index));
                index = end;
                break;
            }
        }
    }
}

}  // namespace

int main() {
    firstLine();

    ifstream inputStream;
    istream* input = &cin;

    ofstream lexemes("lexemes.txt", ios::trunc);
    string line;
    while (getline(*input, line)) {
        cout << line << '\n';
        scanLine(line, lexemes);
        nextLine();
    }

    const int errors = lastLine();
    lexemes.close();
    return errors == 0 ? 0 : 1;
}
