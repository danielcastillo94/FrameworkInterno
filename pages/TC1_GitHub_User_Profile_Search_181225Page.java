package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GitHubSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By searchInput = By.id("search-input");
    private By searchButton = By.id("search-button");
    private By userProfile = By.id("user-profile");
    private By userName = By.className("user-name");
    private By loadingIndicator = By.className("loading");
    
    // Constructor
    public GitHubSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Navigation method
    public void navigateToApplication() {
        driver.get("http://localhost:3000");
        waitForPageLoad();
    }
    
    // Verification methods
    public boolean isSearchInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchButtonDisplayed() {
        try {
            return driver.findElement(searchButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isUserProfileDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userProfile)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Interaction methods
    public void enterUsername(String username) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchField.clear();
        searchField.sendKeys(username);
    }
    
    public void clickSearchButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }
    
    // Getter methods
    public String getSearchInputValue() {
        return driver.findElement(searchInput).getAttribute("value");
    }
    
    public String getUserProfileName() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).getText();
        } catch (Exception e) {
            return null;
        }
    }
    
    // Wait methods
    public void waitForAPIResponse() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIndicator));
    }
    
    private void waitForPageLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
    }
}