# XLeetCode — Selenium Web Automation

A Java automation project developed as part of the Crio XLeetCode assessment. It uses Selenium WebDriver to check navigation, problem pages, and the signed-out submissions experience on [LeetCode](https://leetcode.com/).

## Test coverage

| Test | Scenario |
| --- | --- |
| `testCase01` | Open the homepage and check that the URL contains `leetcode`. |
| `testCase02` | Navigate through **View Questions**, check the problem-set URL, and open the first five problems to compare their names with the page titles. |
| `testCase03` | Open **1. Two Sum** from the problem set and check that the URL contains `two-sum`. |
| `testCase04` | Open the Two Sum submissions tab and check for a registration/login prompt while signed out. |

## Technology

- Java
- Gradle 7.5.1, included through the Gradle Wrapper
- Selenium WebDriver 4.21.0
- WebDriverManager 5.8.0
- Google Chrome

JUnit Jupiter API 5.10.0 is declared as a dependency. The current scenarios are ordinary Java methods invoked by `demo.App`; run them with the Gradle `run` task.

## Prerequisites

- JDK 17 with `JAVA_HOME` configured and Java available on your system path.
- Google Chrome installed.
- Internet access to download Gradle, dependencies, and ChromeDriver, and to access LeetCode.
- A desktop environment: the current configuration launches a visible Chrome window.

A separate Gradle installation and manual ChromeDriver download are not required. WebDriverManager handles driver setup.

## Getting started

Clone or download this repository, then open a terminal in the folder containing `build.gradle`, `gradlew`, and `gradlew.bat`.

If your checkout contains another `saptarshiroy16-ME_QA_XLEETCODE1` folder, enter that folder first.

### Windows PowerShell

```powershell
java -version
.\gradlew.bat build
.\gradlew.bat run
```

### macOS / Linux

```bash
java -version
chmod +x gradlew
./gradlew build
./gradlew run
```

The first execution may take longer while dependencies and the browser driver are downloaded.

## Execution and results

`demo.App` creates a browser session, invokes all four scenarios in sequence, and calls `endTest()` to quit the browser after the sequence finishes normally.

Check the terminal for scenario messages such as:

```text
SUCCESS: Test Case 01 Passed!
SUCCESS: On problemset page
SUCCESS: Test Case 03 Passed!
SUCCESS: 'Register or Log in' message verified.
```

These are illustrative messages, not a record of a verified run. Failures are generally printed to the console rather than enforced through assertions. A successful Gradle exit alone does not establish that every scenario passed. The `build` and `test` tasks do not execute these browser scenarios.

ChromeDriver logging is configured to write to `chromedriver.log` in the working directory.

## Project structure

```text
.
├── src/main/java/demo/
│   ├── App.java                  # Entry point and scenario execution order
│   └── TestCases.java            # Browser setup, scenarios, and cleanup
├── gradle/wrapper/               # Gradle Wrapper configuration and JAR
├── build.gradle                  # Dependencies and application configuration
├── gradlew                      # macOS/Linux Gradle launcher
├── gradlew.bat                  # Windows Gradle launcher
├── assesment/                   # Crio assessment utilities
├── __CRIO__/                    # Crio project metadata
├── run_assesment.sh             # Assessment helper
├── run_platform_assesment.sh    # Platform assessment helper
└── run_platform_assesment.bat   # Windows platform assessment helper
```

## Troubleshooting and limitations

- **Java or Gradle startup errors:** confirm that `JAVA_HOME` points to JDK 17 and `java -version` reports the expected installation.
- **ChromeDriver setup errors:** check that Chrome is installed and that your network allows driver downloads.
- **Elements not found or waits timing out:** the scenarios depend on LeetCode's live page structure, text, and loading behavior. Website changes, verification challenges, or slow responses can require locator or wait updates in `TestCases.java`.
- **Browser remains open after an error:** an uncaught exception before `endTest()` can interrupt cleanup; close the browser manually if necessary.

This project automates a third-party website and is intended for learning and assessment. It does not submit solutions or sign in to a LeetCode account.
