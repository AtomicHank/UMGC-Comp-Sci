# Personal Expense Tracker
## Unit 5 Test Plan

Prepared for CMSC 495 Computer Science Capstone  
Team Members: Joe Merrill, Mayank Kulshreshta, Kyle Lewis, Matthew Lukenich, Greg Rossi, Jamar Watson  
Submission Date: April 7, 2026

## Introduction

This document presents the formal test plan for the Personal Expense Tracker capstone project. The Personal Expense Tracker is a desktop budgeting application built with Java 21, JavaFX, Spring Boot, and SQLite. The software allows a user to register, log in, create and manage expenses, create custom categories, search transaction history, and review totals with pie and bar chart summaries.

The purpose of this test plan is to define how the project team will verify that the application functions correctly, stores data reliably, and provides a usable experience for the intended audience. Because the application manages personal financial records, testing must confirm more than basic code execution. It must also confirm that transactions are saved accurately, displayed clearly, aggregated correctly, and resilient to common user-input errors.

This plan outlines the testing objectives, strategy, scope, cases, procedures, schedule, resources, and defect-management process the team will use through the remainder of the capstone. It is designed to support the Unit 5 submission while also serving as the foundation for final validation in later units.

## Testing Objectives

The overall goal of testing is to verify that the Personal Expense Tracker is stable, correct, and usable enough for final capstone submission. Specific objectives are listed below.

1. Confirm that all core functional requirements are implemented correctly.
2. Verify that data is stored, retrieved, updated, and deleted accurately.
3. Validate that category totals, overall totals, and chart summaries match saved data.
4. Confirm that the application initializes the database and default categories correctly on first launch.
5. Identify validation weaknesses involving blank fields, invalid numeric input, and incorrect authentication attempts.
6. Reduce the likelihood of regressions by maintaining automated tests for critical logic paths.
7. Confirm that a first-time user can complete the basic workflow of registering, logging in, entering expenses, and reading dashboard results.
8. Provide a documented process for recording, prioritizing, fixing, and retesting defects.

### Quality Attributes to Be Assessed

1. Functional correctness
2. Data integrity
3. Usability
4. Maintainability through repeatable test automation
5. Basic reliability during repeated use

## Test Strategy

The project uses a layered test strategy that combines automated testing with manual validation. This approach is appropriate for the project because the application includes both backend logic and a desktop user interface.

### Automated Testing Approach

Automated testing focuses on code that can be validated consistently and repeatedly.

1. **Model testing**  
   Model tests confirm that `User`, `Category`, and `Expense` objects are created correctly and preserve expected values.

2. **Service-layer unit testing**  
   Service tests validate logic in `AuthenticationService`, `CategoryService`, and `ExpenseService` using mocked dependencies where appropriate. These tests verify registration behavior, authentication handling, category retrieval, expense CRUD logic, search support, and aggregation methods.

3. **Repository and initialization testing**  
   Database initialization tests confirm that the required tables are created and that default categories are seeded correctly.

4. **Integration testing**  
   Integration tests validate that multiple layers of the application work together correctly. The current integration workflow covers registration, category creation, expense creation, total calculation, category totals, and expense deletion.

5. **UI testing scaffolding**  
   TestFX is included for UI interaction testing. Although full execution is limited in some headless Java 21 environments, the test scaffolding remains valuable for structure and future extension.

### Manual Testing Approach

Manual testing remains necessary because the application is a desktop GUI system and some usability and display issues are easier to evaluate by direct interaction.

1. Manual walkthrough of the full user workflow
2. Manual validation of form behavior and messages
3. Manual verification of totals and chart updates
4. Manual review of logout behavior and screen transitions
5. Exploratory testing for unusual user actions and edge cases

### Testing Methodologies

The following methodologies will be used:

1. Unit testing
2. Integration testing
3. Regression testing
4. Usability testing
5. Exploratory testing
6. Limited manual performance observation for local responsiveness

## Test Scope

The scope identifies which areas of the project are included in formal testing and which are outside the current boundaries of the Unit 5 plan.

### In Scope

