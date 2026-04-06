/*
 * Student: Joe Merrill
 * Course: CMSC 430 Compiler Theory and Design
 * Project: Project 4
 * Date: 2026-04-06
 * File: symbols.h
 * Description: Provides the simple symbol-table template used to store and
 * retrieve scalar and list declarations during semantic analysis.
 */

#pragma once

#include <map>
#include <string>

using namespace std;

template <typename T>
class Symbols {
public:
	void insert(char* lexeme, T entry);
	bool find(char* lexeme, T& entry);

private:
	map<string, T> symbols;
};

template <typename T>
void Symbols<T>::insert(char* lexeme, T entry) {
	string name(lexeme);
	symbols[name] = entry;
}

template <typename T>
bool Symbols<T>::find(char* lexeme, T& entry) {
	string name(lexeme);
	typedef typename map<string, T>::iterator Iterator;
	Iterator iterator = symbols.find(name);
	bool found = iterator != symbols.end();
	if (found) {
		entry = iterator->second;
	}
	return found;
}
