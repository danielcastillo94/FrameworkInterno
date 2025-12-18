Feature: GitHub user search error handling
  As a user of the GitHub profile search component
  I want to see a friendly error message when searching for non-existent users
  So that I understand when a user profile cannot be found

  @error-handling @github-search
  Scenario: Search for non-existent GitHub user
    Given I am on the GitHub profile search component
    When I enter a non-existent username "usuarioinexistente123456789" in the search field
    And I click the search button with magnifying glass icon
    And I wait for the GitHub API response
    Then I should see a friendly error message indicating user not found
    And I should not see any profile metrics displayed
    And I should not see any user information in the left section