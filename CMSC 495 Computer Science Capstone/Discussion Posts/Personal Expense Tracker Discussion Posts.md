# Personal Expense Tracker Discussion Posts

These are draft discussion posts based on `CMSC 495 Discussions.txt` and the capstone project in `cmsc495-expense-tracker-feature-unit-tests`. Replace bracketed placeholders such as team member names before posting.

## Unit 1 Discussion Post

**Project Topic:** Personal Expense Tracker

**Team Members:** `[Insert Team Member Names]`

**Project Overview:**  
Our group selected a Personal Expense Tracker for our capstone project. The problem we want to address is that many people know they need to track their spending, but they often do not have a simple tool that helps them record daily purchases, organize them into categories, and review where their money is going. Our software will provide a desktop application where users can register, log in, add expenses, manage categories, search previous transactions, and view totals and charts that summarize their spending habits. The goal of the project is to create a practical, easy-to-use application that demonstrates the full software development life cycle while also solving a real personal finance problem for students and everyday users.

### Works Cited

"Personal Expense Tracker Project Plan." *CMSC 495 Computer Science Capstone*, 2026.

"README." *cmsc495-expense-tracker-feature-unit-tests*, 2026.

## Unit 2 Discussion Post

One of the biggest insights I gained from the project planning readings is that a strong project plan does much more than list tasks. It creates shared direction for the team by clearly defining goals, deliverables, scope, roles, timeline, resources, and communication expectations. Without those elements, even a technically strong project can become disorganized because people may have different assumptions about what is being built, who owns what, and how success will be measured.

For our Personal Expense Tracker project, planning forced us to turn a broad idea into a practical deliverable. We defined our goals around building a desktop application that allows users to register, log in, manage expenses, create categories, and review totals with charts. We also had to define what was out of scope, such as bank integration, cloud sync, and mobile support. That limitation was actually helpful because it kept the project realistic for the capstone timeline. We also assigned responsibilities around implementation, testing, database work, and documentation so the workload would be more balanced.

Another lesson from project planning is that communication is part of the plan, not something separate from it. Our group's success depends not only on the technical work but also on how consistently we share updates, surface blockers, and review each other's contributions. A well-defined project plan provides that structure early, which reduces confusion later when deadlines start to overlap.

### Works Cited

"Personal Expense Tracker Project Plan." *CMSC 495 Computer Science Capstone*, 2026.

Spring. "Spring Boot Reference Documentation." *Spring*, https://docs.spring.io/spring-boot/reference/. Accessed 6 Apr. 2026.

## Unit 3 Discussion Post

The project design readings reinforced for me that effective design is really about creating a roadmap from goals to implementation. A good design identifies what the system must do, what it will not do, how the work will be structured, how progress will be evaluated, and what risks could interfere with success. In other words, project design connects the original project idea to actual execution.

For our Personal Expense Tracker, one of the most important design decisions was to keep the architecture focused and achievable. Instead of designing a large cloud-based system, we chose a layered desktop application using JavaFX, Spring Boot, and SQLite. That decision helped us define clear boundaries: the system would support local registration, expense CRUD operations, category management, search, and charts, but it would not include online synchronization or external financial integrations. I think this is an example of good design because it aligns the architecture with the project's academic goals and time constraints.

Another important design lesson is that success criteria need to be established early. For our project, success is not just "the app runs." It also means the user can complete the main workflow reliably, the data persists correctly, the interface is understandable, and the code is organized well enough to support testing and documentation. One challenge in project design is resisting the temptation to overdesign. In my experience, a simpler design that the whole team understands is more effective than a more ambitious design that becomes difficult to finish or explain.

### Works Cited

"Personal Expense Tracker Project Design." *CMSC 495 Computer Science Capstone*, 2026.

OpenJFX. "JavaFX Documentation." *OpenJFX*, https://openjfx.io/. Accessed 6 Apr. 2026.

SQLite. "SQLite Documentation." *SQLite*, https://www.sqlite.org/docs.html. Accessed 6 Apr. 2026.

## Unit 4 Discussion Post

From the Phase I source code readings, one of the clearest lessons is that early source code should do more than prove that the project can compile. A well-structured Phase I codebase should establish the project environment, implement the foundation of the main workflow, introduce documentation practices, and create the first layer of testing. That foundation makes later development more stable and less chaotic.

