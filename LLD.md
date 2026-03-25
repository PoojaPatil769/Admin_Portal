# Low-Level Design (LLD)

## 1. Package Structure

- `Base`
  - `BaseClass`
  - `BasePage`
- `POM`
  - `HomePage`, `LoginPage`, `RegisterPage`, `DashboardPage`
  - `UsersPage`, `AddUserPage`, `OperatorsPage`, `UsefulLinksPage`, `DownloadsPage`
- `TestClass`
  - `TC_001RegisterTest` ... `TC_007_DownloadsTest`
- `Utility`
  - `ExcelUtil`, `JsonUtil`, `LoginUtil`, `ExtentManager`
- `Listeners`
  - `ExtentTestListener`

## 2. Class Design

### BaseClass

Responsibilities:

- Initialize browser based on TestNG parameter `browser`
- Retry driver startup for resilience
- Maximize window and load app URL from classpath
- Quit driver after class execution

Key methods:

- `setup(String browser)`
- `getDriver()`
- `tearDown()`

### BasePage

Responsibilities:

- Store page-level WebDriver
- Initialize `@FindBy` elements via `PageFactory`

### Page Objects (POM)

Each page object:

- Keeps locators and interactions for one page
- Exposes business actions only
- Does not perform TestNG assertions

Examples:

- `LoginPage`: enter email/password, click sign-in, read error messages
- `UsersPage`: add user navigation, delete flows, alert handling
- `DownloadsPage`: validate official links and downloadable assets

### JsonUtil

Responsibilities:

- Read test data from `testdata/testdata.json`
- Map fields deterministically with explicit key order

Methods:

- `getData(nodeName)`
- `getData(nodeName, keys...)`

### ExcelUtil

Responsibilities:

- Read excel sheet rows from `testdata/excel.xlsx`
- Ignore empty rows

### LoginUtil

Responsibilities:

- Shared login helper for tests requiring authenticated state

### ExtentManager

Responsibilities:

- Create and return singleton `ExtentReports` instance
- Generate timestamped report file

### ExtentTestListener

Responsibilities:

- Create report node for each test case
- Mark pass/fail/skip
- Capture screenshot on failure
- Add browser context to test name

## 3. Test Class Design

- `TC_001`: Register scenarios (Excel)
- `TC_002`: Login scenarios (JSON)
- `TC_003`: Dashboard validations (Excel)
- `TC_004`: Users/Add User/delete/cancel (JSON + UI validation)
- `TC_005`: Operators page validations (JSON)
- `TC_006`: Useful links validations (JSON)
- `TC_007`: Downloads validations (JSON)

## 4. Data Flow

1. DataProvider reads input from Excel/JSON.
2. Test method receives strongly ordered parameters.
3. Test uses POM methods to act on UI.
4. Assertions validate outcomes.
5. Listener records result + screenshots.

## 5. Parallel Execution Details

- TestNG suite has 3 `<test>` nodes with browser parameters:
  - `chrome`
  - `firefox`
  - `edge`
- Execution is parallel at test level (`parallel="tests"`).
- Each browser thread runs the same test class list.

## 6. Error Handling Notes

- Driver startup has retry logic.
- Alert-sensitive flows in users/delete are handled defensively for browser differences.
- Screenshot capture failures do not break listener execution.
