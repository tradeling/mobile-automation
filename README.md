<h1 align="center">

[![BROWSERSTACK-DASHBOARD](https://img.shields.io/badge/BROWSERSTACK-DASHBOARD-yellow)](https://app-automate.browserstack.com/dashboard/v2/builds/5f62542718e255e637258e57965570b5e0beb888)
[![ENGINEERING-PRACTICES](https://img.shields.io/badge/ENGINEERING-PRACTICES-red)](https://github.com/tradeling/coding-guide/blob/master/10-engineering-principles.md)
[![LOCAL-DEV_GUIDE](https://img.shields.io/badge/LOCAL-DEV_GUIDE-blue)](https://github.com/tradeling/coding-guide/blob/master/11-local-environment.md)
[![API-DESIGN_GUIDE](https://img.shields.io/badge/API-DESIGN_GUIDE-brightgreen)](https://github.com/tradeling/coding-guide/blob/master/12-api-design-guide.md)
[![MANUAL TEST CASES](https://img.shields.io/badge/MANUAL-TEST_CASES-green)](https://tradeling.atlassian.net/projects/QTM?selectedItem=com.atlassian.plugins.atlassian-connect-plugin%3Acom.xpandit.plugins.xray__testing-board#!page=test-repository)
[![GITHUB BEST PRACTICES](https://img.shields.io/badge/GITHUB-BEST_PRACTICES-orange)](https://github.com/tradeling/engineering-guide/blob/develop/onboarding/onboarding-github-guide.md)

</h1>


```markdown
💡🚨 Improve the document - if you find it outdated or something missing.
"Leave the place better than you found it - Always." ~ Everyone
```

```markdown
💡🚨 We choose to go to the moon in this decade and do the other things,
not because they are easy, but because they are hard. -- John F. Kennedy, 1962
```

```markdown
💡🚨 "15 min rule"
We are collaborative and very friendly, but we value your time and other people time, So

- If you have any question about anything, google it first.
- If you couldn't find the answer in 15 minutes then you are free to ask your colleagues.
```

# Mobile Automation Guidelines

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

## Tradeling App and Edukaan Configuration

- Repository is configured to execute Edukaan and Tradeling App scripts
- There are two testng files maintained in the repository one for edukaan(edukaan-testng.xml) and one for tradeling app(buyerApp-testng.xml)
- There are two profile created in the pom.xml one for Edukaan and another for Tradeling app which contains the suite for execution(i.e testng xml) and the application releated details(like app name, app package, bunde id etc.)
- The packages for edukaan and buyer app has been segrgated for page objects or rest capabilities or the test data/resources

<img width="1113" alt="Screen Shot 2022-03-29 at 11 55 03 AM" src="https://user-images.githubusercontent.com/17287880/160563430-dd4e66d4-3852-4d2d-813a-20243b7a2aff.png">

## Device Details

- All the details for the target devices for execution are provided in file 'src/main/resources/setup/deviceDetails.properties'
- All the details for emote execution that is browser stack is provided in file 'src/main/resources/setup/browserStack.properties'

## Test Cases

### Test Class

- Each test class contains methods annotated with `@Test` i.e. Test cases
- Test class should be extended with class `EnvironmentSetup` to inherit the driver and also to handle the TestNG hooks.
- The naming convention for the test cases is like `register_new_buyer`

### Test Case Management

- For test case management we are using TestNG
- Define the test cases inside the `test` tag of testng
- Each test tag of testng will have it's own driver and require platform to be passed throught attribute `deviceType`


## Execution of Test scripts

The required input to run the scripts:
- The profile i.e buyerApp or edukaan. Pass the value with arguement '-PbuyerApp or -Pedukaan'
- The environment for execution local or device farm. Pass the value with argument '-Denvironment=local or -Denvironment=remote'
- The appPath is the path of the ios and anroid build in the execution server. Pathe value with argument '-DappPath=pathtoBuilds'. This argument is not required if the execution is remote. Aslo if the app is preinstalled in the devices this argument can be skipped.

Sample Command
`mvn test -PbuyerApp -Denvironment=local -DappPath={localPath}`
