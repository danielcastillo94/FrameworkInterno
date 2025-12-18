Feature: User Profile Left Section Validation
  As a user of the GitHub profile search application
  I want to view complete user profile details in the left section
  So that I can see avatar, personal information and follow the user

  Scenario: Validate left section displays all user profile details correctly
    Given the user navigates to the GitHub profile search page
    When the user searches for an existing GitHub user with complete profile information
    Then the user profile should load successfully
    And the avatar image should be displayed in the left section
    And the full name and username with @ should be visible
    And the user biography should be displayed
    And the user location should be visible
    And the user company information should be displayed
    And the user website link should be visible and clickable
    And the Follow button should be displayed and enabled

  Scenario: Validate left section handles missing profile information
    Given the user navigates to the GitHub profile search page
    When the user searches for a GitHub user with incomplete profile information
    Then the user profile should load successfully
    And missing fields should display empty values or 'Not available' text