In our Personal Expense Tracker project, Phase I focused on building the core structure of the application. We set up the project with Java 21, Maven, Spring Boot, JavaFX, and SQLite. We implemented the main model classes, database initialization, authentication workflow, category retrieval, expense CRUD logic, and the first working UI views. We also started test coverage for the models, services, repository initialization, and an integration workflow. For me, this phase highlighted how important naming, package structure, and consistent UI identifiers are, because those small decisions affect readability and make testing easier later.

Another key takeaway is that documentation and version control are part of coding quality, not optional add-ons. As our codebase grew, it became clear that tracking changes, reviewing logic, and documenting the architecture were necessary to keep the project understandable across team members. The biggest challenge I anticipate in Phase I and beyond is avoiding rushed changes that solve one short-term problem but create confusion later. Our strategy for dealing with that is to keep the architecture simple, add tests as we go, and review code against the project scope instead of just adding features because they seem interesting.

### Works Cited

"Personal Expense Tracker Phase I Source Report." *CMSC 495 Computer Science Capstone*, 2026.

"README." *cmsc495-expense-tracker-feature-unit-tests*, 2026.

JUnit Team. "JUnit 5 User Guide." *JUnit 5*, https://junit.org/junit5/docs/current/user-guide/. Accessed 6 Apr. 2026.

## Unit 5 Discussion Post

The testing readings made it clear that a test plan is not just a checklist of things to click on. A strong test plan defines the goals of testing, the types of testing to be used, the scope of what will and will not be tested, the procedures for executing tests, the schedule, the resources, and the process for recording defects. That structure matters because quality is easier to maintain when testing is planned instead of improvised.

For our Personal Expense Tracker, we are using a mix of unit testing, repository testing, integration testing, and manual UI validation. Unit tests help us confirm that service logic and model behavior are working correctly. Repository and integration tests help verify database initialization and end-to-end workflows like registering a user, creating a category, adding an expense, and checking totals. We also have UI testing scaffolding, although some environments make full UI automation difficult, so manual walkthroughs remain important. I think this combination is appropriate because it balances realism with the size of the project.

One insight I gained is that effective test cases need to reflect both common use and failure conditions. For example, we should not only test valid expense creation, but also blank fields, invalid amounts, and incorrect login attempts. Metrics such as pass/fail rates, defect counts, retest outcomes, and coverage of key workflows can help measure the effectiveness of our testing effort. In the end, a good test plan improves software quality because it creates repeatable expectations for verification instead of depending on memory or last-minute checking.

### Works Cited

"Personal Expense Tracker Test Plan." *CMSC 495 Computer Science Capstone*, 2026.

JUnit Team. "JUnit 5 User Guide." *JUnit 5*, https://junit.org/junit5/docs/current/user-guide/. Accessed 6 Apr. 2026.

Mockito. "Mockito Documentation." *Mockito*, https://site.mockito.org/. Accessed 6 Apr. 2026.

Spring. "Testing." *Spring Boot Reference Documentation*, https://docs.spring.io/spring-boot/reference/testing/. Accessed 6 Apr. 2026.

## Unit 6 Discussion Post

The Phase II source code readings emphasized that finalizing code is not just about adding the last features. It is also about clarity, completeness, accuracy, and documentation. In a capstone project, that matters because the final deliverable is judged not only by whether the software works, but also by whether the team can explain what was built, how it works, and how quality was maintained.

For our Personal Expense Tracker, our strategy in Phase II has been to review the application and the documents together rather than treating them as separate workstreams. That means updating the project documentation so it matches the real desktop architecture, refining validation in the source code, expanding tests around edge cases, and making sure the README, diagrams, user guide, and final report all describe the same system. I think this is especially important because teams can easily drift into a situation where the software and the written documents no longer match.

Another lesson I am taking from this phase is that constructive peer feedback should be specific and improvement-oriented. Good feedback should identify what is unclear, incomplete, or inconsistent and explain why it matters. In our group context, that means reviewing not just whether a feature exists, but whether the code is readable, whether the documentation is accurate, and whether the final report reflects the current build. The biggest challenge in Phase II is managing the pressure of final deliverables without sacrificing quality, so our best strategy is to keep changes deliberate and to verify them as we go.

### Works Cited

