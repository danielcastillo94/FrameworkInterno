package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class ActicenterContractPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By acticenterModuleLink = By.xpath("//a[contains(text(),'Acticenter')]");
    private By contractSelector = By.id("contract-selector");
    private By casaDeBolsaContractOption = By.xpath("//option[contains(text(),'Persona Física Casa de Bolsa')]");
    private By contractTypeLabel = By.id("contract-type-label");
    private By totalContractValueComponent = By.id("total-contract-value");
    private By breakdownPopup = By.className("contract-breakdown-popup");
    private By purchasingPowerMXNLabel = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Poder de compra MXN')]");
    private By purchasingPowerMXNValue = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Poder de compra MXN')]/following-sibling::span[@class='value']");
    private By currentCashValue = By.id("current-cash-value");
    private By breakdownItems = By.className("breakdown-item");
    private By authenticationStatus = By.id("user-auth-status");
    private By serviceStatusIndicator = By.id("currentcash-service-status");
    
    public ActicenterContractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToActicenter() {
        driver.get("https://acticenter.example.com");
    }
    
    public void verifyUserAuthenticated() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(authenticationStatus));
    }
    
    public boolean isCasaDeBolsaContractAvailable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contractSelector));
        driver.findElement(contractSelector).click();
        List<WebElement> options = driver.findElements(casaDeBolsaContractOption);
        return !options.isEmpty();
    }
    
    public boolean verifyCurrentCashServiceStatus() {
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(serviceStatusIndicator));
        return statusElement.getAttribute("data-status").equals("operational");
    }
    
    public void accessActicenterModule() {
        wait.until(ExpectedConditions.elementToBeClickable(acticenterModuleLink)).click();
    }
    
    public String getCurrentCashValue() {
        WebElement cashElement = wait.until(ExpectedConditions.visibilityOfElementLocated(currentCashValue));
        return cashElement.getText();
    }
    
    public void selectCasaDeBolsaContract() {
        wait.until(ExpectedConditions.elementToBeClickable(contractSelector)).click();
        wait.until(ExpectedConditions.elementToBeClickable(casaDeBolsaContractOption)).click();
    }
    
    public boolean isContractDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(contractTypeLabel)).isDisplayed();
    }
    
    public String getContractType() {
        return driver.findElement(contractTypeLabel).getText();
    }
    
    public void clickTotalContractValue() {
        wait.until(ExpectedConditions.elementToBeClickable(totalContractValueComponent)).click();
    }
    
    public boolean isBreakdownPopupDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(breakdownPopup)).isDisplayed();
    }
    
    public boolean isPurchasingPowerMXNPresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchasingPowerMXNLabel)).isDisplayed();
    }
    
    public String getPurchasingPowerMXNValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchasingPowerMXNValue)).getText();
    }
    
    public boolean isBreakdownItemPresent(String itemName) {
        By itemLocator = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'" + itemName + "')]");
        try {
            return driver.findElement(itemLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean verifyZeroValuesDisplayCorrectly() {
        List<WebElement> items = driver.findElements(breakdownItems);
        for (WebElement item : items) {
            String value = item.findElement(By.className("value")).getText();
            if (value.isEmpty() || (!value.contains("$") && !value.equals("$0.00"))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isBreakdownVerticallyAligned() {
        WebElement contractValue = driver.findElement(totalContractValueComponent);
        WebElement popup = driver.findElement(breakdownPopup);
        
        int contractX = contractValue.getLocation().getX();
        int popupX = popup.getLocation().getX();
        
        return Math.abs(contractX - popupX) <= 5;
    }
}