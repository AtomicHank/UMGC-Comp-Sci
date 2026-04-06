# CMSC 430 Project 3 Documentation

Author: Joseph Merrill

Date: 2026-04-06

## Discussion of Approach

I approached this project by starting with the supplied interpreter skeleton and identifying which parts of the language were already implemented and which rubric items still needed to be supported. The scanner, parser, and evaluation helpers already handled some arithmetic and relational behavior, so I focused first on mapping the remaining project requirements to the correct file: lexical changes in `scanner.l`, grammar and semantic actions in `parser.y`, and runtime evaluation helpers in `values.cc`.

The next step was extending literal and operator support. In the scanner, I verified support for real literals, hexadecimal integer literals, and character literals with escape sequences. In the parser and evaluation functions, I confirmed the full set of arithmetic operators worked correctly, including subtraction, division, remainder, exponentiation, and unary negation. I also verified the additional relational operators and logical operators so that complex conditional expressions would evaluate correctly.

After the expression-level features were working, I focused on the statement-level features required for Project 3. I added and validated support for both `if` statements and `fold` statements. For `if`, the main goal was to ensure nested conditions and chained `elsif` branches selected the correct expression result. For `fold`, I made sure both left and right folding were evaluated correctly and that the operator passed into the fold could be applied across the list in the correct order of association.

I then completed the function and symbol-handling features. The interpreter now supports multiple variable declarations, list declarations, and functions with parameters supplied on the command line. I also strengthened parameter parsing so runtime arguments could be passed in forms consistent with the language, including decimal values, real values, hexadecimal values, and quoted character literals. This made the command-line execution behavior match the expectations described in the requirements document more closely.

Finally, I verified the interpreter against the supplied test files and added extra targeted tests for edge cases that were important to the rubric, especially fold behavior and parameter handling. This incremental approach made it easier to isolate problems, confirm that grammar changes still compiled cleanly through Bison and Flex, and ensure the final interpreter behavior matched both the project requirements and the rubric categories.

## Test Plan

Use screenshots of each compiler run and place them under the matching test case.

| Test Case | Input / Args | What It Verifies | Expected Result | Screenshot |
| --- | --- | --- | --- | --- |
| `test1.txt` | none | Arithmetic expression evaluation | `Result = 25` | Add screenshot |
| `test5.txt` | none | Real literals and hexadecimal integer literals | `Result = 115.57` | Add screenshot |
| `test6.txt` | none | Character literals with escape sequences | `Result = 12` | Add screenshot |
| `test7.txt` | none | Addition, subtraction, multiplication, division, remainder, exponentiation, negation | `Result = 5` | Add screenshot |
| `test8.txt` | none | Relational operators and logical operators | `Result = 0` | Add screenshot |
| `test9.txt` | none | `if` / `elsif` / `else` evaluation | `Result = 3` | Add screenshot |
| `test13.txt` | none | Right fold | `Result = 2` | Add screenshot |
| `test14.txt` | none | Left fold | `Result = 0` | Add screenshot |
| `test10.txt` | none | Multiple variable declarations with mixed types | `Result = 10` | Add screenshot |
| `test11.txt` | `2.5` | Single function parameter | `Result = 4` | Add screenshot |
| `test12.txt` | `5 2.5` | Multiple function parameters | `Result = 3.5` | Add screenshot |
| `test15.txt` | `2 2 65` | Combined statements, variables, lists, parameters, and operators | `Result = 3` | Add screenshot |
| Custom parameter test | `#A` | Hexadecimal command-line parameter handling | `Result = 11` | Add screenshot |
| Custom parameter test | `'A'` | Character command-line parameter handling | `Result = 1` | Add screenshot |
| Custom fold test | none | Fold with exponentiation | `Result = 512` | Add screenshot |
| Custom fold test | none | Fold with remainder | `Result = 2` | Add screenshot |

## Lessons Learned

One of the biggest lessons I learned from this project is how closely connected the scanner, parser, and evaluator are in a compiler-style program. A language feature is not truly complete just because the parser recognizes the syntax. The scanner has to classify the tokens correctly, the parser has to place them in the right precedence structure, and the semantic actions have to compute the correct result at runtime. Working through Project 3 made that pipeline much clearer to me.

The hardest part of the project was extending the grammar without breaking behavior that already worked. Small changes to operator handling or statement rules can affect precedence, associativity, or error recovery in unexpected ways. The `fold` statement was especially useful for understanding this because it depends on both grammar structure and correct runtime evaluation order. Implementing left and right fold behavior showed me that parsing a construct and evaluating it correctly are related but separate problems.

I also learned that testing needs to be organized around language features, not just around whole programs. The supplied tests were helpful, but the most useful checks were the smaller targeted cases that isolated a single behavior, such as hexadecimal parameter input, character parameter input, or fold evaluation with a less common operator. That style of testing made it much easier to confirm whether a failure came from tokenization, parsing, or evaluation.

Another important lesson was the value of keeping the implementation aligned with the rubric, not only the requirements document. The rubric emphasized more than just runtime correctness. It also required coverage of specific language features, good documentation, and comment blocks in each source file. That reminded me that successful project completion in a software course includes both technical implementation and clear communication of design, testing, and reflection.

If I were improving this project further, I would add a small automated regression test script that runs the compiler against all sample inputs and compares the reported results automatically. I would also consider adding stronger runtime validation for parameter types so that invalid command-line input could be reported with more precise semantic error messages. Those improvements would make the interpreter easier to maintain and easier to verify after future grammar changes.

## Improvements That Could Be Made

- Add automated regression scripts that run the full test suite and compare expected results.
- Split literal and command-line parsing helpers into a shared utility module to remove duplication.
- Add more semantic validation for invalid parameter formats and mismatched runtime inputs.
- Add a cleaner packaging script that exports the source zip and documentation together.

## Submission Checklist

- Export this documentation to PDF, Word, or RTF after replacing the placeholders with your final wording.
- Insert screenshots for each test case listed above.
- Submit the source zip and the documentation file as two separate uploads.
