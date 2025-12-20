Feature: Contract Value and Composition Component Validation
  As a patrimonial banking advisor
  I want to view the contract value and composition component
  So that I can see all applicable items for individual bank contracts

  Background:
    Given the user is authenticated as a patrimonial banking advisor in Acticenter
    And there is an active connection with SAP and Lumina services
    And a valid individual bank contract exists with available information in Lumina

  Scenario: Display contract value and composition component with all applicable items
    Given the user has accessed Acticenter successfully
    When the user searches for a valid individual bank contract using the search icon
    Then the system displays the client's general view
    And the user can select the desired contract
    When the user views the contract value and composition component in the main view
    Then the system displays the total contract value updated to the review date
    When the user clicks on the contract value and composition component
    Then a popup aligned vertically with the component is displayed
    And the popup shows the complete breakdown of all items
    And the following items are visible: Cash MXN, Cash USD, Pending settlements, Debt funds, Hedge funds, Variable income funds, Cash in transit, CDs and promissory notes, Money market, Capital market
    And all items display their corresponding monetary values on the right side
    And items with no balance show $0.00
    When the user validates monetary values against Lumina services
    Then all displayed values are correct and consistent with data sources
    And values are consistent with the advisor module
    And each item shows its label and value in appropriate monetary format
    And Cash MXN corresponds to the cash in the contract's main account
    When the contract has a related Mexdolar account
    Then Cash USD is displayed with the SAP balance without conversion
    When the contract does not have Mexdolar
    Then Cash USD item is not displayed
    When the user clicks outside the popup area
    Then the popup closes correctly
    And the main component view is restored
    When the user hovers over elements with tooltips according to Figma designs
    Then tooltips are displayed correctly