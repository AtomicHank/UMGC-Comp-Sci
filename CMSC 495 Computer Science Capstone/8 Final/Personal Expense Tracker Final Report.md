# Personal Expense Tracker
## Unit 8 Final Report

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

The Personal Expense Tracker is a desktop application created to help users record, organize, and analyze personal spending. The motivation for the project came from a simple and common problem: many people know they should track expenses, but they do not have a lightweight tool that is easy to run, easy to understand, and simple to maintain. The team selected this project because it addresses a real need while still providing enough technical depth to demonstrate planning, design, implementation, testing, and documentation skills expected in a capstone course.

The final system allows users to register, log in, add and manage expenses, create custom categories, search saved transactions, and review totals with visual summaries. The project also includes diagrams, tests, a user guide, and the formal milestone documents completed during the course. Together, these artifacts show the full software development life cycle from concept through final delivery.

Related work includes spreadsheet-based budgeting, commercial finance dashboards, and basic bookkeeping tools. Our project differs by focusing on a compact academic implementation that can be reviewed end to end. Instead of building a very large product with partial completion, the team chose a focused application with a complete workflow.

## Process Overview

The project followed an iterative process aligned with the course milestones. The team began with project planning and requirements definition, moved into architecture and design, implemented the source code in two phases, created a formal test plan, documented end-user instructions, and then consolidated the work into the final report.

### Tools and Artifacts Used

1. Java 21 for development
2. Spring Boot for application startup and service organization
3. JavaFX for the graphical desktop interface
4. SQLite for embedded local data storage
5. Maven for dependency management and build execution
6. JUnit 5, Mockito, TestFX, and JaCoCo for testing support
7. Mermaid for diagrams
8. Markdown documents for capstone deliverables

### Summary of Individual Contributions

This table should be customized with actual names before submission.

| Role | Typical Contribution Areas |
| --- | --- |
| Project Manager | Coordinated milestones, meetings, and final integration |
| Application Developer | Implemented JavaFX UI and service logic |
| Database and Testing Lead | Managed schema validation, test planning, and integration checks |
| Documentation and QA Support | Maintained reports, diagrams, user guide content, and review consistency |

## Requirements Specification

### Functional Requirements

1. Users can register and log in.
2. The database initializes automatically and seeds default categories.
3. Users can add, update, delete, and search expenses.
4. Users can create custom categories.
5. The dashboard calculates total spending.
6. The application displays category data in pie and bar chart form.
7. Users can log out and return to the login screen.

### Non-Functional Requirements

1. The application runs locally without a separate server.
2. The code is organized into UI, service, model, and repository layers.
3. Data persists through SQLite.
4. The system is supported by automated tests and project documentation.
5. The interface is understandable to a first-time reviewer.

## Project Design

The project uses a layered desktop architecture rather than a distributed web platform. This was an intentional decision. The team determined that a focused desktop application would better support full delivery, documentation quality, and maintainable testing within the capstone timeline.

### Architecture

The system is organized into four main areas:

1. `MainApp` bootstraps Spring Boot and JavaFX
2. The UI layer contains `LoginView`, `DashboardView`, and `SceneNavigator`
3. The service layer contains `AuthenticationService`, `CategoryService`, and `ExpenseService`
4. The persistence layer contains `DatabaseManager` plus SQLite-backed SQL queries

This structure separates user interaction from business logic and database setup, making the project easier to test and explain.

### Data Design

The project uses three main tables:

1. `user`
2. `category`
3. `expense`

The `user` table stores account identity, the `category` table stores default and custom categories, and the `expense` table stores transactions associated with a user and category. This relational structure is intentionally compact so the project remains understandable and stable.

### System Workflow

When the application starts, the database is initialized. A user registers or logs in on the login screen. On successful authentication, the dashboard opens. From there, the user can add expenses, edit selected rows, delete expenses, create categories, search records, and review totals and charts. The service layer coordinates data operations, while the JavaFX UI handles input and rendering.

## Project Evaluation

The team evaluated the project using automated tests, manual walkthroughs, and source review.

### Testing Strategy

