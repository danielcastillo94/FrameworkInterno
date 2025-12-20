package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ActicenterLoginPage;
import pages.ContractSearchPage;
import pages.ContractValueCompositionPage;
import org.junit.Assert;

public class CashInTransitVerificationSteps {
    private WebDriver driver;
    private ActicenterLoginPage loginPage;
    private ContractSearchPage contractSearchPage;
    private ContractValueCompositionPage compositionPage;

    @Given("the user is authenticated with wealth management permissions")
    public void userIsAuthenticatedWithWealthManagementPermissions() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("SAP prenotes service is operational")
    public void sapPrenotesServiceIsOperational() {
        // Verify SAP prenotes service availability
        // This could be a health check or assumption
    }

    @Given("Figma responsive designs are available as reference")
    public void figmaResponsiveDesignsAreAvailable() {
        // Reference designs validation
    }

    @Given("the user accesses Acticenter with wealth management credentials")
    public void userAccessesActicenterWithCredentials() {
        loginPage = new ActicenterLoginPage(driver);
        loginPage.navigateToActicenter();
        loginPage.login("wealth_advisor_user", "password123");
    }

    @When("the user searches and selects a corporate bank contract with Mexdolar account")
    public void userSearchesAndSelectsCorporateBankContract() {
        contractSearchPage = new ContractSearchPage(driver);
        contractSearchPage.searchContractByType("corporate_bank_mexdolar");
        contractSearchPage.selectFirstContract();
    }

    @When("the user clicks on the contract value and composition component")
    public void userClicksOnContractValueCompositionComponent() {
        compositionPage = new ContractValueCompositionPage(driver);
        compositionPage.clickValueCompositionComponent();
    }

    @Then("the popup with applicable items breakdown is displayed")
    public void popupWithItemsBreakdownIsDisplayed() {
        Assert.assertTrue("Popup should be visible", compositionPage.isPopupVisible());
    }

    @And("the Cash in Transit item shows the value from SAP prenotes service or $0.00 if none")
    public void cashInTransitItemShowsValueFromSAP() {
        String cashInTransit = compositionPage.getCashInTransitValue();
        Assert.assertNotNull("Cash in Transit value should be displayed", cashInTransit);
        Assert.assertTrue("Cash in Transit should show valid amount", 
            cashInTransit.matches("\\$[0-9,]+\\.[0-9]{2}"));
    }

    @And("the USD Cash item shows the Mexdolar account balance from SAP without conversion")
    public void usdCashItemShowsMexdolarBalance() {
        String usdCash = compositionPage.getUSDCashValue();
        Assert.assertNotNull("USD Cash value should be displayed", usdCash);
        Assert.assertTrue("USD Cash should be in USD format", usdCash.contains("USD") || usdCash.startsWith("$"));
    }

    @And("the MXN Cash item shows the balance from the bank contract main account")
    public void mxnCashItemShowsMainAccountBalance() {
        String mxnCash = compositionPage.getMXNCashValue();
        Assert.assertNotNull("MXN Cash value should be displayed", mxnCash);
        Assert.assertTrue("MXN Cash should show valid amount", 
            mxnCash.matches("\\$[0-9,]+\\.[0-9]{2}"));
    }

    @When("the user closes the popup by clicking outside the component area")
    public void userClosesPopupByClickingOutside() {
        compositionPage.closePopupByClickingOutside();
    }

    @Then("the popup closes correctly")
    public void popupClosesCorrectly() {
        Assert.assertFalse("Popup should be closed", compositionPage.isPopupVisible());
    }

    @When("the user changes device orientation to portrait mode")
    public void userChangesOrientationToPortrait() {
        compositionPage.changeOrientationToPortrait();
    }

    @Then("the value and composition component adapts correctly to portrait according to Figma designs")
    public void componentAdaptsToPortrait() {
        Assert.assertTrue("Component should adapt to portrait", 
            compositionPage.isComponentResponsiveInPortrait());
    }

    @When("the user opens the popup again in portrait mode")
    public void userOpensPopupInPortraitMode() {
        compositionPage.clickValueCompositionComponent();
    }

    @Then("all items are visible, readable and correctly aligned in portrait")
    public void allItemsVisibleInPortrait() {
        Assert.assertTrue("All items should be visible in portrait", 
            compositionPage.areAllItemsVisibleAndAligned());
    }

    @When("the user changes device orientation to landscape mode")
    public void userChangesOrientationToLandscape() {
        compositionPage.changeOrientationToLandscape();
    }

    @Then("the component adapts correctly to landscape according to Figma designs")
    public void componentAdaptsToLandscape() {
        Assert.assertTrue("Component should adapt to landscape", 
            compositionPage.isComponentResponsiveInLandscape());
    }

    @When("the user opens the popup in landscape mode")
    public void userOpensPopupInLandscapeMode() {
        compositionPage.clickValueCompositionComponent();
    }

    @Then("all items are correctly aligned, readable and accessible in landscape")
    public void allItemsAlignedInLandscape() {
        Assert.assertTrue("All items should be aligned in landscape", 
            compositionPage.areAllItemsVisibleAndAligned());
    }

    @And("the popup close functionality works correctly in both portrait and landscape orientations")
    public void popupCloseFunctionalityWorksInBothOrientations() {
        compositionPage.closePopupByClickingOutside();
        Assert.assertFalse("Popup should close in landscape", compositionPage.isPopupVisible());
        
        compositionPage.changeOrientationToPortrait();
        compositionPage.clickValueCompositionComponent();
        compositionPage.closePopupByClickingOutside();
        Assert.assertFalse("Popup should close in portrait", compositionPage.isPopupVisible());
        
        driver.quit();
    }
}