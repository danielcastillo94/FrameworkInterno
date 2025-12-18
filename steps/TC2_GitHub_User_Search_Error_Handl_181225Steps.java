package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GitHubSearchPage;
import org.junit.Assert;

public class GitHubSearchErrorSteps {
    private WebDriver driver;
    private GitHubSearchPage gitHubSearchPage;

    @Given("the system has internet connection and access to GitHub API")
    public void theSystemHasInternetConnectionAndAccessToGitHubAPI() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("the user accesses the GitHub profile search component")
    public void theUserAccessesTheGitHubProfileSearchComponent() {
        driver.get("http://localhost:3000");
        gitHubSearchPage = new GitHubSearchPage(driver);
        Assert.assertTrue("Search input should be displayed", gitHubSearchPage.isSearchInputDisplayed());
        Assert.assertTrue("Search button should be displayed", gitHubSearchPage.isSearchButtonDisplayed());
    }

    @When("the user enters a non-existent username {string} in the search field")
    public void theUserEntersANonExistentUsernameInTheSearchField(String username) {
        gitHubSearchPage.enterUsername(username);
        String enteredValue = gitHubSearchPage.getSearchInputValue();
        Assert.assertEquals("Username should be entered correctly", username, enteredValue);
    }

    @And("the user clicks the search button with magnifying glass icon")
    public void theUserClicksTheSearchButtonWithMagnifyingGlassIcon() {
        gitHubSearchPage.clickSearchButton();
    }

    @And("the system queries the GitHub API for the user profile")
    public void theSystemQueriesTheGitHubAPIForTheUserProfile() {
        gitHubSearchPage.waitForAPIResponse();
    }

    @Then("the system receives a response indicating the user does not exist")
    public void theSystemReceivesAResponseIndicatingTheUserDoesNotExist() {
        Assert.assertTrue("API response should indicate user not found", gitHubSearchPage.isUserNotFoundResponseReceived());
    }

    @And("the system displays a friendly error message or empty state")
    public void theSystemDisplaysAFriendlyErrorMessageOrEmptyState() {
        Assert.assertTrue("Error message or empty state should be displayed", gitHubSearchPage.isErrorMessageDisplayed());
    }

    @And("no profile metrics are displayed for Repos, Followers, Following or Gists")
    public void noProfileMetricsAreDisplayedForReposFollowersFollowingOrGists() {
        Assert.assertFalse("Repos metric should not be displayed", gitHubSearchPage.isReposMetricDisplayed());
        Assert.assertFalse("Followers metric should not be displayed", gitHubSearchPage.isFollowersMetricDisplayed());
        Assert.assertFalse("Following metric should not be displayed", gitHubSearchPage.isFollowingMetricDisplayed());
        Assert.assertFalse("Gists metric should not be displayed", gitHubSearchPage.isGistsMetricDisplayed());
    }

    @And("the left section user information remains empty or shows not available message")
    public void theLeftSectionUserInformationRemainsEmptyOrShowsNotAvailableMessage() {
        Assert.assertTrue("User information section should be empty or show not available message", gitHubSearchPage.isUserInfoSectionEmpty());
        driver.quit();
    }
}