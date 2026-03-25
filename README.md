# 🚀 Admin_Portal

Welcome to the Admin_Portal repository.

📌 This project contains a real-time UI Automation Testing Framework developed for the JavaByKiran / TheKiranAcademy web application.

⚙️ The framework is built using Selenium WebDriver, Java, TestNG, and the Page Object Model (POM) design pattern.
It implements a Hybrid Test Automation Framework that combines Data-Driven Testing (DDT), reusable page objects, and reporting utilities to efficiently execute and manage automated test cases.

🧪 The automation framework covers several important application modules including:

✔ 🔐 Login  
✔ 📝 Registration  
✔ 📊 Dashboard  
✔ 👤 Users Management  
✔ ☎ Operators  
✔ 🔗 Useful Links  
✔ 📥 Downloads

___
📊 Test Data Strategy

Different modules use different data sources to demonstrate multiple DDT approaches:

🔐 Login Module → Test data stored in JSON files, parsed using the Jackson library

👤 Add User Module → Test data stored in JSON files

📂 Other Modules (Operators, Downloads, Useful Links, etc.) → Test data stored in Excel files

This approach demonstrates a Hybrid Data-Driven framework where the automation suite can read test data from multiple sources.
___
## 🧰 Tech Stack


| Technology            | Purpose                  |
| --------------------- | ------------------------ |
| ☕ Java               | Programming Language     |
| 🌐 Selenium WebDriver | Browser Automation       |
| 🧪 TestNG             | Test Execution Framework |
| 📄 DDT Apache POI     | Excel Test Data Handling |
| 📦 DDT Jackson        | JSON Test Data Parsing   |
| ✨ Page Object Model(POM) | Design Pattern       |
| 📊 Extent Reports     | HTML Test Reporting      |
| 📝 Log4j2             | Logging Framework        |
| ⚙️ Maven              | Dependency Management    |
| 🔧 Git / GitHub       | Version Control          |
| 🔧   CI/CD            |      Jenkins             |

---
🔗 Application Under Test (AUT)

JavaByKiran / TheKiranAcademy Web Application

*Features automated:

✔ Login & Registration

✔ Dashboard validations

✔ User management

✔ Operators listing

✔ Downloads page

✔ Useful links navigation
___
🌍 Cross Browser Execution

• 🌐 Google Chrome

• 🦊 Mozilla Firefox

• 🔷 Microsoft Edge

___

⭐The Login Page is the entry point of the JavaByKiran / TheKiranAcademy web application.
It authenticates users using Email and Password and grants access to the application dashboard upon successful login.
This page is a critical functional area, as all secured modules depend on successful authentication.

⚙️The following validations are automated on the Login page:

✔ Email input field validation

✔ Password input field validation

✔ Login button functionality

✔ Successful login navigation to Dashboard

✔ Handling of invalid login credentials

✔ Verification of error messages (if credentials are incorrect)

<img width="1273" height="662" alt="Image" src="https://github.com/user-attachments/assets/3a34fece-6d32-4b0d-9feb-804dcef43150" />
____


⭐The Register Page allows new users to create an account in the JavaByKiran / TheKiranAcademy application.
This page captures essential user details and enables successful registration before logging into the system.
It plays a key role in validating new user onboarding workflows.

🧪 Automation Points Covered

- **Verify Register page loads successfully:**
- **Validate Name input field:**
- **Validate Mobile number input field:**
- **Validate Email address input field:**
- **Validate Password input field:**
- **Verify Sign In / Register button functionality:**
- **Verify navigation to the Login page after successful registration:**
- **register new  membership link:**

<img width="1265" height="655" alt="Image" src="https://github.com/user-attachments/assets/179eb097-52db-4301-bf3d-cf28b620065e" />

---

⭐ Dashboard is the landing page after successful login, Displays available courses offered by JavaByKiran , and acts as the main navigation hub of the application

🧪 Automation Points Covered
---

• Verify Dashboard page loads successfully after login

• Validate Dashboard page title and heading

• Verify visibility of all course cards

• Validate course names displayed correctly

• Verify More Info button is present for each course

• Validate navigation menu items on left panel

• Verify Logout option availability

