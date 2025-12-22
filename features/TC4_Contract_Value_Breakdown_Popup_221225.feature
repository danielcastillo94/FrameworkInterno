Feature: Contract Value Breakdown Popup
  As an authenticated user in Acticenter
  I want to view the contract value breakdown in a popup
  So that I can see all applicable contract value items

  Background:
    Given the user is authenticated in Acticenter
    And the user has access to contracts
    And at least one contract is available for consultation

  Scenario: Verify popup displays on click and closes when clicking outside
    Given the user accesses the Acticenter module
    When the user selects any available contract
    Then the system displays the selected contract with the total value component visible
    When the user clicks on the total value component
    Then the system displays the popup with the contract value breakdown
    And all applicable items are shown in the popup
    When the user verifies the popup content
    Then the popup is fully displayed with all items visible
    And all values are displayed correctly
    And the information is organized vertically and aligned with the component
    When the user clicks again inside the total value component
    Then the popup remains displayed or behaves according to the defined design
    When the user clicks on any area outside the component and popup
    Then the popup closes automatically
    And the component returns to its closed state
    When the user clicks on the total value component again
    Then the popup displays again with all breakdown information
    When the user clicks on different areas outside the component
    Then the popup closes correctly in all cases