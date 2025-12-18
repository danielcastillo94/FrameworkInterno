package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GitHubDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By userProfile = By.xpath("//div[@class='user-profile']");
    private By reposCounter = By.xpath("//span[contains(@class, 'repos-count') or @data-testid='repos-count']");
    private By followersCounter = By.xpath("//span[contains(@class, 'followers-count') or @data-testid='followers-count']");
    private By followingCounter = By.xpath("//span[contains(@class, 'following-count') or @data-testid='following-count']");
    private By gistsCounter = By.xpath("//span[contains(@class, 'gists-count') or @data-testid='gists-count']");

    public GitHubDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProfileDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userProfile));
            return driver.findElement(userProfile).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReposCountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(reposCounter));
            return driver.findElement(reposCounter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFollowersCountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(followersCounter));
            return driver.findElement(followersCounter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFollowingCountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(followingCounter));
            return driver.findElement(followingCounter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGistsCountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(gistsCounter));
            return driver.findElement(gistsCounter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getReposCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(reposCounter));
        String countText = driver.findElement(reposCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }

    public int getFollowersCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(followersCounter));
        String countText = driver.findElement(followersCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }

    public int getFollowingCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(followingCounter));
        String countText = driver.findElement(followingCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }

    public int getGistsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(gistsCounter));
        String countText = driver.findElement(gistsCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }

    public int getActualGitHubReposCount(String username) {
        Response response = RestAssured.get("https://api.github.com/users/" + username);
        return response.jsonPath().getInt("public_repos");
    }

    public int getActualGitHubFollowersCount(String username) {
        Response response = RestAssured.get("https://api.github.com/users/" + username);
        return response.jsonPath().getInt("followers");
    }

    public int getActualGitHubFollowingCount(String username) {
        Response response = RestAssured.get("https://api.github.com/users/" + username);
        return response.jsonPath().getInt("following");
    }

    public int getActualGitHubGistsCount(String username) {
        Response response = RestAssured.get("https://api.github.com/users/" + username);
        return response.jsonPath().getInt("public_gists");
    }
}