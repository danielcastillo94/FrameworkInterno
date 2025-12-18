Feature: GitHub User Profile Search
  As a user of the GitHub profile search application
  I want to search for existing GitHub users
  So that I can view their public profile information

  Background:
    Given the GitHub profile search application is accessible
    And there is internet connection to consume GitHub API

  Scenario: Successfully search for an existing GitHub user profile
    Given the application loads correctly showing the search input and search button with magnifying glass icon
    When I enter a valid existing GitHub username "octocat" in the search field
    Then the entered text is displayed correctly in the input
    When I click on the search button with magnifying glass icon
    Then the system makes a request to the GitHub API
    And the searched user profile loads and displays correctly with all public information