# Personal Expense Tracker
## CMSC 495 User Guide

Prepared for CMSC 495 Computer Science Capstone  
Team Members:
Submission Date:

## Cover Page

## Introduction

The Personal Expense Tracker is a desktop budgeting application designed to help users capture personal spending, organize transactions by category, search historical records, and review totals through charts. The software combines a JavaFX desktop interface, a Spring Boot application layer, and an embedded SQLite database so that users can manage expense data locally without installing a separate database server.

This guide explains how to install and start the application, navigate the interface, complete common tasks, and solve routine problems without developer assistance. The document is intended to support first-time users, instructors reviewing the project, and team members who need a clear operating reference.

### Overview of the Software

The application supports the following core functions:

1. Register a new user account.
2. Log in with an existing account.
3. Add expenses with a category, amount, description, and date.
4. Update an existing expense after selecting it from the table.
5. Delete an expense with confirmation.
6. Add custom categories in addition to the default category list.
7. Search expense history by category, description, or date text.
8. Review total spending and chart-based summaries by category.

### Purpose of the User Guide

The purpose of this guide is to help users:

1. Install the software correctly.
2. Understand the main screens and controls.
3. Perform the system's major tasks in the correct order.
4. Follow basic usage standards for clean and accurate data entry.
5. Resolve common setup and runtime issues independently.

### Target Audience

This guide is written for:

1. Course instructors and project evaluators.
2. End users who want to track personal expenses.
3. Student team members who need an operational reference.

## Getting Started

### System Requirements

The project documentation and build configuration identify the following requirements:

1. Java Development Kit (JDK) 21.
2. Apache Maven 3.6.3 or newer for local builds.
3. JavaFX 21.0.2, resolved automatically through Maven.
4. A Windows, macOS, or Linux environment capable of running JavaFX.
5. Docker Desktop or Docker Engine if the containerized launch option is used.

### Installation Options

Users can run the application in one of two ways:

1. Local Java/Maven setup.
2. Docker container setup with browser-based access through noVNC.

The Docker option is generally the easiest choice for project demonstrations because it avoids local Maven and JavaFX setup.

### Installation Instructions: Local Setup

Use these steps when Java 21 and Maven are already installed on the machine.

1. Download or clone the project repository.
2. Open a terminal in the `cmsc495-expense-tracker-main` folder.
3. Confirm that Java 21 is active by running `java -version`.
4. Confirm that Maven is available by running `mvn -version`.
5. Build the application with `mvn clean install`.
6. Start the desktop application with `mvn javafx:run`.

The application uses a local SQLite file named `expense_tracker.db` in the project directory unless the datasource path is overridden.

### Installation Instructions: Docker Setup

Use these steps when Docker Desktop or Docker Engine is installed.

1. Open a terminal in the `cmsc495-expense-tracker-main` folder.
2. Start the Docker daemon if it is not already running.
3. Run `docker compose up --build`.
4. Open a web browser and go to `http://localhost:6080/vnc.html`.
5. Interact with the JavaFX application inside the browser window.

In the Docker configuration, the SQLite database is stored in a Docker named volume so that user data remains available after the container stops.

### First-Run Behavior

On first launch, the application creates the following database tables automatically:

1. `user`
2. `category`
3. `expense`

The system also seeds these default categories:

1. Food
2. Transportation
3. Housing
4. Entertainment
5. Healthcare
6. Other

### User Interface Overview

The application uses two main screens: the Login screen and the Dashboard.

#### Login Screen

The Login screen includes:

1. A title label for the application name.
2. An email field.
3. A password field.
4. A `Login` button.
5. A `Register` button.
6. A status label for authentication errors.

Recommended screenshot for final submission:

1. Insert an annotated screenshot of the login screen and label each control.

#### Dashboard Screen

After login or registration, the Dashboard appears. It contains:

1. A welcome label showing the currently logged-in email address.
2. A `Logout` button.
3. An expense-entry form.
4. A category dropdown list.
5. Amount, description, and date fields.
6. Buttons for adding, updating, deleting, searching, and adding categories.
7. An expense table.
8. A running `Total Spent` label.
9. A pie chart called `Expense Breakdown`.
10. A bar chart for `Spending by Category`.

Recommended screenshot for final submission:

1. Insert one full annotated dashboard screenshot.
2. Insert a second screenshot focused on the expense table and chart area.

Recommended diagram for final submission:

