# QA Automation Test - Periplus Shopping Cart

This repository contains the automated test script and manual test documentation for the Periplus Shopping Cart functionality, developed as part of the technical assessment for OpenWay Group.

## Tech Stack
* **Language:** Java 17
* **Automation Framework:** Selenium WebDriver (v4.18.1)
* **Testing Framework:** TestNG
* **Build Tool:** Maven
* **Browser:** Google Chrome

## Repository Structure
```text
.
├── src/test/java/
│   └── PeriplusCartTest.java    # The automated Selenium test script
├── Assignment 5.1.pdf           # Theory: Components of a Test Case (Task 1)
├── Assignment 5.2.1.pdf         # Manual Test Suite: Shopping Cart  (Task 2.1)
├── pom.xml                      # Maven configuration & dependencies
└── README.md                    # Project documentation & execution guide

```

## Configuration & Credentials

Before executing the tests, you must configure the login credentials directly in the source code to ensure the authentication step passes:

1. Open `src/test/java/PeriplusCartTest.java`.

2. Locate the PLACEHOLDERDS section at the top of the class:
```java
private static final String EMAIL = "your_email_here@gmail.com";
private static final String PASSWORD = "your_password_here";
```

3. Replace the values with your registered Periplus account credentials.

4. Save the file before running the Maven command.


## Prerequisites
Ensure you have the following installed before running the tests:

1. Java Development Kit (JDK)

2. Apache Maven (Installed and added to System PATH)

3. Google Chrome Browser (Latest version)

## How to Run the Tests

1. Extrsct this repository to your local machine

2. Open your Terminal or Command Prompt

3. Navigate to the project root directory (where `pom.xml` is located)

4. Run the following Maven command:
```bash
mvn clean test
```

5. The automation process begins by launching a Chrome instance and navigating to the Periplus homepage. It then locates and clicks the "Sign In" button to access the login page and authenticates using registered test credentials. Upon successful login, the script executes a search for "Mortal Engines," identifies the first available result, and utilizes a JavaScript-based click to add the product to the cart, effectively bypassing any potential UI interceptions. Once the cart counter is confirmed to have updated, the system performs a direct navigation to the checkout page to verify that the product is present and correctly displayed within the cart summary.

6. Execution results will be displayed directly in the terminal
