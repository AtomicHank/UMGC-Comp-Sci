# Personal Expense Tracker
## Unit 2 Project Plan

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Executive Summary

The Personal Expense Tracker is a desktop application that helps users record, organize, and review personal spending. The project addresses a common problem: many users want to track expenses, but they do not have a simple local tool that is easy to run and understand. Our solution is a Java-based desktop program with registration, login, category management, expense tracking, search, and chart-based summaries.

This capstone project is designed to demonstrate the full software development life cycle. In addition to building the application, the team will produce a project plan, design document, source code reports, a test plan, a user guide, and a final report. The project emphasizes clear scope, maintainable code, and practical teamwork rather than unnecessary feature sprawl.

## Project Overview

The application is built with Java 21, JavaFX, Spring Boot, and SQLite. A user can create an account, log in, add expenses, update saved records, delete records, define custom categories, search transactions, and review total spending with pie and bar charts. SQLite is used as the embedded database so the application remains simple to deploy and evaluate in an academic setting.

The project solves the problem of poor visibility into daily spending. While users may remember major bills, smaller purchases are often forgotten. Over time, those small transactions can have a major impact on budgets. By categorizing expenses and presenting totals visually, the application helps users identify where their money is going.

## Project Goals and Objectives

### Goals

1. Deliver a working desktop expense tracker.
2. Demonstrate structured planning, design, implementation, testing, and documentation.
3. Produce a capstone submission that is coherent from requirements through final report.

### Objectives

1. Implement user registration and login.
2. Persist data locally with SQLite.
3. Support expense CRUD operations.
4. Provide default and custom categories.
5. Support search and dashboard summaries.
6. Build automated tests for major logic paths.
7. Document the system for reviewers and end users.

### Deliverables

1. Project Plan
2. Project Design
3. Phase I Source Code Report
4. Test Plan
5. Phase II Source Code Report
6. User Guide
7. Final Report
8. Presentation Outline
9. Final application source code and diagrams

## Project Scope

### In Scope

1. Desktop JavaFX user interface
2. Registration and login
3. Expense creation, update, deletion, and search
4. Default and custom categories
5. Spending totals and charts
6. Local database persistence
7. Automated testing and project documentation

### Out of Scope

1. Bank integration
2. Cloud synchronization
3. Mobile application deployment
4. Receipt scanning
5. Budget alerts and notification services
6. CSV or PDF export

### Constraints

1. The project must fit the capstone timeline.
2. The team must use tools available on standard student hardware.
3. The final product should be complete and stable rather than overloaded with unfinished features.

## Project Resources

### Personnel

| Role | Responsibilities |
| --- | --- |
| Project Manager | Tracks milestones, coordinates meetings, and manages status updates |
| Application Developer | Implements Java, Spring Boot, and JavaFX functionality |
| Database and Testing Lead | Oversees schema design, integration checks, and automated testing |
| Documentation and QA Support | Maintains reports, diagrams, formatting, and user-facing instructions |

### Software and Tools

1. Java 21
2. Maven
3. Spring Boot 3.2
4. JavaFX 21
5. SQLite JDBC driver
6. Git and GitHub
7. JUnit 5, Mockito, TestFX, and JaCoCo
8. Mermaid for diagrams

### Budget

The project is intentionally built on open-source tooling and locally hosted resources. No paid hosting or licensing is required, so the expected cost is effectively zero beyond existing hardware and internet access.

## Communication Plan

Team communication will be simple, regular, and documented.

### Channels

1. Group chat for quick updates
2. Email for formal coordination
3. GitHub for source control and review
4. Shared documents for report drafting

### Frequency

1. One scheduled weekly meeting
2. One progress update from each member before the meeting
3. Extra check-ins before major deadlines

### Responsibilities

1. The project manager prepares the agenda and tracks milestones.
2. Each member reports progress, blockers, and next steps.
3. Code and document changes are reviewed before final integration.

## Milestones

| Unit | Milestone |
| --- | --- |
| Unit 2 | Project plan and role assignment |
| Unit 3 | Project design and requirements |
| Unit 4 | Phase I source implementation |
| Unit 5 | Formal test plan |
| Unit 6 | Phase II source refinement |
| Unit 7 | User guide |
| Unit 8 | Final report and presentation |
| Unit 9 | Final peer review |

## Success Criteria

The project will be successful if:

1. The application supports the full user workflow from login to expense reporting.
2. Data is stored and retrieved correctly.
3. The interface is understandable to a first-time user.
4. The codebase includes meaningful automated tests.
5. The final documents accurately reflect the implemented system.

## Conclusion

This project plan establishes a realistic path for the Personal Expense Tracker capstone. The chosen scope is practical, the technology stack is appropriate, and the milestone structure aligns well with the course schedule. By staying focused on core functionality and strong documentation, the team can deliver a project that is both technically credible and academically complete.

## References

1. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
2. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
3. SQLite. *SQLite Documentation*. https://www.sqlite.org/docs.html
4. Project source code in `cmsc495-expense-tracker-feature-unit-tests`
