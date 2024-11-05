  @regression
  @registration
Feature: SmartCar Registration Feature
  Agile story:
  The user with SmartCarMobile system installed on their vehicle should be able to register their system.


  Scenario: User should be able to register their system.
    Given user is on the Login screen
    When user clicks the Register button
    And user clicks the Register You System button
    And user enters the serial number
    And user clicks the Continue button
    And user enters his data
    And user clicks the Submit button
    Then Pop-up window "Registration Complete" should be displayed


