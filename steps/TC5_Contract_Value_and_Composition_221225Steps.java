package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ScreenOrientation;
import pages.ActicenterContractPage;
import static org.junit.Assert.*;

public class ContractValueComponentSteps {
    private WebDriver driver;
    private ActicenterContractPage acticenterPage;
    
    public ContractValueComponentSteps(WebDriver driver) {
        this.driver = driver;
        this.acticenterPage = new ActicenterContractPage(driver);
    }
    
    @Given("the user is authenticated in Acticenter")
    public void theUserIsAuthenticatedInActicenter() {
        acticenterPage.navigateToActicenter();
        acticenterPage.login();
    }
    
    @And("the mobile device or tablet is available")
    public void theMobileDeviceOrTabletIsAvailable() {
        acticenterPage.verifyMobileDeviceReady();
    }
    
    @And("Persona Moral Banco and Casa de Bolsa contracts are available for testing")
    public void personaMoralBancoAndCasaDeBolsaContractsAreAvailableForTesting() {
        assertTrue(acticenterPage.verifyContractsAvailable());
    }
    
    @Given("the device is in portrait orientation")
    public void theDeviceIsInPortraitOrientation() {
        acticenterPage.setDeviceOrientation("portrait");
    }
    
    @When("the user accesses the Acticenter module from a mobile device")
    public void theUserAccessesTheActicenterModuleFromAMobileDevice() {
        acticenterPage.accessActicenterModule();
    }
    
    @Then("the system loads the Acticenter interface in responsive portrait mode")
    public void theSystemLoadsTheActicenterInterfaceInResponsivePortraitMode() {
        assertTrue(acticenterPage.isPortraitModeActive());
    }
    
    @When("the user searches for a Persona Moral Banco contract using the search function with magnifying glass")
    public void theUserSearchesForAPersonaMoralBancoContractUsingTheSearchFunctionWithMagnifyingGlass() {
        acticenterPage.clickSearchIcon();
        acticenterPage.searchContract("Persona Moral Banco");
        acticenterPage.selectFirstContract();
    }
    
    @Then("the system displays the selected contract with the total value component adapted to portrait view")
    public void theSystemDisplaysTheSelectedContractWithTheTotalValueComponentAdaptedToPortraitView() {
        assertTrue(acticenterPage.isTotalValueComponentVisible());
        assertTrue(acticenterPage.isComponentAdaptedToPortrait());
    }
    
    @When("the user clicks on the total value component to display the breakdown in portrait orientation")
    public void theUserClicksOnTheTotalValueComponentToDisplayTheBreakdownInPortraitOrientation() {
        acticenterPage.clickTotalValueComponent();
    }
    
    @Then("the breakdown popup is displayed correctly adapted to portrait orientation showing all applicable items vertically")
    public void theBreakdownPopupIsDisplayedCorrectlyAdaptedToPortraitOrientationShowingAllApplicableItemsVertically() {
        assertTrue(acticenterPage.isBreakdownPopupVisible());
        assertTrue(acticenterPage.isBreakdownVerticalLayout());
    }
    
    @And("all breakdown items are visible and readable in portrait orientation")
    public void allBreakdownItemsAreVisibleAndReadableInPortraitOrientation() {
        assertTrue(acticenterPage.areAllBreakdownItemsVisible());
    }
    
    @And("all items including Efectivo MXN, Efectivo USD for Mexdolar, Pendientes por liquidar, Fondos, Cedes y pagarés, and Mercado de dinero y capitales are shown correctly with aligned values")
    public void allItemsIncludingEfectivoMXNEfectivoUSDForMexdolarPendientesPorLiquidarFondosCedesYPagaresMercadoDeDineroYCapitalesAreShownCorrectlyWithAlignedValues() {
        assertTrue(acticenterPage.verifyBreakdownItem("Efectivo MXN"));
        assertTrue(acticenterPage.verifyBreakdownItem("Efectivo USD"));
        assertTrue(acticenterPage.verifyBreakdownItem("Pendientes por liquidar"));
        assertTrue(acticenterPage.verifyBreakdownItem("Fondos"));
        assertTrue(acticenterPage.verifyBreakdownItem("Cedes y pagarés"));
        assertTrue(acticenterPage.verifyBreakdownItem("Mercado de dinero y capitales"));
        assertTrue(acticenterPage.areValuesAligned());
    }
    
    @When("the user rotates the device to landscape orientation")
    public void theUserRotatesTheDeviceToLandscapeOrientation() {
        acticenterPage.setDeviceOrientation("landscape");
    }
    
    @Then("the system automatically adapts the interface to landscape orientation maintaining the selected contract")
    public void theSystemAutomaticallyAdaptsTheInterfaceToLandscapeOrientationMaintainingTheSelectedContract() {
        assertTrue(acticenterPage.isLandscapeModeActive());
        assertTrue(acticenterPage.isContractStillSelected());
    }
    
