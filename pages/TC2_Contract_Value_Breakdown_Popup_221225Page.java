package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class ContractValuePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("loginBtn");
    private By contractSelector = By.xpath("//div[@class='contract-selector']");
    private By contractValueComponent = By.xpath("//div[@class='contract-value-component']");
    private By breakdownPopup = By.xpath("//div[@class='contract-breakdown-popup']");
    private By poderCompraMXN = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Poder de compra MXN')]");
    private By efectivoMXN = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo MXN')]");
    private By efectivoUSD = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo USD')]");
    private By pendientesLiquidar = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Pendientes por liquidar')]");
    private By fondosDeuda = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de deuda')]");
    private By fondosCobertura = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de cobertura')]");
    private By fondosRentaVariable = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Fondos de renta variable')]");
    private By efectivoTransito = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Efectivo en tránsito')]");
    private By cedesPagares = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Cedes y pagarés')]");
    private By mercadoDinero = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Mercado de dinero')]");
    private By mercadoCapitales = By.xpath("//div[@class='breakdown-item']//span[contains(text(),'Mercado de capitales')]");
    private By breakdownItems = By.xpath("//div[@class='breakdown-item']");
    private By breakdownValues = By.xpath("//div[@class='breakdown-item']//span[@class='value']");

    public ContractValuePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToActicenter() {
        driver.get("https://acticenter.com");
    }

    public void performLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        driver.findElement(usernameInput).sendKeys("testuser");
        driver.findElement(passwordInput).sendKeys("testpassword");
        driver.findElement(loginButton).click();
    }

    public void selectContract() {
        wait.until(ExpectedConditions.elementToBeClickable(contractSelector));
        driver.findElement(contractSelector).click();
    }

    public boolean areValuationServicesAvailable() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contractValueComponent));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContractValueComponentVisible() {
        try {
            return driver.findElement(contractValueComponent).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContractValueComponent() {
        wait.until(ExpectedConditions.elementToBeClickable(contractValueComponent));
        driver.findElement(contractValueComponent).click();
    }

    public boolean isBreakdownPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(breakdownPopup));
            return driver.findElement(breakdownPopup).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateApplicableItems() {
        try {
            List<WebElement> items = driver.findElements(breakdownItems);
            return items.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateMonetaryFormat() {
        try {
            List<WebElement> values = driver.findElements(breakdownValues);
            Pattern monetaryPattern = Pattern.compile("^(\\$|USD \\$)?[0-9]{1,3}(,[0-9]{3})*\\.[0-9]{2}$");
            for (WebElement value : values) {
                String valueText = value.getText().trim();
                if (!monetaryPattern.matcher(valueText).matches()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPopupVerticallyAligned() {
        try {
            WebElement component = driver.findElement(contractValueComponent);
            WebElement popup = driver.findElement(breakdownPopup);
            int componentX = component.getLocation().getX();
            int popupX = popup.getLocation().getX();
            return Math.abs(componentX - popupX) <= 10;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateZeroValueItems() {
        try {
            List<WebElement> values = driver.findElements(breakdownValues);
            boolean hasZeroValue = false;
            for (WebElement value : values) {
                String valueText = value.getText().trim();
                if (valueText.contains("$0.00") || valueText.contains("USD $0.00")) {
                    hasZeroValue = true;
                }
            }
            return hasZeroValue;
        } catch (Exception e) {
            return false;
        }
    }
}