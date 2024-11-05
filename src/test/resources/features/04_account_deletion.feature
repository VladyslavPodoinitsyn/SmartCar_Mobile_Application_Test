 @regression
 @account_deletion
Feature: SmartCar Account Deletion Feature
  Agile story:
  The user should be able to delete their account.


  Scenario: User should be able to delete their account.
    Given user is on the Login screen
    When user login to the application
    And user clicks the Setup tab
    And user clicks the Account section
    And user clicks the Account Deletion section
    And user clicks the Delete Account button
    And user clicks the Confirm button
    And user clicks the Okay button
    And user enters username and password
    Then login should fail with the "Login failed" error