📚 Courses Displayed on Dashboard
---
• Selenium – Automation Testing

• Java / J2EE – Software Development

• Python – Data Science

• PHP – Web Development

<img width="1279" height="635" alt="Image" src="https://github.com/user-attachments/assets/b7522315-ee64-42b2-8cdf-32d9d0a7a67d" />

____

⭐Add user page: Allows admin to add new users to the system, captures complete user information, Used for user management functionality

🧪 Automation Points Covered
---
- **Verify Add User page loads correctly:**
- **Validate Username input field:**
- **Validate Mobile number input field:**
- **Validate Email input field:**
- **Validate Course input field:**
- **Validate Gender radio button selection:**
- **Validate State dropdown selection:**
- **Validate Password field:**
- **Verify Submit button functionality:**
- **Verify Cancel button functionality:**

<img width="1268" height="658" alt="Image" src="https://github.com/user-attachments/assets/5808d14b-1e3b-47be-9c06-b1cc8f47df86" />

___

⭐ Operator page displays a list of operators available for technical and administrative support, Shows operator name, purpose, preferred communication methods, contact number, and working timings, and helps users quickly connect with appropriate support staff

⬢ Automation Scope Implemented
---
- **Validate Operators page navigation from Dashboard:**
- **Verify Operators page title is displayed:**
- **Validate Operators table is present:**
- **Validate minimum number of operator records displayed:**
- **Print all operator names for verification and logging:**
- **Operators page header visibility:**

<img width="1276" height="664" alt="Image" src="https://github.com/user-attachments/assets/a8c7b391-5d03-47cd-bf0e-f2fc02c745da" />

___


⭐UsefullLink page displays important learning, interview, and placement-related resources. Requires an active internet connection, Acts as a centralised resource hub for students

⬢ Automation Scope Implemented
---
- **Validate Useful Links page navigation from Dashboard:**
- **Verify Useful Links page title is displayed:**
- **Validate the links table is present:**
- **Validate total number of links displayed:**
- **Validate if all content names are present there or not:**

<img width="1280" height="636" alt="Image" src="https://github.com/user-attachments/assets/2dd994f4-a408-458b-b344-8a5b26fa744f" />

____

⭐Download page displays a list of required software and tools for automation setup,Helps users download correct versions of browsers, drivers, and dependencies,Acts as a one-stop setup reference for Selenium automation

⬢ Automation Scope Implemented
---
- **Validate Downloads page navigation from Dashboard:**
- **Verify Downloads page title is displayed:**
- **Validate downloads table presence:**
- **Validate software names and vendor information:**
- **Verify Official Website buttons:**

<img width="1259" height="652" alt="Image" src="https://github.com/user-attachments/assets/f5273feb-8a9c-4153-bbbb-5c79532b6f90" />

___

Here, I did cross-browser testing 

⬢ Test Execution Summary

<img width="1280" height="678" alt="Image" src="https://github.com/user-attachments/assets/fd8798e0-3fca-4409-8fd1-497512de41ce" />
___

Report

<img width="1277" height="656" alt="Image" src="https://github.com/user-attachments/assets/5d2d6822-dc1c-4f3c-82a8-c29487ba5909" />


<img width="1277" height="656" alt="Image" src="https://github.com/user-attachments/assets/ce050804-6ec1-43f9-892f-0b62ffbd1be1" />

___

## 🔧 Jenkins CI Execution

This project is integrated with Jenkins for Continuous Integration.  
Below is the sample build execution output:

📸 Jenkins Build Dashboard
<img width="1190" height="347" alt="Image" src="https://github.com/user-attachments/assets/e1994966-b140-4ed1-a95a-6700134cf916" />

## 📊 Console Output
<img width="1212" height="159" alt="Image" src="https://github.com/user-attachments/assets/4cc47df3-5a12-45bd-a926-a8db6dddd596" />

✅ Test Execution Summary
<img width="651" height="229" alt="Image" src="https://github.com/user-attachments/assets/957c82b7-dea9-45a7-b7d3-c2f1f008ae96" />

🔹 Using Jenkins

Configure job with GitHub repository

Set branch: main

Add Maven goal: clean test

