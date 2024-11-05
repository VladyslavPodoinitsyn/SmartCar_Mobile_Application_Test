@regression
@logout
Feature: SmartCar Log Out Feature
  Agile story:
  The users should be able to log out of the application.


  Scenario: Users should be able to log out of the application
    Given user is on the Home screen
    When user clicks on the Setup tab
    And user clicks on the Account section
    And user clicks on the Log Out button
    Then the text "Your Car, Connected." should be displayed