1. Include the existing login sequence or deployment diagram from the `docs/Visuals` folder as a supporting figure.

## Standards and Guidelines

To make the best use of the software, users should follow these data-entry standards:

1. Use a valid, memorable email address when registering.
2. Enter the password consistently because the application requires the exact password at login.
3. Enter amounts as numeric values only, such as `12.50` or `99.99`.
4. Select a category before saving an expense.
5. Use the date picker to ensure a valid transaction date format.
6. Use short, meaningful descriptions such as `Groceries`, `Gas`, or `Monthly streaming subscription`.
7. Create custom categories only when the default categories do not fit the transaction.
8. Avoid creating duplicate custom categories with slightly different spelling.

These guidelines improve data quality and make searching, totals, and charts easier to interpret.

## Using the Software

### Registering a New User

Use registration the first time you access the application.

1. Open the application.
2. Enter an email address in the email field.
3. Enter a password in the password field.
4. Click `Register`.
5. If registration succeeds, the application automatically opens the Dashboard.

Important behavior:

1. Email addresses are normalized to lowercase before they are stored.
2. If the email already exists, registration fails and the user remains on the login screen.

### Logging In

Use these steps after your account has been created.

1. Enter your registered email address.
2. Enter your password.
3. Click `Login`.
4. If the credentials are correct, the Dashboard opens.

If login fails, the screen displays the message `Invalid email or password.`

### Navigating the Dashboard

The Dashboard is arranged in three primary work areas:

1. Left panel: expense-entry form, category creation, and search.
2. Center panel: expense table and total-spending label.
3. Right panel: pie chart and bar chart reports.

The top of the window displays the logged-in user and the `Logout` button.

### Adding an Expense

Use these steps to record a new transaction.

1. Open the category dropdown and choose a category.
2. Enter the amount in the `Amount` field.
3. Enter an optional note in the `Description` field.
4. Select the transaction date.
5. Click `Add Expense`.

Expected result:

1. The record appears in the expense table.
2. The `Total Spent` label updates.
3. The pie chart and bar chart refresh to include the new amount.

Validation notes:

1. Category, amount, and date are required.
2. If the amount is not numeric, the application displays `Amount must be a valid number.`

### Selecting an Existing Expense

Before updating or deleting a record, you must select it in the table.

1. Click the desired row in the expense table.
2. The form fields automatically populate with that row's category, amount, description, and date.
3. Make edits or continue to deletion.

### Updating an Expense

Use these steps to revise a saved expense.

1. Click the expense row you want to modify.
2. Confirm that the fields populate with the current values.
3. Change one or more fields.
4. Click `Update Selected`.
5. Confirm that the revised data appears in the table and that totals refresh.

If no row is selected, the application displays `Please select an expense to update.`

### Deleting an Expense

Use these steps to remove a record.

1. Select the expense in the table.
2. Click `Delete Selected`.
3. Read the confirmation dialog.
4. Click `OK` to complete the deletion.

Expected result:

1. The expense is removed from the table.
2. The `Total Spent` label updates.
3. The charts refresh automatically.

If no expense is selected, the application displays `Please select an expense to delete.`

### Adding a Custom Category

The application includes default categories, but users may create their own categories as needed.

1. Click `Add Custom Category`.
2. Enter the category name in the dialog box.
3. Click the dialog confirmation button.
4. Open the category dropdown to verify the new category appears.

Best practice:

1. Use category names that are clear and distinct, such as `Books`, `Pet Care`, or `Tuition`.

### Searching Expenses

The search feature filters the expense table based on the text entered in the search field.

1. Type a search term into the `Search` field.
2. Click `Search`.
3. Review the filtered results in the expense table.

The search supports:

1. Category-name matching.
2. Description matching.
3. Transaction-date text matching.

Search behavior:

1. Category and description searches are case-insensitive.
2. Partial matches are supported.
3. Date searches can use partial text such as `2026-03`.

### Reviewing Totals and Reports

The dashboard provides two visual summary tools.

#### Total Spent

The label above the table shows the sum of all expenses for the current user in the format `Total Spent: $0.00`.

#### Pie Chart

The pie chart displays an `Expense Breakdown` by category, allowing users to see which categories consume the largest share of spending.

#### Bar Chart

The bar chart displays `Spending by Category`, allowing users to compare category totals side by side.

### Logging Out

Use logout when you are finished or when a different user needs to access the application.

