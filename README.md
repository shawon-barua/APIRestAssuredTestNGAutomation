# API Automation BY Restassured and TestNG

This repo demonstrates how to do API Test Automation using Java, TestNg and make a Maven project.
- For the API Test Automation I used Restassured
- Tried to Modularize
- Resusable , maintainable code
- Random data by java Faker used
- Data driven by read input from JSON

**Table of Contents**
<!--ts-->
I have tested below API:
https://documenter.getpostman.com/view/4012288/TzK2bEa8#intro
<!--ts-->
Not All API Automated.
Below are the API have been automated.

### Add User

### Login User

### Add Contact

### Delete Contact

**Prerequisites**

- Java Development Kit (JDK)
- Maven

**Dependencies Used**

- TESTNG
- Jackson-databind
- Java-Faker

### How TO Run the API Tests

- Clone the repo
- Import in IDE
- Run testng_suite.xml
  From Terminal go to project directory and run (if maven installed and maven path given) :

```
mvn clean install
mvn clean verify -Dtest=*Test
```

## Resources

### API Tutorials

- https://medium.com/@RoboticAutomata/restassured-testng-api-test-automation-tutorial-68be216e4d63
- https://github.com/rest-assured/rest-assured/wiki/Usage



