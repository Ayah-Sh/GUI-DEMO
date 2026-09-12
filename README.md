# AppiumTask — Mobile Automation Tests (iOS)

This repository contains a small Appium-based test automation project (Java + Maven) focused on iOS (IOSDriverFactory). It includes page objects, utilities, and a sample test for Wikipedia.

## Features
- Page Object Model for cleaner tests
- Properties-driven configuration (test/resources/config.properties)
- Example test: test/java/wiki/WikipediaTest.java

## Prerequisites
- Java JDK 8+ (11 recommended)
- Maven
- Appium server installed and running
- Xcode and device Real device (for iOS tests)

## Setup
1. Clone the repository:
   git clone <repo-url>
2. Open or navigate to the project root where `pom.xml` is located.
3. Update configuration: edit `test/resources/config.properties` to set device, platformVersion, app/package/activity or other required keys.
4. Start Appium server and connect IOS device. (your device UDID and ORGnaation ID is required )
5. Enter your

## Running tests
- Run the whole test suite:
  mvn test

- Run a single test class by name (example):
  mvn -Dtest=WikipediaTest test

- Use Maven profiles or system properties to pass additional parameters (if added in pom.xml or test harness).

## Project structure (important files)

A concise tree of the repository's key files:

- pom.xml — Maven build file
- src/
  - main/
    - java/
      - driverManager/
        - IOSDriverFactory.java — driver setup
      - engine/platform/
        - ActionsBot.java — helper utilities
      - pages/
        - HomePage.java
        - SearchPage.java
        - ArticlePage.java
        - ReadingListPage.java
      - utils/
        - PropertiesReader.java — loads test properties
  - test/
    - java/
      - wiki/
        - WikipediaTest.java — example test
    - resources/
      - config.properties — test configuration
- target/ — compiled classes and build artifacts




## Project To-Do

A short checklist of planned improvements and tasks. Mark items done as they are completed.

- [ ] Add Android support (AndroidDriverFactory, device capability matrix)
- [ ] Edit Driver Manager to handle Android 
- [ ] Add Report -  automated screenshots/video capture on test failure
- [ ] GitHub Actions

If you'd like, I can implement any of these now (CI, Android driver, or video embedding).


2) Upload the video as a GitHub release asset, or attach it to a PR/comment and link to it. Then embed or link using a normal Markdown link:

```markdown
[Watch demo video](https://github.com/OWNER/REPO/releases/download/v1.0/your-video.mp4)
```


