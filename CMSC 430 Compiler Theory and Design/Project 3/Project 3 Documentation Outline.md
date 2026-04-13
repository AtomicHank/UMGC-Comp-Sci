# CMSC 430 Project 3 Documentation

Author: Joseph Merrill

Date: 2026-04-06

## Report Completion Checklist

- [ ] Paste terminal screenshots into this document before export.
- [ ] Export this file as PDF, Word, or RTF for submission.
- [ ] Submit the source archive and the exported report as two separate files.
- [ ] Do not submit `Project 3 Approach.pdf` as the report because that file is the instructor handout, not the student documentation.

## Discussion of Approach

I approached this project by starting with the supplied interpreter skeleton and identifying which parts of the language were already implemented and which rubric items still needed to be supported. The scanner, parser, and evaluation helpers already handled some arithmetic and relational behavior, so I focused first on mapping the remaining project requirements to the correct file: lexical changes in `scanner.l`, grammar and semantic actions in `parser.y`, and runtime evaluation helpers in `values.cc`.

The next step was extending literal and operator support. In the scanner, I verified support for real literals, hexadecimal integer literals, and character literals with escape sequences. In the parser and evaluation functions, I confirmed the full set of arithmetic operators worked correctly, including subtraction, division, remainder, exponentiation, and unary negation. I also verified the additional relational operators and logical operators so that complex conditional expressions would evaluate correctly.

After the expression-level features were working, I focused on the statement-level features required for Project 3. I added and validated support for both `if` statements and `fold` statements. For `if`, the main goal was to ensure nested conditions and chained `elsif` branches selected the correct expression result. For `fold`, I made sure both left and right folding were evaluated correctly and that the operator passed into the fold could be applied across the list in the correct order of association.

I then completed the function and symbol-handling features. The interpreter now supports multiple variable declarations, list declarations, and functions with parameters supplied on the command line. I also strengthened parameter parsing so runtime arguments could be passed in forms consistent with the language, including decimal values, real values, hexadecimal values, and quoted character literals. This made the command-line execution behavior match the expectations described in the requirements document more closely.

Finally, I verified the interpreter against the supplied test files and added extra targeted tests for edge cases that were important to the rubric, especially fold behavior and parameter handling. This incremental approach made it easier to isolate problems, confirm that grammar changes still compiled cleanly through Bison and Flex, and ensure the final interpreter behavior matched both the project requirements and the rubric categories.

## Test Plan

Each screenshot should show the command used, the source listing, and the final `Result = ...` line.

Required screenshots for rubric coverage:

- [ ] `test5.txt` with no command-line arguments. Verifies real literals and hexadecimal integer literals. Expected result: `115.57`.
- [ ] `test6.txt` with no command-line arguments. Verifies character literals with escape sequences. Expected result: `12`.
- [ ] `test7.txt` with no command-line arguments. Verifies subtraction, division, remainder, exponentiation, and unary negation together with operator precedence. Expected result: `5`.
- [ ] `test8.txt` with no command-line arguments. Verifies additional relational and logical operators. Expected result: `0`.
- [ ] `test9.txt` with no command-line arguments. Verifies `if`, `elsif`, and `else`. Expected result: `3`.
- [ ] `test10.txt` with no command-line arguments. Verifies multiple variable declarations with mixed types. Expected result: `10`.
- [ ] `test11.txt 6.8`. Verifies a single function parameter supplied on the command line. Expected result: `8.3`.
- [ ] `test12.txt 16 15.9`. Verifies multiple function parameters supplied on the command line. Expected result: `14.9`.
- [ ] `test13.txt` with no command-line arguments. Verifies right fold using a list variable. Expected result: `2`.
- [ ] `test14.txt` with no command-line arguments. Verifies left fold using a list literal. Expected result: `0`.
- [ ] `test15.txt 1 2.5 65`. Verifies the combined language features test case from the requirements handout. Expected result: `1`.

Additional screenshots that strengthen the test plan:

- [ ] `test1.txt` with no command-line arguments. Verifies the original arithmetic expression behavior. Expected result: `25`.
- [ ] `test16.txt 1 -1`. Verifies nested `if` statements and character return values. Expected result: `52`.
- [ ] Custom parameter test using a program such as `function main x: integer returns integer; begin x + 1; end;` with argument `#A`. Verifies hexadecimal command-line parameter handling. Expected result: `11`.
- [ ] Custom parameter test using a program such as `function main c: character returns integer; begin if c = 'A' then 1; else 0; endif; end;` with argument `'A'`. Verifies character command-line parameter handling. Expected result: `1`.
- [ ] Custom fold test using `function main returns integer; begin fold right ^ (2, 3, 2) endfold; end;`. Verifies right fold with exponentiation. Expected result: `512`.
- [ ] Custom fold test using `function main returns integer; begin fold left % (20, 6, 4) endfold; end;`. Verifies left fold with remainder. Expected result: `2`.

Suggested PowerShell commands for the screenshots from the supplied test set:

- [ ] `Get-Content -Raw ".\Project 3 Test Data\test5.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test6.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test7.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test8.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test9.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test10.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test11.txt" | .\Project 3 Skeleton Code\compile.exe 6.8`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test12.txt" | .\Project 3 Skeleton Code\compile.exe 16 15.9`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test13.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test14.txt" | .\Project 3 Skeleton Code\compile.exe`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test15.txt" | .\Project 3 Skeleton Code\compile.exe 1 2.5 65`
- [ ] `Get-Content -Raw ".\Project 3 Test Data\test16.txt" | .\Project 3 Skeleton Code\compile.exe 1 -1`

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

- Confirm the final report contains the sections `Discussion of Approach`, `Test Plan`, `Lessons Learned`, and `Improvements That Could Be Made`.
- Confirm every source file includes a comment block with project name, author, date, and description.
- Insert the required screenshots listed in the test plan.
- Export this document to PDF, Word, or RTF.
- Submit `Project 3 Source Submission Clean 2026-04-06.zip` as the source archive.
- Submit the exported report as the documentation file.
