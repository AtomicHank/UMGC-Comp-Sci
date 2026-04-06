# Personal Expense Tracker
## Unit 4 Phase I Source Code Report

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

Phase I established the technical foundation for the Personal Expense Tracker. The team used this stage to move from planning and design into a functioning codebase with a working user workflow, persistent storage, early tests, and supporting documentation. The purpose of Phase I was to prove that the project architecture was viable and that the application could support the core expense-tracking problem selected for the capstone.

## Project Setup

The project setup focused on creating a consistent development environment for all team members.

### Setup Components

1. Java 21
2. Maven for build and dependency management
3. Spring Boot for application startup and service organization
4. JavaFX for the desktop UI
5. SQLite for local persistence
6. Git-based version control

This setup created a single codebase that all contributors could build, review, and extend.

## Core Functionality Implemented

Phase I delivered the first complete version of the main application workflow.

### Domain Model

The team implemented the core model classes:

1. `User`
2. `Category`
3. `Expense`

These classes define the project’s data structure and support both UI and persistence logic.

### Database Initialization

The `DatabaseManager` component creates the `user`, `category`, and `expense` tables at startup and seeds default categories such as Food, Transportation, Housing, Entertainment, Healthcare, and Other. This makes the project easier to run on a new machine without manual database setup.

### Authentication

The `AuthenticationService` supports registration and login. It normalizes email addresses, stores password hashes, and returns a `User` object on successful authentication.

### Category Management

The `CategoryService` returns default and user-specific categories and supports adding new custom categories from the dashboard.

### Expense Management

The `ExpenseService` supports the main CRUD workflow:

1. Add expense
2. Update expense
3. Delete expense
4. Retrieve and search expenses
5. Calculate totals and category summaries

### User Interface

Phase I delivered two working views:

1. `LoginView` for registration and login
2. `DashboardView` for expense entry, table display, search, and charts

This was enough to demonstrate the application’s end-to-end purpose.

## Unit Testing in Phase I

The team began testing early instead of postponing it until the end of the course.

### Tests Added

1. Model tests for `User`, `Category`, and `Expense`
2. Service tests for authentication, categories, and expenses
3. Repository test coverage for database initialization
4. Integration coverage for a user workflow
5. UI test scaffolding with TestFX

### Value of Early Testing

These tests improved confidence in the codebase, reduced the risk of silent regressions, and created a strong base for future refinement in Phase II.

## Documentation in Phase I

Phase I also included documentation artifacts so the code would remain understandable as the project grew.

### Documentation Artifacts

1. Environment requirements
2. Architecture diagram
3. Data model diagram
4. Login sequence diagram
5. Project planning and design documents

Documenting the project early helped keep the written deliverables aligned with the actual implementation.

## Collaboration and Implementation

The team used version control, regular check-ins, and task division to support collaborative development.

### Collaboration Practices

1. Shared repository and common project structure
2. Division of work across implementation, testing, and documentation
3. Review of code and writing before integration
4. Ongoing identification of issues to address in Phase II

## Phase I Outcomes

By the end of Phase I:

1. The project built successfully around a consistent Java and Maven setup.
2. The database schema and default data were in place.
3. Users could register and log in.
4. Expenses could be added, updated, deleted, and searched.
5. Categories could be managed through defaults and custom additions.
6. Automated tests and technical documentation were established.

## Remaining Work for Phase II

The next phase will focus on:

1. Refining validation and usability
2. Expanding test coverage
3. Updating documentation to reflect final implementation
4. Finalizing the project for capstone submission

## Conclusion

Phase I successfully transformed the Personal Expense Tracker from a design idea into a functioning software product. The team established the architecture, implemented the core workflow, and created the first round of automated tests and technical documentation. This phase provided the stable base needed for final improvements in Phase II.

## References

1. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
2. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
3. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
4. Project source code and diagrams in `cmsc495-expense-tracker-feature-unit-tests`
