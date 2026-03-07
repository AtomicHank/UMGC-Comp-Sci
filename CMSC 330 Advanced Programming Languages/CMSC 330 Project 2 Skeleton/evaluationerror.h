// Student Name: Joe Merrill
// Date: March 1, 2026
// Project: CMSC 330 Project 2
// Description: Defines the EvaluationError exception class used for runtime evaluation errors.
// CMSC 330 Advanced Programming Languages
// Project 2
//
// This file contains the definition of the EvaluationError exception class.

#include <stdexcept>
#include <string>

class EvaluationError: public std::runtime_error {
public:
    EvaluationError(const std::string& message): std::runtime_error(message) {
    }
};