1. User registration
2. User login and failed-login handling
3. Database initialization and default category seeding
4. Category retrieval and custom category creation
5. Expense add, update, delete, retrieval, and search
6. Dashboard total calculation
7. Category pie chart and bar chart data generation
8. Logout workflow
9. Validation of blank or invalid amount input
10. Validation of blank or invalid credential input

### Out of Scope

1. Mobile device support
2. Bank or credit-card integration
3. Cloud synchronization
4. Security penetration testing
5. Enterprise-scale load testing
6. Export to CSV, PDF, or spreadsheet
7. Advanced budgeting, reminders, or alert workflows

### Assumptions

1. The application will be run locally on a desktop operating system.
2. Java 21 and Maven are available in the development environment.
3. SQLite local files are acceptable for both development and integration testing.
4. Reviewers are evaluating an academic capstone project, not a production finance platform.

## Test Environment

The standard test environment for this project is described below.

### Software Environment

1. Java 21
2. Maven 3.6.3 or newer
3. JavaFX 21.0.2
4. Spring Boot 3.2.4
5. SQLite JDBC 3.45.1.0
6. JUnit 5
7. Mockito
8. TestFX
9. JaCoCo

### Hardware Environment

1. Standard laptop or desktop used by team members
2. Sufficient local storage for project files and SQLite database files
3. Internet access for dependency installation and repository collaboration

### Database Configuration

1. Production/local application database: `expense_tracker.db`
2. Integration testing database: `expense_tracker_test.db`
3. Database setup is initialized through the Spring Boot application and `DatabaseManager`

### Test Environment Configuration Steps

1. Pull the latest source code from the shared repository.
2. Open the project root in the development environment.
3. Confirm Java and Maven versions.
4. Run `mvn clean install` to build the project and resolve dependencies.
5. Use the test profile when running integration tests so they do not target the primary application database.

## Test Cases

The following cases cover the most important functional, usability, and reliability concerns for the current scope of the project.

### Summary Test Case Table

| Test Case ID | Test Description | Expected Result | Type | Priority |
| --- | --- | --- | --- | --- |
| TC-01 | Register a new user with valid email and password | User account is created and returned by the service | Functional | High |
| TC-02 | Attempt registration with duplicate email | Registration fails without creating a second account | Functional | High |
| TC-03 | Attempt registration with blank email or password | Registration is rejected | Functional | High |
| TC-04 | Log in with valid credentials | User is authenticated and taken to dashboard | Functional | High |
| TC-05 | Log in with invalid credentials | Authentication fails and user remains on login screen | Functional | High |
| TC-06 | Launch application on clean database | Tables are created and default categories are seeded | Integration | High |
| TC-07 | Add valid expense | Expense record is inserted and appears in dashboard data | Functional | High |
| TC-08 | Update selected expense | Modified record replaces previous values | Functional | High |
| TC-09 | Delete selected expense | Expense is removed after confirmation | Functional | High |
| TC-10 | Add custom category | Category appears in category list for the user | Functional | Medium |
| TC-11 | Search expenses by category or description | Matching records are returned | Functional | Medium |
| TC-12 | Verify total spending after multiple entries | Total label equals sum of expenses | Functional | High |
| TC-13 | Verify category chart totals after multiple entries | Pie and bar chart values match grouped amounts | Functional | Medium |
| TC-14 | Enter invalid amount text | Validation message appears and no expense is added | Usability | High |
| TC-15 | Enter negative amount | Validation message appears and no expense is added | Usability | Medium |
| TC-16 | Clear date and attempt update | User receives validation feedback and application does not crash | Functional | Medium |
| TC-17 | Logout from dashboard | Login view is restored | UI | Medium |
| TC-18 | Run integration workflow | Registration, category, expense, totals, and deletion all succeed together | Integration | High |

### Detailed Test Case Descriptions

#### TC-01: Register a New User

- **Preconditions:** Clean or known database state
- **Input/Test Data:** Unique email, valid password
- **Procedure:**
  1. Start the application or test context.
  2. Submit a unique email and password through the registration workflow.
  3. Verify that a `User` object is returned or the dashboard opens.
- **Expected Result:** New user is created successfully and can proceed into the application.

#### TC-05: Invalid Login Attempt

