# High-Level Design (HLD)

## 1. Objective

Build a production-style learning automation framework for a static training portal using:

- Java
- Selenium WebDriver
- TestNG
- Page Object Model (POM)
- Data-driven testing (Excel + JSON)
- Extent reporting with screenshots
- Cross-browser parallel execution (Chrome, Firefox, Edge)

## 2. System Context

The framework automates UI validation flows of a local static web application under:

- `src/test/resources/static Website`

It is intended for:

- learning architecture of scalable test frameworks
- writing maintainable test cases
- understanding reporting and parallel execution

## 3. Architecture Overview

Main layers:

1. `Base Layer`
- `BaseClass`: browser setup, app bootstrap, teardown
- `BasePage`: common parent for page objects

2. `POM Layer`
- Encapsulates locators and user actions for each page

3. `Test Layer`
- Test classes `TC_001` to `TC_007`
- Assertions + scenario orchestration

4. `Utility Layer`
- `ExcelUtil`, `JsonUtil`, `LoginUtil`, `ExtentManager`

5. `Listener/Reporting Layer`
- `ExtentTestListener`: logs test start/pass/fail/skip, attaches screenshots

6. `Execution Layer`
- `testng.xml`: browser matrix and parallel strategy

## 4. Execution Design

- Parallel model: TestNG `parallel="tests"` with `thread-count="3"`
- Browser matrix:
  - Chrome
  - Firefox
  - Edge
- Each browser runs the full suite (`TC_001` to `TC_007`) independently.

## 5. Reporting & Observability

- Log4j2 writes execution logs to `logs/automation.log`
- Extent HTML reports are generated under `test-output/extent-reports`
- Failure screenshots are captured and attached automatically

## 6. Design Principles

- Single responsibility per layer
- Data-driven assertions
- Stable locators in POM
- Deterministic JSON mapping via explicit keys
- Minimal cross-test coupling

## 7. Non-Functional Targets

- Readability for learners
- Easy extensibility for new pages/tests
- Browser portability
- Repeatable local execution
