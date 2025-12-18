Feature: GitHub User Profile Search
  As a user of the GitHub profile search system
  I want to search for existing GitHub users
  So that I can view their profile metrics accurately

  Scenario: Validate dashboard metrics for an existing GitHub user
    Given the system has internet connection and access to GitHub API
    And the user interface is displayed with search input and search button
    When I enter a valid existing GitHub username "octocat" in the search field
    And I click the search button with magnifying glass icon
    Then the system should query the GitHub API for the user profile
    And the system should successfully retrieve the user profile data
    And the dashboard should display the Repos metric with total public repositories count
    And the dashboard should display the Followers metric with total followers count
    And the dashboard should display the Following metric with total following count
    And the dashboard should display the Gists metric with total public gists count
    And all metric values should match the actual GitHub profile data
    And the API requests indicator should display the consumed limit in format "X/60"