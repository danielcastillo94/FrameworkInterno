package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GitHubDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators for dashboard metrics
    private By profileContainer = By.id("profile-container");
    private By reposCounter = By.xpath("//span[@data-testid='repos-count']");
    private By followersCounter = By.xpath("//span[@data-testid='followers-count']");
    private By followingCounter = By.xpath("//span[@data-testid='following-count']");
    private By gistsCounter = By.xpath("//span[@data-testid='gists-count']");
    
    // Locators for actual GitHub profile
    private By gitHubReposCounter = By.xpath("//a[@data-tab-item='repositories']//span[@class='Counter']");
    private By gitHubFollowersCounter = By.xpath("//a[contains(@href,'followers')]//span[@class='text-bold color-fg-default']");
    private By gitHubFollowingCounter = By.xpath("//a[contains(@href,'following')]//span[@class='text-bold color-fg-default']");
    private By gitHubGistsLink = By.xpath("//a[contains(@href,'gist.github.com')]");
    private By gitHubGistsCounter = By.xpath("//span[@class='Counter']");
    
    public GitHubDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isProfileLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(profileContainer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isReposCounterDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(reposCounter));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFollowersCounterDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(followersCounter));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFollowingCounterDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(followingCounter));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isGistsCounterDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(gistsCounter));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getReposCount() {
        String countText = driver.findElement(reposCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getFollowersCount() {
        String countText = driver.findElement(followersCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getFollowingCount() {
        String countText = driver.findElement(followingCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getGistsCount() {
        String countText = driver.findElement(gistsCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public void navigateToGitHubProfile(String username) {
        driver.get("https://github.com/" + username);
        wait.until(ExpectedConditions.presenceOfElementLocated(gitHubReposCounter));
    }
    
    public int getActualGitHubReposCount() {
        String countText = driver.findElement(gitHubReposCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getActualGitHubFollowersCount() {
        String countText = driver.findElement(gitHubFollowersCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getActualGitHubFollowingCount() {
        String countText = driver.findElement(gitHubFollowingCounter).getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    
    public int getActualGitHubGistsCount() {
        try {
            driver.findElement(gitHubGistsLink).click();
            wait.until(ExpectedConditions.urlContains("gist.github.com"));
            String countText = driver.findElement(gitHubGistsCounter).getText().trim();
            return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}