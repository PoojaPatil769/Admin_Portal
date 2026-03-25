# Project Explanation

## What this project is

This is a Selenium automation framework built to learn how real-world UI automation projects are structured.

It automates a training portal static website and validates major user journeys:

- Registration
- Login
- Dashboard
- Users / Add User
- Operators
- Useful Links
- Downloads

## Why this project is useful for learning

You learn not only Selenium commands but also framework engineering:

- Layered design (`Base`, `POM`, `TestClass`, `Utility`, `Listeners`)
- Data-driven testing from both Excel and JSON
- Reusable login/setup logic
- Reporting with screenshots
- Cross-browser parallel execution

## How the framework works end-to-end

1. TestNG starts suite from `testng.xml`.
2. Three browser test groups run in parallel:
   - Chrome
   - Firefox
   - Edge
3. For each class, `BaseClass`:
   - starts driver
   - opens app from resources
4. Test methods use POM classes to interact with UI.
5. Assertions validate expected behavior.
6. Extent listener captures status and screenshots on failures.
7. Driver closes after class execution.

## What makes it production-style

- Browser parameterization and matrix execution
- Parallelization strategy designed for stability
- POM-based maintainability
- Deterministic JSON mapping by explicit keys
- Centralized logging and report generation
- Defensive handling for browser-specific alert timing behavior

## Important files to understand first

- `README.md` - quick start and learning path
- `HLD.md` - big-picture architecture
- `LLD.md` - class-level design
- `testng.xml` - execution control
- `src/test/java/Base/BaseClass.java` - driver lifecycle
- `src/test/java/Listeners/ExtentTestListener.java` - reporting lifecycle

## Skills you can build by extending this project

- Add new page objects and new feature tests
- Add smoke/regression tagging
- Add config-based environment management
- Integrate with CI tools
- Improve flaky-test resilience (retry analyzer, waits, synchronization)

## Suggested learner progression

1. Run existing suite and inspect reports.
2. Pick one test class and trace call flow to POM.
3. Add one new validation in an existing page.
4. Add one new complete test class with JSON DataProvider.
5. Run all browsers in parallel and analyze report differences.
