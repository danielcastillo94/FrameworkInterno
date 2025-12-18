Feature: GitHub Profile Followers List Scroll Validation

  @functional @followers @scroll
  Scenario: Validate followers list allows scrolling when exceeding container size
    Given I access the GitHub profile search component
    When I enter an existing username with a considerable number of followers in the search field
    And I press the search button with magnifying glass icon
    Then the system should display the user profile information
    And the followers list should be displayed vertically in the right section
    And each follower should display their avatar, username and direct link to their profile
    When the number of followers exceeds the container size
    Then a vertical scroll should be enabled in the followers list
    When I scroll down in the followers list
    Then the system should allow smooth scrolling through the entire followers list
    When I click on one of the follower links
    Then the system should redirect to the corresponding GitHub page of the selected follower