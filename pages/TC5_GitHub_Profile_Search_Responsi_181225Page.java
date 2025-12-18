package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class GitHubProfileSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By usernameInput = By.id("username-search");
    private By searchButton = By.id("search-btn");
    private By profileContainer = By.className("profile-container");
    private By userInfoSection = By.className("user-info-section");
    private By metricsSection = By.className("metrics-section");
    private By followersList = By.className("followers-list");
    private By followButton = By.id("follow-btn");
    private By followerLinks = By.cssSelector(".followers-list a");
    private By desktopLayout = By.className("desktop-layout");
    private By mobilePortraitLayout = By.className("mobile-portrait-layout");
    private By mobileLandscapeLayout = By.className("mobile-landscape-layout");
    private By leftSection = By.className("left-section");
    private By rightSection = By.className("right-section");
    private By metricsDashboard = By.className("metrics-dashboard");
    private By searchInputContainer = By.className("search-input-container");
    private By userAvatar = By.className("user-avatar");
    private By userName = By.className("user-name");
    private By userBio = By.className("user-bio");
    private By reposCount = By.id("repos-count");
    private By followersCount = By.id("followers-count");
    private By followingCount = By.id("following-count");
    
    public GitHubProfileSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToPage() {
        driver.get("https://your-github-profile-search-app.com");
    }
    
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }
    
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
    }
    
    public boolean isDesktopLayoutDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(desktopLayout));
            return driver.findElement(desktopLayout).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isProfileDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(profileContainer));
            return driver.findElement(profileContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areAllSectionsVisible() {
        try {
            return driver.findElement(userInfoSection).isDisplayed() &&
                   driver.findElement(metricsSection).isDisplayed() &&
                   driver.findElement(followersList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isUserInfoInLeftSection() {
        try {
            WebElement leftSec = driver.findElement(leftSection);
            return leftSec.findElements(By.className("user-info-section")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isMetricsDashboardVisible() {
        try {
            return driver.findElement(metricsDashboard).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFollowersInRightSection() {
        try {
            WebElement rightSec = driver.findElement(rightSection);
            return rightSec.findElements(By.className("followers-list")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areElementsProperlyAligned() {
        try {
            WebElement userInfo = driver.findElement(userInfoSection);
            WebElement metrics = driver.findElement(metricsSection);
            WebElement followers = driver.findElement(followersList);
            return userInfo.getLocation().getX() >= 0 &&
                   metrics.getLocation().getY() >= 0 &&
                   followers.getLocation().getX() >= 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isMobilePortraitLayoutDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mobilePortraitLayout));
            return driver.findElement(mobilePortraitLayout).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchInputVisible() {
        try {
            return driver.findElement(usernameInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areMetricsVisible() {
        try {
            return driver.findElement(metricsSection).isDisplayed() &&
                   driver.findElement(reposCount).isDisplayed() &&
                   driver.findElement(followersCount).isDisplayed() &&
                   driver.findElement(followingCount).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isUserInfoVisible() {
        try {
            return driver.findElement(userInfoSection).isDisplayed() &&
                   driver.findElement(userAvatar).isDisplayed() &&
                   driver.findElement(userName).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFollowersListVisible() {
        try {
            return driver.findElement(followersList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areElementsVerticallyOrganized() {
        try {
            WebElement search = driver.findElement(searchInputContainer);
            WebElement userInfo = driver.findElement(userInfoSection);
            WebElement metrics = driver.findElement(metricsSection);
            WebElement followers = driver.findElement(followersList);
            
            int searchY = search.getLocation().getY();
            int userInfoY = userInfo.getLocation().getY();
            int metricsY = metrics.getLocation().getY();
            int followersY = followers.getLocation().getY();
            
            return searchY < userInfoY && userInfoY < metricsY && metricsY < followersY;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isMobileLandscapeLayoutDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mobileLandscapeLayout));
            return driver.findElement(mobileLandscapeLayout).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areAllElementsVisibleInLandscape() {
        try {
            return driver.findElement(usernameInput).isDisplayed() &&
                   driver.findElement(userInfoSection).isDisplayed() &&
                   driver.findElement(metricsSection).isDisplayed() &&
                   driver.findElement(followersList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areElementsUsingHorizontalSpace() {
        try {
            WebElement userInfo = driver.findElement(userInfoSection);
            WebElement followers = driver.findElement(followersList);
            int userInfoX = userInfo.getLocation().getX();
            int followersX = followers.getLocation().getX();
            return Math.abs(userInfoX - followersX) > 100;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchButtonClickable() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            return button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFollowButtonClickable() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(followButton));
            return button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areFollowerLinksClickable() {
        try {
            List<WebElement> links = driver.findElements(followerLinks);
            if (links.isEmpty()) {
                return false;
            }
            return links.get(0).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areAllInteractiveElementsWorking() {
        return isSearchButtonClickable() && 
               isFollowButtonClickable() && 
               areFollowerLinksClickable();
    }
}