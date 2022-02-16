# mobile-automation

# Pre-Requisites

- System should have JRE 1.8 or later, also set environment variable `JAVA_HOME`
- System should have Apache Maven setup
- System should have Android SDK and environment variable for Android should be setup i.e. 'ANDROID_HOME'
- System should have XCODE(for IOS execution)
- Command line Appium should be setup in the system(make sure you install latest version i.e 1.22.2)
- In order write scripts and locate elements, Appium GUI(Desktop version) can also be installed.

You may follow this link for more step by step details for the setup https://www.swtestacademy.com/how-to-install-appium-on-mac/

# Project Details

## Framework

### Mobile Events

- All the actions for mobile are defined in `MobileActions` class.

### Reporting

- Use customize loggers to log into the reports. 
- Log methods available in Logger class: `logPass(description)`, `logFail(description, exception)`
- Fail logger automatically captures the screenshot

<img width="1792" alt="Screen Shot 2022-02-16 at 11 54 13 AM" src="https://user-images.githubusercontent.com/17287880/154220250-9194740f-02c5-44e0-8959-63602e70f7bb.png">

## Page Objects

- Create one screen as a class and add locators to the same class
- Each locator is declared as of type `MobileElement` which is generic to `IOSElement` and `AndroidElement`
- Each locator should be annotated with `@iOSXCUITFindBy` and `@AndroidFindBy`
- `actions` a thread safe object of `MobileActions` is used to access the Mobile Events in the page objects

## Test Cases

### Test Class

- Each test class contains methods annotated with `@Test` i.e. Test cases
- Test class should be extended with class `EnvironmentSetup` to inherit the driver and also to handle the TestNG hooks.
- The naming convention for the test cases is like `register_new_buyer`

### Test Case Management

- For test case management we are using TestNG
- Define the test cases inside the `test` tag of testng
- Each test tag of testng will have it's own driver and require platform to be passed throught attribute `deviceType`
