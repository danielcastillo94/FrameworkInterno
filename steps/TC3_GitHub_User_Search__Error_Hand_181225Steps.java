package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GitHubSearchPage;
import static org.junit.Assert.*;

public class GitHubSearchErrorSteps {
    private WebDriver driver;
    private GitHubSearchPage gitHubSearchPage;

    @Given("the GitHub profile search application is open")
    public void theGitHubProfileSearchApplicationIsOpen() {
        driver = new ChromeDriver();
        driver.get("https://your-github-search-app-url.com");
        gitHubSearchPage = new GitHubSearchPage(driver);
        assertTrue("Search input should be displayed", gitHubSearchPage.isSearchInputDisplayed());
        assertTrue("Search button should be displayed", gitHubSearchPage.isSearchButtonDisplayed());
    }

    @When("I enter a non-existent username {string} in the search field")
    public void iEnterANonExistentUsernameInTheSearchField(String username) {
        gitHubSearchPage.enterUsername(username);
        assertEquals("Entered text should be visible in input", username, gitHubSearchPage.getSearchInputValue());
    }

    @And("I click on the search button with magnifying glass icon")
    public void iClickOnTheSearchButtonWithMagnifyingGlassIcon() {
        gitHubSearchPage.clickSearchButton();
    }

    @Then("the system should display an empty state or friendly error message")
    public void theSystemShouldDisplayAnEmptyStateOrFriendlyErrorMessage() {
        gitHubSearchPage.waitForErrorOrEmptyState();
        assertTrue("Error message or empty state should be displayed", gitHubSearchPage.isErrorMessageDisplayed() || gitHubSearchPage.isEmptyStateDisplayed());
    }

    @And("no technical errors or exceptions should be visible")
    public void noTechnicalErrorsOrExceptionsShouldBeVisible() {
        assertFalse("Technical error should not be displayed", gitHubSearchPage.isTechnicalErrorVisible());
        driver.quit();
    }
}