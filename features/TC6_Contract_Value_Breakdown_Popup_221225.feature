Feature: Contract Value Breakdown Pop-up Auto-close Behavior

  As an authenticated user
  I want the contract value breakdown pop-up to close automatically when clicking outside of it
  So that I can have a clean and intuitive user experience

  Background:
    Given the user is authenticated in Acticenter
    And a contract is selected
    And the contract value component is visible and functional

  Scenario: Verify pop-up closes when clicking outside the component
    Given the user accesses a contract in Acticenter
    When the user clicks on the contract value component
    Then the pop-up with the contract composition breakdown is displayed
    And all applicable items are visible in the breakdown
    When the user clicks on any area outside the breakdown component
    Then the breakdown pop-up closes automatically
    And the main contract value component remains visible

  Scenario: Verify pop-up close consistency across multiple interactions
    Given the user has opened the contract value breakdown pop-up
    When the user clicks on different areas outside the component
    Then the pop-up closes in all cases
    When the user reopens the breakdown pop-up
    And clicks outside the component again
    Then the pop-up closes automatically

  Scenario: Verify pop-up remains open when clicking inside its area
    Given the user has opened the contract value breakdown pop-up
    When the user clicks inside the pop-up area
    Then the breakdown pop-up remains open
    And all breakdown information is still visible