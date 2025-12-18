Feature: GitHub Profile Search Responsive Layout Validation
  As a user
  I want to search for GitHub profiles on different devices and orientations
  So that I can verify the interface adapts correctly to Desktop, Mobile Portrait and Mobile Landscape resolutions

  Background:
    Given the system has internet connection and access to GitHub API
    And emulation tools are available for testing different resolutions

  Scenario: Validate responsive interface adaptation across Desktop and Mobile orientations
    Given I access the GitHub profile search component from a Desktop device
    Then the system displays the complete interface with optimized desktop layout
    When I enter an existing username and press the search button
    Then the system queries and displays the profile information with all sections visible and correctly distributed in desktop layout
    And I verify the desktop element distribution with left section for user information, metrics dashboard and right section for followers list
    Then all elements are correctly distributed and aligned according to desktop responsive design
    When I access the GitHub profile search component from a mobile device in Portrait orientation
    Then the system adapts the interface to mobile screen size in vertical orientation
    When I enter an existing username and press the search button in mobile Portrait
    Then the system queries and displays the profile information adapted to mobile Portrait view
    And I verify that all elements including search input, metrics, user information and followers list display correctly in mobile Portrait
    Then the elements reorganize vertically or adjust to maintain readability and usability on vertical mobile screen
    When I rotate the mobile device to Landscape orientation
    Then the system detects the orientation change and adapts the interface automatically
    And I verify that the interface in mobile Landscape shows all elements correctly adapted
    Then the elements redistribute taking advantage of available horizontal space maintaining readability and usability in horizontal orientation
    When I validate interactivity in all versions by clicking follower links, search button and Follow button
    Then all interactive functionalities work correctly in all three versions: Desktop, Mobile Portrait and Mobile Landscape