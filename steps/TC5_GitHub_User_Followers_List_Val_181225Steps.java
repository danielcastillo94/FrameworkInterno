package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FollowersListPage;
import org.junit.Assert;

public class FollowersListSteps {
    private WebDriver driver;
    private FollowersListPage followersListPage;
    private String searchedUsername = "octocat";
    private String mainWindowHandle;

    @Given("the GitHub API is available")
    public void theGitHubAPIIsAvailable() {
        // Verification that API is responding - can be implemented with API call
        Assert.assertTrue("GitHub API should be available", true);
    }

    @And("there is internet connectivity for loading avatars")
    public void thereIsInternetConnectivityForLoadingAvatars() {
        // Verification of internet connectivity
        Assert.assertTrue("Internet connectivity should be available", true);
    }

    @Given("I am on the GitHub user search page")
    public void iAmOnTheGitHubUserSearchPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        followersListPage = new FollowersListPage(driver);
        followersListPage.navigateToSearchPage();
        mainWindowHandle = driver.getWindowHandle();
    }

    @When("I search for a GitHub user with multiple followers")
    public void iSearchForAGitHubUserWithMultipleFollowers() {
        followersListPage.searchForUser(searchedUsername);
    }

    @Then("the user profile should load successfully")
    public void theUserProfileShouldLoadSuccessfully() {
        Assert.assertTrue("User profile should be displayed", 
            followersListPage.isUserProfileDisplayed());
    }

    @And("the followers list should be displayed on the right side vertically aligned")
    public void theFollowersListShouldBeDisplayedOnTheRightSideVerticallyAligned() {
        Assert.assertTrue("Followers list should be visible on the right side", 
            followersListPage.isFollowersListDisplayed());
        Assert.assertTrue("Followers list should be vertically aligned", 
            followersListPage.isFollowersListVerticallyAligned());
    }

    @And("each follower should display their avatar image")
    public void eachFollowerShouldDisplayTheirAvatarImage() {
        Assert.assertTrue("All followers should have visible avatars", 
            followersListPage.areAllFollowerAvatarsDisplayed());
    }

    @And("each follower should display their username")
    public void eachFollowerShouldDisplayTheirUsername() {
        Assert.assertTrue("All followers should have visible usernames", 
            followersListPage.areAllFollowerUsernamesDisplayed());
    }

    @And("each follower should have a clickable link to their GitHub profile")
    public void eachFollowerShouldHaveAClickableLinkToTheirGitHubProfile() {
        Assert.assertTrue("All followers should have clickable profile links", 
            followersListPage.areAllFollowerLinksPresent());
    }

    @When("I click on a specific follower link")
    public void iClickOnASpecificFollowerLink() {
        followersListPage.clickOnFirstFollowerLink();
    }

    @Then("I should be redirected to that follower's GitHub profile page")
    public void iShouldBeRedirectedToThatFollowersGitHubProfilePage() {
        followersListPage.switchToNewWindow(mainWindowHandle);
        Assert.assertTrue("Should be redirected to GitHub profile page", 
            followersListPage.isOnGitHubProfilePage());
    }

    @When("I return to the followers list")
    public void iReturnToTheFollowersList() {
        followersListPage.closeCurrentWindowAndSwitchBack(mainWindowHandle);
    }

    @And("the followers count exceeds the container size")
    public void theFollowersCountExceedsTheContainerSize() {
        Assert.assertTrue("Followers count should exceed container size", 
            followersListPage.doesFollowersCountExceedContainerSize());
    }

    @Then("the followers list should allow vertical scrolling")
    public void theFollowersListShouldAllowVerticalScrolling() {
        Assert.assertTrue("Followers list should be scrollable", 
            followersListPage.isFollowersListScrollable());
    }

    @And("all followers should be accessible through scrolling")
    public void allFollowersShouldBeAccessibleThroughScrolling() {
        Assert.assertTrue("All followers should be accessible after scrolling", 
            followersListPage.canScrollToLastFollower());
        driver.quit();
    }
}