    @And("the total value component is displayed correctly in landscape orientation")
    public void theTotalValueComponentIsDisplayedCorrectlyInLandscapeOrientation() {
        assertTrue(acticenterPage.isTotalValueComponentVisible());
        assertTrue(acticenterPage.isComponentAdaptedToLandscape());
    }
    
    @When("the user clicks on the total value component to display the breakdown in landscape orientation")
    public void theUserClicksOnTheTotalValueComponentToDisplayTheBreakdownInLandscapeOrientation() {
        acticenterPage.clickTotalValueComponent();
    }
    
    @Then("the breakdown popup is displayed correctly adapted to landscape orientation")
    public void theBreakdownPopupIsDisplayedCorrectlyAdaptedToLandscapeOrientation() {
        assertTrue(acticenterPage.isBreakdownPopupVisible());
        assertTrue(acticenterPage.isBreakdownAdaptedToLandscape());
    }
    
    @And("the component adapts to landscape orientation without loss of information or functionality")
    public void theComponentAdaptsToLandscapeOrientationWithoutLossOfInformationOrFunctionality() {
        assertTrue(acticenterPage.areAllBreakdownItemsVisible());
        assertTrue(acticenterPage.isPopupFunctional());
    }
    
    @When("the user searches for a Persona Moral Casa de Bolsa contract")
    public void theUserSearchesForAPersonaMoralCasaDeBolsaContract() {
        acticenterPage.clickSearchIcon();
        acticenterPage.searchContract("Persona Moral Casa de Bolsa");
        acticenterPage.selectFirstContract();
    }
    
    @Then("the component and breakdown adapt correctly in portrait orientation showing Casa de Bolsa specific items")
    public void theComponentAndBreakdownAdaptCorrectlyInPortraitOrientationShowingCasaDeBolsaSpecificItems() {
        acticenterPage.clickTotalValueComponent();
        assertTrue(acticenterPage.isBreakdownPopupVisible());
        assertTrue(acticenterPage.verifyBreakdownItem("Poder de compra MXN"));
    }
    
    @And("the breakdown shows items including Poder de compra MXN and Efectivo USD")
    public void theBreakdownShowsItemsIncludingPoderDeCompraMXNAndEfectivoUSD() {
        assertTrue(acticenterPage.verifyBreakdownItem("Poder de compra MXN"));
        assertTrue(acticenterPage.verifyBreakdownItem("Efectivo USD"));
    }
    
    @Then("the component and breakdown adapt correctly in landscape orientation showing Casa de Bolsa specific items")
    public void theComponentAndBreakdownAdaptCorrectlyInLandscapeOrientationShowingCasaDeBolsaSpecificItems() {
        assertTrue(acticenterPage.isComponentAdaptedToLandscape());
        acticenterPage.clickTotalValueComponent();
        assertTrue(acticenterPage.isBreakdownPopupVisible());
        assertTrue(acticenterPage.verifyBreakdownItem("Poder de compra MXN"));
    }
    
    @When("the user uses the client contract search function with magnifying glass")
    public void theUserUsesTheClientContractSearchFunctionWithMagnifyingGlass() {
        acticenterPage.clickSearchIcon();
        acticenterPage.searchContract("test contract");
    }
    
    @Then("the search function operates correctly allowing to search and select contracts")
    public void theSearchFunctionOperatesCorrectlyAllowingToSearchAndSelectContracts() {
        assertTrue(acticenterPage.isSearchFunctionWorking());
    }
    
    @Then("the search function operates correctly in landscape orientation")
    public void theSearchFunctionOperatesCorrectlyInLandscapeOrientation() {
        acticenterPage.clickSearchIcon();
        acticenterPage.searchContract("test contract");
        assertTrue(acticenterPage.isSearchFunctionWorking());
    }
    
    @When("the user opens the breakdown popup in portrait orientation")
    public void theUserOpensTheBreakdownPopupInPortraitOrientation() {
        acticenterPage.clickTotalValueComponent();
        assertTrue(acticenterPage.isBreakdownPopupVisible());
    }
    
    @And("the user clicks outside the component")
    public void theUserClicksOutsideTheComponent() {
        acticenterPage.clickOutsidePopup();
    }
    
    @Then("the popup closes correctly in portrait orientation")
    public void thePopupClosesCorrectlyInPortraitOrientation() {
        assertFalse(acticenterPage.isBreakdownPopupVisible());
    }
    
    @When("the user rotates to landscape orientation and opens the breakdown popup")
    public void theUserRotatesToLandscapeOrientationAndOpensTheBreakdownPopup() {
        acticenterPage.setDeviceOrientation("landscape");
        acticenterPage.clickTotalValueComponent();
        assertTrue(acticenterPage.isBreakdownPopupVisible());
    }
    
    @Then("the popup closes correctly in landscape orientation")
    public void thePopupClosesCorrectlyInLandscapeOrientation() {
        assertFalse(acticenterPage.isBreakdownPopupVisible());
    }
}