"Personal Expense Tracker Phase II Source Report." *CMSC 495 Computer Science Capstone*, 2026.

"README." *cmsc495-expense-tracker-feature-unit-tests*, 2026.

"deployment_architecture.mmd." *cmsc495-expense-tracker-feature-unit-tests/docs/Visuals*, 2026.

## Unit 7 Discussion Post

One of the most important takeaways from the user guide readings is that a user guide should be written from the user's perspective, not the developer's perspective. It is easy for a development team to assume certain steps are obvious because we built the application, but a good user guide explains how to install, launch, navigate, and troubleshoot the software in a way that is clear to someone seeing it for the first time.

For our Personal Expense Tracker, the most important parts of the user guide are the installation instructions, the explanation of the login and dashboard screens, the step-by-step directions for adding and managing expenses, and the troubleshooting section. Since the application is a desktop system rather than a web app, it is especially important to explain the environment requirements and startup process clearly. I also think annotated screenshots are valuable because they reduce ambiguity and make the workflow easier to follow.

The biggest challenge I anticipate in writing a comprehensive user guide is keeping it aligned with the actual interface as the project is refined. Even small changes in labels, buttons, or validation behavior can make a guide feel outdated very quickly. Because of that, I think the best user guide process is to treat the guide as a living document and review it alongside the actual application before final submission.

### Works Cited

"Personal Expense Tracker User Guide." *CMSC 495 Computer Science Capstone*, 2026.

"Environment Requirements." *cmsc495-expense-tracker-feature-unit-tests/docs*, 2026.

OpenJFX. "JavaFX Documentation." *OpenJFX*, https://openjfx.io/. Accessed 6 Apr. 2026.

## Unit 8 Discussion Post

For this week's presentation discussion, our project is the Personal Expense Tracker, a desktop application designed to help users record and review personal spending in a simple and organized way. The significance of the project is that it addresses a practical everyday problem while also allowing our group to demonstrate the complete software development life cycle, including planning, design, implementation, testing, documentation, and reflection.

In a short project demonstration, I would show the user registering or logging in, opening the dashboard, creating a category, adding an expense, searching transactions, and reviewing the total spending and category charts. That workflow shows the core value of the system in just a few steps. What went well in this project was our decision to keep the scope focused. By choosing a manageable architecture and a clear feature set, we were able to produce a more complete application and stronger supporting documentation.

At the same time, the project also taught us what we could have done better. We could have improved early coordination around documentation updates so that some of the later refinement work would have been smaller. We also saw that UI automation can be harder than expected depending on environment constraints, which reinforced the importance of combining automated tests with manual validation. Overall, the project helped us understand that strong software engineering is not only about building features, but also about making thoughtful tradeoffs, testing carefully, and documenting clearly.

### Works Cited

"Personal Expense Tracker Final Report." *CMSC 495 Computer Science Capstone*, 2026.

"Personal Expense Tracker Presentation Outline." *CMSC 495 Computer Science Capstone*, 2026.

"README." *cmsc495-expense-tracker-feature-unit-tests*, 2026.

## Optional Video Script for Unit 8

Hello everyone, our capstone project is called Personal Expense Tracker. Our team created a desktop application that helps users record, organize, and review personal spending. We chose this project because expense tracking is a real-world problem that many people struggle with, especially when small purchases add up over time.

Our application allows a user to register, log in, add expenses, create categories, search saved transactions, and review total spending with pie and bar charts. The system is built with Java 21, Spring Boot, JavaFX, and SQLite. That technology stack gave us a good balance between a graphical interface, maintainable application structure, and local persistence.

If I were demonstrating the software live, I would show the login screen first, then the dashboard, where the user can add an expense by selecting a category, entering an amount, choosing a date, and saving the record. I would then show how the table updates, how search works, and how the charts reflect the stored expense data.

One thing that went well in this project was our decision to keep the scope realistic. That helped us complete the core functionality and build supporting documents such as the test plan, user guide, and final report. One thing we could have done better was coordinate document updates earlier so our written materials stayed aligned with the source code throughout the project instead of needing more refinement near the end.

Overall, this capstone taught us that good software development depends on planning, teamwork, testing, and documentation just as much as coding. Thank you for watching.

### Works Cited

Use the same works cited listed for the Unit 8 discussion post above if you convert this script into your spoken presentation notes or submission text.
