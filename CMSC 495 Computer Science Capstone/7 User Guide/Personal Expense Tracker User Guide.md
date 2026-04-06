# Personal Expense Tracker
## Unit 7 User Guide

Prepared for CMSC 495 Computer Science Capstone  
Team Members: `[Insert Team Member Names]`  
Submission Date: `[Insert Date]`

## Introduction

The Personal Expense Tracker is a desktop application that helps users record, organize, and review personal spending. This guide explains how to install the software, start the program, navigate the interface, and use the main features successfully.

### Purpose of This Guide

This guide helps users:

1. Install and launch the application
2. Understand the main screens
3. Create an account and log in
4. Add, update, delete, and search expenses
5. Create custom categories
6. Review totals and charts
7. Troubleshoot common problems

### Target Audience

1. Instructors and project reviewers
2. First-time users
3. Team members who need an operating reference

## Getting Started

### System Requirements

1. Java 21
2. Maven 3.6.3 or higher
3. A desktop OS that supports JavaFX

### Installation Instructions

1. Download or clone the project repository.
2. Open a terminal in the `cmsc495-expense-tracker-feature-unit-tests` folder.
3. Run `mvn clean install`.
4. Start the application with `mvn javafx:run` or `mvn spring-boot:run`.

The application creates a local SQLite database file named `expense_tracker.db` the first time it runs.

## User Interface Overview

### Login Screen

The login screen contains:

1. Email field
2. Password field
3. `Login` button
4. `Register` button
5. Status label for success or error messages

Suggested final submission improvement: insert a screenshot of the login screen with labels.

### Dashboard Screen

The dashboard contains:

1. Welcome label
2. Logout button
3. Expense entry form
4. Add, update, delete, search, and category buttons
5. Expense table
6. Total spending label
7. Pie chart and bar chart

Suggested final submission improvement: insert an annotated screenshot of the dashboard.

## Using the Software

### Creating an Account

1. Open the application.
2. Enter an email address.
3. Enter a password.
4. Click `Register`.
5. If successful, the dashboard opens automatically.

### Logging In

1. Enter your email address.
2. Enter your password.
3. Click `Login`.
4. If the credentials are correct, the dashboard appears.

### Adding an Expense

1. Select a category.
2. Enter an amount.
3. Enter an optional description.
4. Choose a date.
5. Click `Add Expense`.

The expense will appear in the table and the totals will refresh.

### Updating an Expense

1. Click a row in the table.
2. Edit the populated form fields.
3. Click `Update Selected`.
4. Confirm the table reflects the change.

### Deleting an Expense

1. Select an expense in the table.
2. Click `Delete Selected`.
3. Confirm the deletion in the dialog box.

### Adding a Custom Category

1. Click `Add Custom Category`.
2. Enter the category name.
3. Confirm the dialog.
4. The category becomes available in the drop-down list.

### Searching Expenses

1. Type a keyword into the search field.
2. Click `Search`.
3. Review the filtered results in the table.

You can search by category name, description text, or date text.

### Reviewing Totals and Charts

1. Read the `Total Spent` label above the table.
2. Review the pie chart for proportional category spending.
3. Review the bar chart for category comparisons.

### Logging Out

1. Click `Logout`.
2. The application returns to the login screen.

## Troubleshooting Tips

### The Application Will Not Start

1. Confirm that Java 21 is installed.
2. Confirm that Maven is available from the command line.
3. Run `mvn clean install` again to restore dependencies if needed.

### Login Fails

1. Check that the email and password are correct.
2. Check for accidental spaces in the fields.
3. If the account does not exist, use `Register` first.

### I Cannot Add an Expense

1. Make sure a category is selected.
2. Make sure the amount is a valid number.
3. Make sure a date is selected.
4. Make sure the amount is zero or greater.

### Totals or Charts Look Wrong

1. Confirm that the expected expenses were saved.
2. Confirm that you are logged in as the intended user.
3. Clear or adjust the search text if the table appears filtered.

## Reference

### Glossary

**Category**  
A label such as Food or Transportation used to organize expenses.

**Dashboard**  
The main screen where expense records are managed and summarized.

**Expense**  
A stored transaction containing amount, date, category, and optional description.

**SQLite**  
The embedded database used to store data locally.

### Index

- Add Category
- Add Expense
- Dashboard
- Delete Expense
- Login
- Logout
- Register
- Search
- Total Spent
- Update Expense

## Conclusion

The Personal Expense Tracker is designed to be simple enough for a first-time user to understand quickly. By following this guide, a reviewer or end user should be able to install the application, create an account, manage expenses, and interpret the summary charts without additional help.

## References

1. OpenJFX. *JavaFX Documentation*. https://openjfx.io/
2. Spring. *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/reference/
3. Project source code, README, and diagrams in `cmsc495-expense-tracker-feature-unit-tests`