1. Unit tests validate model and service logic.
2. Repository testing validates database initialization.
3. Integration testing validates a full user workflow.
4. Manual testing validates the UI and visible calculations.

### Test Plan and Results

The test plan focused on registration, login, category creation, expense CRUD operations, search, totals, and chart aggregation. Test files exist for model behavior, service behavior, repository initialization, and end-to-end integration. The project also contains UI automation scaffolding through TestFX.

### Validation Notes

The current codebase demonstrates that:

1. A user can create an account and authenticate.
2. Expenses can be stored, retrieved, updated, and deleted.
3. Totals and charts respond to saved data.
4. The system can be run locally with standard Java and Maven tooling.

UI tests remain limited in some headless environments because of Java 21 and Monocle compatibility, so the team supplemented automation with manual desktop walkthroughs.

## Design Changes and Alternate Designs

The project evolved over time, and some decisions changed as the team refined the scope.

### Alternatives Considered

1. A larger web or service-based architecture
2. Cloud-hosted persistence and authentication
3. More advanced finance features such as budgeting rules and alerts

### Final Design Decision

The team deliberately narrowed the project to a desktop application because that design:

1. Reduced deployment complexity
2. Kept the focus on core user value
3. Improved the chance of full completion
4. Allowed more time for testing and documentation

### Changes Made Due to Review and Testing

1. Better validation for blank credentials
2. Better validation for invalid amount inputs
3. Portable local database configuration
4. Updated technical diagrams to match the actual codebase
5. Added authentication edge-case test coverage

## Development History

| Unit | Milestone |
| --- | --- |
| Unit 2 | Project plan completed |
| Unit 3 | Project design and requirements finalized |
| Unit 4 | Phase I source code implemented |
| Unit 5 | Formal test plan produced |
| Unit 6 | Phase II source code refined |
| Unit 7 | User guide completed |
| Unit 8 | Final report and presentation outline prepared |
| Unit 9 | Final peer review and reflection |

## Discussion

### Lessons Learned

1. Scope discipline is critical to finishing well.
2. Documentation should evolve with the code, not after it.
3. Tests are most valuable when added early and maintained throughout development.
4. A simpler architecture that the whole team understands is often better than a more ambitious design that cannot be finished.

### Design Strengths

1. Clear user workflow
2. Modular organization by responsibility
3. Local SQLite storage for easy setup
4. Strong alignment between code and documentation

### Limitations

1. The application is local only and does not support synchronization.
2. Password handling is acceptable for coursework but should be strengthened for production use.
3. UI automation remains partially limited by tooling compatibility.
4. The application does not yet support export, budgeting rules, or bank integration.

### Future Improvements

1. Replace the current password hashing approach with BCrypt or Argon2.
2. Add budget limits and monthly reporting filters.
3. Support export to CSV or PDF.
4. Improve packaging for easier distribution to non-developer users.

## Conclusion

The Personal Expense Tracker meets the goals of the capstone by combining a real user problem, a functioning software implementation, layered design, persistent storage, automated testing, and complete supporting documentation. Its greatest strength is coherence: the scope is realistic, the architecture matches the need, and the written deliverables reflect the application that was actually built. The project demonstrates both technical execution and disciplined teamwork.

## Acknowledgement

The team acknowledges the role of the CMSC 495 milestone structure, instructor feedback, and peer review in improving the final quality of the project.

## References

1. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
2. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
3. SQLite. *SQLite Documentation*. https://www.sqlite.org/docs.html
4. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
5. Project source code, diagrams, and documentation in `cmsc495-expense-tracker-feature-unit-tests`

## Appendix A: System Specification

### Minimum Software Requirements

1. Java 21
2. Maven 3.6.3 or newer
3. JavaFX-compatible desktop environment

### Minimum Hardware Requirements

1. Standard student laptop or desktop
2. Local storage for project files and SQLite data
3. Enough memory to run Java, Maven, and the application comfortably

## Appendix B: User Guide Summary

1. Launch the application with Maven from the project directory.
2. Register a new account or log in with an existing account.
3. Use the dashboard to create, update, delete, and search expenses.
4. Add custom categories as needed.
5. Review totals and charts for spending patterns.
6. Log out when finished.
