Feature: GitHub Profile Details Validation
  As a user of the GitHub profile search application
  I want to view complete profile details in the left section
  So that I can see all personal information of a GitHub user

  Scenario: Validate left section displays all personal profile details for a user with complete information
    Given the system has internet connection and access to GitHub API
    And the GitHub profile search component is displayed
    When I enter an existing username with complete profile information in the search field
    And I click the search button with magnifying glass icon
    Then the system should query the user profile through GitHub API
    And the Avatar should be displayed correctly in the left section
    And the Full Name and @Username should be visible
    And the Biography should be displayed
    And the Location and Company information should be shown
    And the Web Link should be displayed as clickable
    And the Follow button should be visible and functional
    And any unavailable fields should display empty or "No disponible" text