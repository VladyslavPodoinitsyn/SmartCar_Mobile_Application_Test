@regression
@login
Feature: SmartCar Log In Feature
  Agile story:
  Only authorized users should be able to login to the application.


  Scenario Outline: Login with invalid user name
    Given user is on the Login screen
    When user enters username "<username>"
    And user enters the password "<password>"
    And user clicks the Log In button
    Then login should fail with an error "<err>"
    Examples:
      | username                      | password | err          |
      | user@gmail.com                | password | Login failed |
      | smartcarmobile.test@gmail.com | password | Login failed |


  Scenario Outline: Login with invalid password
    Given user is on the Login screen
    When user enters username "<username>"
    And user enters the password "<password>"
    And user clicks the Log In button
    Then login should fail with an error "<err>"
    Examples:
      | username                      | password        | err          |
      | smartcarmobile.test@gmail.com | invalidPassword | Login failed |
      | smartcarmobile.test@gmail.com | password        | Login failed |


  Scenario: Login with blank username
    Given user is on the Login screen
    When user enters username ""
    And user enters the password "password"
    And user clicks the Log In button
    Then login should fail with a "Required field" error for the "email" field.


  Scenario: Login with blank password
    When user enters username "smartcarmobile.test@gmail.com"
    And user enters the password ""
    And user clicks the Log In button
    Then login should fail with a "Required field" error for the "password" field.


  Scenario: Login with valid user name and password
    Given user is on the Login screen
    When user enters username "smartcarmobile.test@gmail.com"
    And user enters the password "password"
    And user clicks the Log In button
    And user is on the Home screen
    Then account holder name should be "Test"
