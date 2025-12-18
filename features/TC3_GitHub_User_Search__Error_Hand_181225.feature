Feature: GitHub User Search - Error Handling

  Scenario: Search for a non-existent GitHub user
    Given the GitHub profile search application is open
    When I enter a non-existent username "usuarioquenoexiste123456789" in the search field
    And I click on the search button with magnifying glass icon
    Then the system should display an empty state or friendly error message
    And no technical errors or exceptions should be visible