- **Preconditions:** User does not exist or wrong password is used
- **Input/Test Data:** Incorrect email/password pair
- **Procedure:**
  1. Enter incorrect login credentials.
  2. Click `Login`.
  3. Observe authentication response.
- **Expected Result:** Login fails, the user remains on the login screen, and an error message is shown.

#### TC-07: Add Valid Expense

- **Preconditions:** Authenticated user, available category, dashboard visible
- **Input/Test Data:** Category = Food, Amount = 15.50, Description = Lunch, Date = valid current date
- **Procedure:**
  1. Select a category.
  2. Enter amount, description, and date.
  3. Click `Add Expense`.
  4. Refresh or inspect the table and totals.
- **Expected Result:** Expense appears in the table and total spending updates correctly.

#### TC-12: Verify Total Spending

- **Preconditions:** Authenticated user with multiple saved expense records
- **Input/Test Data:** Multiple amounts such as 10.00, 25.00, and 40.00
- **Procedure:**
  1. Save multiple expenses.
  2. Read the total label.
  3. Compare the displayed total to the sum of saved values.
- **Expected Result:** Displayed total matches the mathematical sum of expenses.

#### TC-13: Verify Category Charts

- **Preconditions:** Authenticated user with expenses across multiple categories
- **Input/Test Data:** Example categories Food, Transportation, Healthcare
- **Procedure:**
  1. Enter expenses in multiple categories.
  2. Refresh the dashboard data.
  3. Compare chart values against expected grouped totals.
- **Expected Result:** Pie and bar chart values reflect correct category totals.

#### TC-14: Invalid Amount Entry

- **Preconditions:** Authenticated user, dashboard visible
- **Input/Test Data:** Amount value such as `abc`
- **Procedure:**
  1. Enter non-numeric text into the amount field.
  2. Attempt to add an expense.
- **Expected Result:** Validation message appears and no record is inserted.

#### TC-18: Full Integration Workflow

- **Preconditions:** Test profile active
- **Input/Test Data:** Unique email, custom category, expense amount and description
- **Procedure:**
  1. Register a new user.
  2. Create a custom category.
  3. Add an expense in that category.
  4. Retrieve expenses and totals.
  5. Delete the expense.
- **Expected Result:** Each stage succeeds, totals are correct, and final state matches expectations after deletion.

## Testing Procedures

This section defines the general procedures the team will follow when executing tests.

### General Procedure

1. Identify the test case to be executed.
2. Prepare the database and application state.
3. Execute the test steps exactly as written.
4. Record expected and actual results.
5. Mark the result as Pass, Fail, Blocked, or Not Run.
6. Create a defect record for each failed case.
7. Retest corrected defects before closure.

### Test Data Preparation

1. Use unique email addresses for registration testing.
2. Use known category names such as Food and Transportation for baseline cases.
3. Use simple numeric values for total and chart verification.
4. Use a separate test database file when running integration tests.
5. Reset or isolate state when prior data would affect expected results.

### Reporting Format for Test Execution

Each execution record should include:

1. Test Case ID
2. Tester name
3. Date executed
4. Environment used
5. Input data
6. Expected result
7. Actual result
8. Pass/Fail status
9. Related defect ID, if applicable

### Performance Observation Procedure

Formal load testing is out of scope, but the team will still perform basic responsiveness checks during manual testing. The tester will note whether common actions such as login, add expense, search, and refresh of totals feel immediate and stable under normal use. Any obvious delay, freezing, or chart refresh issue will be logged as a defect for further analysis.

## Testing Schedule

The schedule below aligns testing work with the capstone timeline.

| Unit | Testing Activities | Deliverable Outcome |
| --- | --- | --- |
| Unit 4 | Build initial model, service, repository, and integration tests | Foundational automated coverage |
| Unit 5 | Finalize formal test plan and execute core functional validation | Unit 5 submission and baseline defect list |
| Unit 6 | Run regression testing after Phase II refinements | Confirm updates do not break existing workflows |
| Unit 7 | Perform manual usability walkthroughs while preparing user guide | Confirm instructions match application behavior |
| Unit 8 | Execute final regression pass and summarize results for final report | Final validation evidence |

### Milestone Targets

