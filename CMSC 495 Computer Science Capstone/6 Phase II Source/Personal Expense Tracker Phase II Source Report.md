# Personal Expense Tracker
## Unit 6 Phase II Source Code Report

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

Phase II moved the Personal Expense Tracker from a working prototype into a more refined and submission-ready application. In this stage, the team focused on confirming project setup, strengthening functionality, improving validation, updating documentation, and preparing the codebase for final review.

## Finalized Project Setup

The team rechecked the full environment to ensure all members could build and run the project consistently.

### Final Setup Components

1. Java 21
2. Maven dependency management
3. Spring Boot application startup
4. JavaFX desktop views
5. SQLite local database
6. Test resources for integration validation
7. Mermaid diagrams and Markdown documentation

## Finalized Core Functionality

### User Workflow

1. Users can register from the login screen.
2. Users can authenticate with valid credentials.
3. Blank credential cases are rejected.
4. Successful login opens the dashboard.

### Expense Workflow

1. Users can add expenses with category, amount, description, and date.
2. Users can update selected expenses.
3. Users can delete selected expenses after confirmation.
4. Invalid amount values are rejected.

### Category Workflow

1. Default categories are seeded automatically.
2. Users can add custom categories.
3. Category lists combine default and user-defined entries.

### Reporting Workflow

1. The dashboard lists the current user’s expenses.
2. Search filters the table by category, description, or date text.
3. The total label reflects current stored data.
4. Pie and bar charts summarize spending by category.

## Complete Testing and Validation

Phase II emphasized verification so the final project would not rely only on demonstration.

### Test Coverage Areas

1. Model classes
2. Authentication service
3. Category service
4. Expense service
5. Database initialization
6. End-to-end user workflow
7. UI test scaffolding

### Testing Notes

UI tests are present but still limited by Java 21 headless compatibility constraints in some environments. The team handled this by combining automated lower-layer tests with manual desktop walkthroughs.

## Documentation Updates

Phase II also improved documentation quality. The project now includes:

1. Environment requirements
2. Updated architecture, data model, and login sequence diagrams
3. README overview and run instructions
4. Capstone reports aligned with the current implementation
5. User-focused operating instructions

## Collaboration and Final Implementation

Finalization required coordination across code, testing, and documentation.

### Collaboration Activities

1. Review open issues from Phase I
2. Split ownership across implementation, testing, and writing
3. Reconcile written deliverables with the actual codebase
4. Update technical diagrams to match the desktop architecture
5. Review changes before final integration

## Code Quality Improvements

Phase II refinement included:

1. Better validation of blank credentials
2. Better validation of invalid amount inputs
3. Portable local database configuration
4. Updated technical diagrams
5. Additional authentication edge-case tests

## Peer Review Preparation

The following questions can guide peer review of the Phase II report and source code:

1. Does the report accurately describe the application that was implemented?
2. Are the architecture and workflow explanations clear?
3. Does the source code demonstrate completion of the project’s core requirements?
4. Are the testing artifacts meaningful and aligned with major workflows?
5. What still needs refinement before final submission?

## Remaining Work Before Final Submission

1. Consolidate testing and lessons learned into the final report
2. Polish the user guide
3. Finalize presentation materials
4. Complete final peer reviews
5. Run one last regression pass

## Conclusion

Phase II brought the Personal Expense Tracker to a final-project level of completeness. The application now includes the main planned features, a working interface, supporting tests, and updated documentation. This phase improved the project’s overall quality and positioned it for a coherent final submission rather than a collection of disconnected milestone files.

## References

1. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
2. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
3. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
4. Project source code, README, and diagrams in `cmsc495-expense-tracker-feature-unit-tests`
