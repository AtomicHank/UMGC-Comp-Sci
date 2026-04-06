# Personal Expense Tracker
## Unit 3 Project Design

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

This design document turns the project plan into an implementation roadmap. The Personal Expense Tracker is designed as a local desktop application that helps users register, log in, record expenses, organize them by category, search transaction history, and review totals through charts. The design favors a clean layered structure over unnecessary complexity so the team can complete the capstone with a stable and testable application.

The project uses JavaFX for the interface, Spring Boot for application organization, and SQLite for embedded storage. This combination gives the team a realistic but manageable technical stack for the capstone timeline.

## Project Scope

### Objectives

1. Provide a working registration and login workflow.
2. Support expense creation, update, deletion, and search.
3. Provide default and custom categories.
4. Display total spending and category-based charts.
5. Deliver code, tests, diagrams, and written documentation.

### Boundaries

1. The application is desktop only.
2. Data is stored locally, not in the cloud.
3. The system focuses on manual expense entry rather than automatic import.
4. The project is an academic prototype, not a commercial finance platform.

## Project Requirements

### Functional Requirements

1. Users shall be able to register and log in.
2. The system shall initialize its database automatically.
3. Users shall be able to add, update, and delete expenses.
4. Users shall be able to create custom categories.
5. Users shall be able to search expenses by text.
6. The system shall calculate total spending for the current user.
7. The system shall display expense data in table and chart form.

### Non-Functional Requirements

1. The user interface shall be easy to understand.
2. Data shall persist between application runs.
3. The codebase shall be modular enough for unit testing.
4. The project shall run on standard student hardware with Java and Maven.
5. The system shall be supported by documentation and diagrams.

## Architecture and Design

The project uses a layered desktop architecture.

### Presentation Layer

1. `LoginView` manages login and registration interactions.
2. `DashboardView` manages expense entry, search, tables, and charts.
3. `SceneNavigator` handles movement between scenes.

### Service Layer

1. `AuthenticationService` manages registration and login validation.
2. `CategoryService` manages default and custom categories.
3. `ExpenseService` manages expense CRUD, search, totals, and category summaries.

### Persistence Layer

1. `DatabaseManager` creates the `user`, `category`, and `expense` tables.
2. The same component seeds default categories.
3. SQLite stores all application data locally.

### Data Model

The system centers on three entities:

1. `User`
2. `Category`
3. `Expense`

The relationships are simple and intentionally compact so the project stays easy to understand, test, and document.

## Project Methodology

The team uses a lightweight Agile-style approach because the course is milestone-driven and the application can be developed iteratively.

### Why This Method Works

1. Features can be implemented in small increments.
2. Weekly submissions naturally map to iterations.
3. Feedback from each milestone can improve the next one.
4. Documentation can evolve alongside the code.

## Work Breakdown Structure

| Work Package | Description | Owner |
| --- | --- | --- |
| WP1 | Finalize requirements and scope | Project Manager |
| WP2 | Configure Java, Maven, JavaFX, and SQLite | Application Developer |
| WP3 | Create schema and initialization logic | Database and Testing Lead |
| WP4 | Implement authentication workflow | Application Developer |
| WP5 | Implement dashboard UI and expense table | Application Developer |
| WP6 | Implement expense CRUD and search | Application Developer |
| WP7 | Implement category management | Database and Testing Lead |
| WP8 | Build tests and validate workflows | Database and Testing Lead |
| WP9 | Maintain diagrams and reports | Documentation and QA Support |

## Project Schedule

| Unit | Planned Work |
| --- | --- |
| Unit 2 | Project plan and team alignment |
| Unit 3 | Requirements and design finalization |
| Unit 4 | Phase I source code and setup |
| Unit 5 | Test planning and verification |
| Unit 6 | Phase II source refinement |
| Unit 7 | User guide completion |
| Unit 8 | Final report and presentation preparation |
| Unit 9 | Final peer review |

## Project Risks and Mitigation

### Technical Risks

1. JavaFX or Maven setup issues  
   Mitigation: standardize environment requirements early.

2. Database query or schema errors  
   Mitigation: keep the schema small and test CRUD workflows early.

3. UI automation limitations in headless environments  
   Mitigation: combine automated lower-layer tests with manual UI walkthroughs.

### Team Risks

1. Uneven workload distribution  
   Mitigation: assign clear ownership for tasks and documents.

2. Communication delays  
   Mitigation: require weekly check-ins and early blocker reporting.

3. Late document integration  
   Mitigation: update reports continuously rather than waiting until the end.

## Project Evaluation Plan

The project will be evaluated through:

1. Feature completion against scope
2. Automated tests and manual walkthroughs
3. Consistency between source code and written reports
4. Usability of the interface for a first-time reviewer
5. Team ability to explain architecture, testing, and tradeoffs

## Conclusion

This design gives the team a realistic roadmap for implementing the Personal Expense Tracker. The layered structure keeps the code understandable, the scope remains achievable, and the milestone plan supports steady progress through the capstone. By staying disciplined about scope and documentation, the team can deliver a complete project instead of an overextended one.

## References

1. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
2. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
3. SQLite. *SQLite Documentation*. https://www.sqlite.org/docs.html
4. Project source code and diagrams in `cmsc495-expense-tracker-feature-unit-tests`