Click Build Now



==============================================
# QA Automation Framework - Learning Guide

This project is a Java Selenium TestNG framework built for learning production-style UI automation patterns.

## 1. Project Architecture

Core folders:

- `src/test/java/Base`:
  - `BaseClass`: WebDriver setup/teardown, browser handling, app bootstrap.
  - `BasePage`: parent for all Page Objects.
- `src/test/java/POM`:
  - Page Object classes for Login, Register, Dashboard, Users, Add User, Operators, Useful Links, Downloads.
- `src/test/java/TestClass`:
  - TestNG test classes `TC_001` to `TC_007`.
- `src/test/java/Utility`:
  - `ExcelUtil`, `JsonUtil`, `LoginUtil`, `ExtentManager`.
- `src/test/java/Listeners`:
  - `ExtentTestListener` for reporting + screenshots on failure.
- `testdata`:
  - `excel.xlsx` and `testdata.json` (data-driven sources).
- `testng.xml`:
  - Suite configuration and listener registration.

## 2. Execution Flow

1. TestNG starts suite from `testng.xml`.
2. Listener (`ExtentTestListener`) initializes Extent report.
3. Suite runs in parallel at TestNG `<test>` level:
   - `ChromeTest`
   - `FirefoxTest`
   - `EdgeTest`
4. Each test class extends `BaseClass`, so per browser thread:
   - browser driver starts (with retry for startup stability)
   - app opens from `src/test/resources/static Website/index.html`
5. Tests execute with DataProviders (Excel/JSON).
6. On failure, listener captures screenshot and attaches it to Extent report.
7. Driver closes in `@AfterClass`.

## 3. Run Commands

Run full parallel cross-browser suite:

```bash
mvn -q test
```

Current execution model:

- Browser matrix: Chrome + Firefox + Edge
- Parallelization: `parallel="tests"` with `thread-count="3"`
- Scope: full suite (`TC_001` to `TC_007`) on each browser

Output:

- TestNG reports: `test-output`
- Extent reports: `test-output/extent-reports`
- Logs: `logs/automation.log`

### Browser Prerequisites

- Chrome, Firefox, and Edge should be installed on the machine.
- Selenium Manager resolves drivers automatically.
- If a browser startup intermittently fails, framework retries driver startup once.

## 4. Data-Driven Strategy

### Excel-driven tests

- Used where tabular validation data is useful (for example Register, Dashboard).
- `ExcelUtil.getData(sheetName)` reads sheet rows.

### JSON-driven tests

- Used for more scenario-shaped data (Login, Add User, Operators, Useful Links, Downloads).
- `JsonUtil.getData(nodeName, keys...)` maps by explicit key order.
- Always pass explicit key arrays in DataProviders to avoid order-related bugs.

Example:

```java
return JsonUtil.getData("loginData", "TC_ID", "email", "password", "exp");
```

## 5. Page Object Pattern Used Here

Each Page Object should:

- keep locators private/protected
- expose business-level actions (`clickUsers`, `enterEmail`, `submit`)
- avoid test assertions inside POM

Tests should:

- call Page Object methods
- keep assertions in test classes
- use `SoftAssert` only when multiple assertions are meaningful together

## 6. Reporting and Logging

### Extent Reports

- Registered in `testng.xml`
- Creates timestamped HTML report per run
- Captures screenshot for failed tests
- Test name includes browser context (for example `[chrome] TC_002LoginTest.verifyLogin[...]`)

### Logging

- Log4j2 is used for execution logs.
- Prefer `logger.info/debug/error` over `System.out.println`.

---

###  Jenkins  Execution
🔧 1. Configure Git

    Git Path: C:\Program Files\Git\bin\git.exe

🔧 2. Configure Maven

  Name: Maven

Install automatically ✅

Same page → Maven

---

3. Job Configuration
 
4. Source Code Management
 
     Git Repo URL:
     https://github.com/PoojaPatil769/Automation-Training-Portal.git

---
 Open CMD and run:
- java -jar jenkins.war

 👉 Then open browser:      
  
    http://localhost:8080

- AutomationTrainingPortal
- Build Now ▶️












👩‍💻 Author

Pooja Patil