1. Click `Logout`.
2. The application returns to the login screen.

## Troubleshooting Tips

### Problem: The Application Will Not Start Locally

Possible causes and solutions:

1. The machine is using Java 17 instead of Java 21. Install or switch to JDK 21, then verify with `java -version`.
2. Maven is not installed or not on the system path. Install Maven and verify with `mvn -version`.
3. Project dependencies were not built successfully. Run `mvn clean install` again from the project root.
4. JavaFX modules did not resolve correctly. Use the provided Maven commands instead of trying to run classes manually.

### Problem: The Docker Version Will Not Start

Possible causes and solutions:

1. Docker Desktop is installed but the Docker daemon is not running. Start Docker Desktop and wait for it to finish initializing.
2. Port `6080` is already in use. Stop the conflicting service or change the published port in `docker-compose.yml`.
3. The browser cannot connect to the noVNC page. Confirm that `docker compose up --build` completed successfully and then open `http://localhost:6080/vnc.html`.

### Problem: Registration Fails

Possible causes and solutions:

1. The email may already exist in the database. Try logging in instead.
2. Leading or trailing spaces may have been entered. Re-enter the email and password carefully.
3. The database file may be carrying prior data from an earlier session. If this is a test environment, reset the database only if appropriate for your assignment workflow.

### Problem: Login Fails

Possible causes and solutions:

1. Verify that the email and password match the registered account.
2. Remember that the password must be entered exactly as created.
3. If the account has not been created yet, return to the login screen and use `Register`.

### Problem: I Cannot Add an Expense

Possible causes and solutions:

1. No category was selected.
2. The amount field is blank.
3. The date field is blank.
4. The amount is not in numeric format.

### Problem: Update or Delete Does Not Work

Possible causes and solutions:

1. No row was selected in the expense table.
2. The row was previously filtered out after a search.
3. The selected row was cleared when the form reset after another action.

### Problem: My Search Results Look Incomplete

Possible causes and solutions:

1. The search field is still filtering results from an earlier query.
2. The search term may not match the exact words used in the description.
3. Date searches work best with partial date text such as `2026-04` or full dates such as `2026-04-19`.

### Problem: The Charts Are Empty

Possible causes and solutions:

1. No expenses have been entered yet for the current user.
2. You may be logged into a different account than expected.
3. Expenses may have been deleted, leaving no totals to display.

## Reference

### File and Data Reference

Key project and data locations include:

1. Main application folder: `cmsc495-expense-tracker-main`
2. Main database file for local runs: `expense_tracker.db`
3. Environment requirements: `docs/environment_requirements.md`
4. Architecture and sequence diagrams: `docs/Visuals`

### Glossary of Terms

**Bar Chart**  
A graph that compares total spending across categories using vertical bars.

**Category**  
A label used to group related expenses, such as Food or Housing.

**Custom Category**  
A user-created category added in addition to the default categories.

**Dashboard**  
The main application screen where users manage expenses and view charts.

**Expense**  
A single spending record containing category, amount, description, and date.

**JavaFX**  
The graphical user interface framework used to build the desktop screens.

**Login Screen**  
The first screen of the application where users log in or register.

**SQLite**  
The embedded database engine used to store application data locally.

**Total Spent**  
The calculated sum of all expenses saved for the current user.

### Index

- Add Custom Category
- Add Expense
- Authentication
- Bar Chart
- Dashboard
- Default Categories
- Delete Expense
- Docker Setup
- Expense Breakdown
- Expense Table
- Login
- Logout
- Register
- Search
- System Requirements
- Total Spent
- Troubleshooting
- Update Selected

## Additional Information for Final Submission

To better align this document with the assignment rubric before submission, the team should add the following items to the final Word or PDF version:

1. A separate title page with the final team roster.
2. Annotated screenshots of the Login screen and Dashboard.
3. At least one supporting system diagram from the project documentation.
4. Page numbers, consistent heading styles, and figure captions.
5. Final proofreading for spacing, citations, and team-specific formatting preferences.

## References

1. Project README and source files in `cmsc495-expense-tracker-main`.
2. `docs/environment_requirements.md` in the project repository.
3. OpenJFX Documentation. https://openjfx.io/
4. Spring Boot Reference Documentation. https://docs.spring.io/spring-boot/reference/
5. SQLite Documentation. https://www.sqlite.org/docs.html