1. By end of Unit 5: core test plan approved and major high-priority cases defined
2. By end of Unit 6: high-priority functional defects reviewed and addressed
3. By end of Unit 7: usability issues documented and user guide aligned with current build
4. By end of Unit 8: final regression run completed and results summarized

## Testing Resources

### Personnel Resources

| Role | Responsibility |
| --- | --- |
| Joe Merrill | Test-plan consolidation, discussion of defects, and manual validation support |
| Mayank Kulshreshta | Service and model test implementation support |
| Kyle Lewis | Integration and workflow verification support |
| Matthew Lukenich | UI walkthroughs and usability review support |
| Greg Rossi | Database and environment validation support |
| Jamar Watson | Final evidence review and documentation support |

The exact distribution of tasks can be adjusted as needed by the team, but every member should contribute to either automated testing, manual validation, or documentation of results.

### Software and Tool Resources

1. Maven
2. Java 21
3. Spring Boot test framework
4. JUnit 5
5. Mockito
6. TestFX
7. JaCoCo
8. SQLite test database
9. GitHub for source control and review history

### Equipment and Budget

1. Team laptops or desktops
2. Internet access for dependency retrieval and collaboration
3. No additional paid testing software is required

The budget impact of testing is effectively zero because all planned testing tools are open source or already part of the development environment.

## Defect Management

Defect management ensures that issues discovered during testing are documented, prioritized, corrected, and retested in a consistent way.

### Defect Workflow

1. Identify the issue during test execution
2. Reproduce the issue to confirm it is not a one-time anomaly
3. Record the defect with steps to reproduce
4. Assign severity and owner
5. Apply a fix in source control
6. Retest the relevant case
7. Close the defect only after the expected result is achieved

### Defect Severity Levels

1. **Critical** - Prevents use of a major feature such as login, registration, or saving expense data
2. **Major** - Causes incorrect totals, broken CRUD behavior, or data inconsistency
3. **Minor** - Causes a small usability or formatting issue with a workaround
4. **Enhancement** - Improvement idea that does not block release

### Defect Priority Levels

1. **High** - Must be fixed before final submission
2. **Medium** - Should be fixed if time allows
3. **Low** - Can be documented as a limitation if not completed

### Defect Record Fields

1. Defect ID
2. Summary
3. Reporter
4. Date found
5. Environment
6. Severity
7. Priority
8. Steps to reproduce
9. Expected result
10. Actual result
11. Status
12. Owner

## Testing Metrics

The team will use simple but meaningful metrics to evaluate test effectiveness.

1. Number of test cases written
2. Number of high-priority test cases executed
3. Pass/fail rate per test cycle
4. Number of defects found by severity
5. Number of defects retested and closed
6. Availability of automated coverage for core services and workflows

These metrics are appropriate for a capstone project because they provide useful quality signals without requiring a large enterprise reporting system.

## Risks to the Testing Effort

1. **UI automation limitations**  
   Some JavaFX UI tests may not run consistently in headless environments.  
   **Mitigation:** supplement automation with manual walkthroughs and focus automation on service and integration layers.

2. **Shared local database state**  
   Existing data may affect expected results.  
   **Mitigation:** use isolated test databases and unique test data whenever possible.

3. **Compressed schedule near final submission**  
   Late fixes may reduce time available for retesting.  
   **Mitigation:** begin structured testing early and maintain a running defect list.

4. **Environment differences between team members**  
   Java or Maven setup differences may create inconsistent results.  
   **Mitigation:** document environment requirements and standardize build steps.

## Conclusion

This test plan provides the Personal Expense Tracker team with a structured and realistic approach to verifying software quality. It covers the areas required by the Unit 5 assignment while also reflecting the actual architecture and testable workflows of the application. By combining automated testing, manual validation, defect tracking, and scheduled regression checks, the team can improve confidence in both the correctness of the code and the usability of the final product.

## References

1. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
2. Mockito. *Mockito Documentation*. https://site.mockito.org/
3. Spring. *Spring Boot Testing*. https://docs.spring.io/spring-boot/reference/testing/
4. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
5. Project source code, README, and test files in `cmsc495-expense-tracker-feature-unit-tests`
