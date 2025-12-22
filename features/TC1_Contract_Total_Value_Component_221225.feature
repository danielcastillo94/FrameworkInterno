Feature: Contract Total Value Component Visualization
  As a wealth banking advisor or banker
  I want to view the total contract value component
  So that I can verify the correct display of contract valuations for Individual Bank contracts

  Background:
    Given the user is authenticated in Acticenter with advisor or wealth banker profile
    And an Individual Bank contract exists in the system with updated valuation information

  Scenario: Verify total contract value component displays correctly for Individual Bank contracts
    Given the user has accessed the Acticenter module
    When the user searches and selects an Individual Bank contract using the search magnifier
    Then the system displays the selected contract in the client general screen
    When the user navigates to the operation flow containing the contract value and composition component
    Then the system displays the component with the total contract value at the review date
    And the component shows the total monetary value for Individual Bank contract in MXN currency format
    When the user clicks on the component
    Then the component is interactive and displays additional information