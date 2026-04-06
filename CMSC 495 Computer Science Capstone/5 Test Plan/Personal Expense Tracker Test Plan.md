# Personal Expense Tracker
## Unit 5 Test Plan

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

This test plan defines how the Personal Expense Tracker will be validated before final submission. The application is a desktop system built with Java 21, JavaFX, Spring Boot, and SQLite. Because it stores personal financial records and presents totals visually, testing must confirm both correctness and usability.

## Testing Objectives

1. Verify that core functional requirements work correctly.
2. Confirm that data is stored and retrieved reliably.
3. Validate that totals and charts match saved expense data.
4. Detect defects early enough to correct them before final submission.
5. Confirm that a first-time user can complete the main workflow.

## Test Strategy

The project uses a layered testing strategy that combines automated and manual methods.

### Automated Testing

1. Unit tests for model and service logic
2. Repository tests for database initialization
3. Integration tests for the end-to-end user workflow
4. UI test scaffolding for login behavior

### Manual Testing

1. Manual walkthroughs of registration, login, and dashboard use
2. Validation of totals and charts after multiple expense entries
3. Exploratory testing for edge cases and usability issues

## Test Scope

### In Scope

1. Registration and login
2. Database initialization and default category seeding
3. Expense add, update, delete, and retrieval
4. Category creation and retrieval
5. Search behavior
6. Total spending calculation
7. Pie chart and bar chart data
8. Logout workflow

### Out of Scope

1. Bank integration
2. Mobile deployment
3. Enterprise-scale performance testing
4. External security penetration testing

## Test Environment

The standard environment includes:

1. Java 21
2. Maven 3.6.3 or newer
3. JavaFX 21
4. SQLite test database configuration
5. Desktop OS capable of running JavaFX

## Test Cases

| Test Case ID | Description | Expected Result | Type |
| --- | --- | --- | --- |
| TC-01 | Register a new user | User is created and login succeeds | Functional |
| TC-02 | Attempt invalid login | Authentication fails and user remains on login screen | Functional |
| TC-03 | Launch app on clean database | Tables are created and default categories exist | Integration |
| TC-04 | Add a valid expense | Expense appears in the table and persists | Functional |
| TC-05 | Update an expense | Selected record changes correctly | Functional |
| TC-06 | Delete an expense | Record is removed after confirmation | Functional |
| TC-07 | Add a custom category | Category appears in the drop-down list | Functional |
| TC-08 | Search expenses | Matching records are returned | Functional |
| TC-09 | Review total spending | Total label matches saved expense sum | Functional |
| TC-10 | Review chart output | Chart values match category totals | Functional |
| TC-11 | Submit invalid amount | Validation message appears and no record is added | Usability |
| TC-12 | Log out from dashboard | Application returns to the login view | UI |

## Testing Procedures

1. Prepare the database state.
2. Execute the test steps.
3. Record the actual result.
4. Compare the result to the expected outcome.
5. Log any failure as a defect.
6. Retest after the fix is applied.

### Example Procedure for Adding an Expense

1. Log in with a valid user.
2. Select a category.
3. Enter a valid amount, description, and date.
4. Click `Add Expense`.
5. Confirm that the expense appears in the table and updates totals.

## Testing Schedule

| Unit | Testing Work |
| --- | --- |
| Unit 4 | Initial unit tests and environment verification |
| Unit 5 | Formal test case definition and feature validation |
| Unit 6 | Regression testing after refinements |
| Unit 7 | Manual usability walkthroughs |
| Unit 8 | Final regression pass before submission |

## Testing Resources

### Personnel

1. Testing lead to manage cases and defects
2. Developers to write and maintain automated tests
3. Documentation support to record evidence and results
4. Entire team for manual walkthroughs

### Tools

1. JUnit 5
2. Mockito
3. TestFX
4. JaCoCo
5. Maven
6. SQLite test database

## Defect Management

### Workflow

1. Identify and reproduce the issue
2. Record the defect with steps and severity
3. Assign an owner
4. Apply a fix
5. Retest before closing

### Severity Levels

1. Critical: blocks major workflows such as login or saving data
2. Major: causes wrong calculations or broken CRUD behavior
3. Minor: small workflow or display issue
4. Enhancement: desirable improvement but not required for release

## Responsibilities

| Responsibility | Owner |
| --- | --- |
| Service and model tests | `[Insert Name]` |
| Integration and database validation | `[Insert Name]` |
| Manual UI walkthroughs | `[Insert Name]` |
| Defect tracking and evidence collection | `[Insert Name]` |

## Conclusion

This test plan gives the team a structured way to validate the Personal Expense Tracker before final submission. By combining automated testing with manual walkthroughs, the team can confirm both technical correctness and user-facing behavior while keeping testing aligned with the actual project scope.

## References

1. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
2. Mockito. *Mockito Documentation*. https://site.mockito.org/
3. Spring. *Spring Boot Testing*. https://docs.spring.io/spring-boot/reference/testing/
4. Project source code and test files in `cmsc495-expense-tracker-feature-unit-tests`
