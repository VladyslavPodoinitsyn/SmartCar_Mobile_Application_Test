 @regression
 @cellular_usage
Feature: SmartCar Cellular Usage Feature
  Agile story:
  The user should be aware of how much cellular data has been used.
  The user should not be able to start live streaming if they have used up 100% of their cellular data.

  Scenario Outline: User should be aware of how much cellular data has been used.
    Given user is on the Home screen
    When user refreshes the Home screen
    Then data usage alert "<value>" should appear with the text "<text>"
    Examples:
    | value | text                                                    |
    |   50  | Test car has used 50% of its data plan for this period. |
    |   75  | Test car has used 75% of its data plan for this period. |
    |   90  | Test car has used 90% of its data plan for this period. |
    |   100 | Test car has used 100% of its data plan for this period.|


  Scenario: User should not be able to start live streaming if they have used up 100% of their cellular data.
    Given user is on the Home screen
    When user refreshes the Home screen
    And user selects LTE sours in the Dash Cam widget
    And user clicks on the label in the Dash Cam widget
    Then Live View-Not enough data screen should appear with text contains "Test car has used 100% of its data plan for this period."