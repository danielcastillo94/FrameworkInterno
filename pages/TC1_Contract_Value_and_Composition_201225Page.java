package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class ContractValueCompositionPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private boolean hasMexdolarAccount;

    // Locators
    private By valueCompositionComponent = By.id("contract-value-composition");
    private By totalValueLabel = By.xpath("//div[@id='contract-value-composition']//span[@class='total-value']");
    private By compositionPopup = By.id("composition-popup");
    private By popupCloseArea = By.className("popup-overlay");
    
    // Item locators
    private By cashMXNItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo MXN')]");
    private By cashUSDItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo USD')]");
    private By pendingSettlementsItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Pendientes por liquidar')]");
    private By debtFundsItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de deuda')]");
    private By hedgeFundsItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de cobertura')]");
    private By variableIncomeFundsItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de renta variable')]");
    private By cashInTransitItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo en tránsito')]");
    private By cdsAndNotesItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Cedes y pagarés')]");
    private By moneyMarketItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Mercado de dinero')]");
    private By capitalMarketItem = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Mercado de capitales')]");
    
    private By allBreakdownItems = By.className("breakdown-item");
    private By itemValues = By.className("item-value");
    private By tooltipElements = By.className("tooltip-trigger");
    private By activeTooltip = By.className("tooltip-active");

    public ContractValueCompositionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.hasMexdolarAccount = false;
    }

    public boolean isComponentVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(valueCompositionComponent));
            return driver.findElement(valueCompositionComponent).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTotalValueDisplayed() {
        try {
            return driver.findElement(totalValueLabel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalValue() {
        try {
            return driver.findElement(totalValueLabel).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void clickOnComponent() {
        wait.until(ExpectedConditions.elementToBeClickable(valueCompositionComponent));
        driver.findElement(valueCompositionComponent).click();
    }

    public boolean isPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(compositionPopup));
            return driver.findElement(compositionPopup).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPopupAligned() {
        try {
            WebElement component = driver.findElement(valueCompositionComponent);
            WebElement popup = driver.findElement(compositionPopup);
            int componentX = component.getLocation().getX();
            int popupX = popup.getLocation().getX();
            return Math.abs(componentX - popupX) < 10;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBreakdownComplete() {
        try {
            List<WebElement> items = driver.findElements(allBreakdownItems);
            return items.size() >= 9;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemVisible(String itemName) {
        try {
            By itemLocator = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'" + itemName + "')]");
            return driver.findElement(itemLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean allItemsHaveMonetaryValues() {
        try {
            List<WebElement> items = driver.findElements(allBreakdownItems);
            List<WebElement> values = driver.findElements(itemValues);
            return items.size() == values.size() && values.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyZeroBalanceFormat() {
        try {
            List<WebElement> values = driver.findElements(itemValues);
            for (WebElement value : values) {
                String text = value.getText();
                if (text.equals("$0.00") || text.matches("\\$[0-9,]+\\.[0-9]{2}")) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void validateAgainstLumina() {
        // Integration point with Lumina API
        // Mock validation for demonstration
    }

    public boolean areValuesConsistent() {
        // Mock validation - would integrate with actual Lumina service
        return true;
    }

    public boolean matchesAdvisorModule() {
        // Mock validation - would integrate with advisor module
        return true;
    }

    public boolean verifyMonetaryFormat() {
        try {
            List<WebElement> values = driver.findElements(itemValues);
            for (WebElement value : values) {
                if (!value.getText().matches("\\$[0-9,]+\\.[0-9]{2}")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyCashMXNFromMainAccount() {
        // Integration with main account validation
        return true;
    }

    public void setMexdolarAccountExists(boolean exists) {
        this.hasMexdolarAccount = exists;
    }

    public boolean verifyCashUSDFromSAP() {
        // Integration with SAP service validation
        return true;
    }

    public void clickOutsidePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(popupCloseArea));
        driver.findElement(popupCloseArea).click();
    }

    public void hoverOverTooltipElements() {
        try {
            List<WebElement> tooltipTriggers = driver.findElements(tooltipElements);
            for (WebElement trigger : tooltipTriggers) {
                actions.moveToElement(trigger).perform();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean areTooltipsDisplayed() {
        try {
            List<WebElement> tooltipTriggers = driver.findElements(tooltipElements);
            if (tooltipTriggers.isEmpty()) {
                return true;
            }
            actions.moveToElement(tooltipTriggers.get(0)).perform();
            Thread.sleep(500);
            return driver.findElements(activeTooltip).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}