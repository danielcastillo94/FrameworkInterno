Feature: Contract Total Value Validation in Acticenter
  As a wealth management advisor
  I want to view the total contract value in the main component
  So that I can verify the complete portfolio value for my clients

  Background:
    Given the user is authenticated as a wealth management advisor in Acticenter
    And there is at least one active client with a contract in Casa de Bolsa or Banco
    And Lumina services for funds are operational and available

  Scenario: Validate contract total value display in main component
    Given the advisor has successfully logged into Acticenter
    When the advisor searches and selects an existing client and contract using the search magnifying glass
    Then the system displays the general screen for the selected client or contract
    When the advisor navigates to the funds operation flow where the contract value component is located
    Then the system shows the main component with the total contract value
    And the component displays the total contract value as of the review date with correct monetary format
    And the displayed value matches the sum of all contract line items including purchasing power MXN, cash MXN, cash USD, pending settlements, debt funds, hedge funds, equity funds, cash in transit, CDs and promissory notes, money market and capital market as applicable