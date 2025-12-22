package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.FundCategoriesPage;
import static org.junit.Assert.*;

public class FundCategoriesSteps {
    private WebDriver driver;
    private FundCategoriesPage fundCategoriesPage;
    
    public FundCategoriesSteps(WebDriver driver) {
        this.driver = driver;
        this.fundCategoriesPage = new FundCategoriesPage(driver);
    }
    
    @Given("the Lumina services for funds are operational and returning correct data")
    public void luminaServicesAreOperational() {
        fundCategoriesPage.verifyLuminaServicesStatus();
    }
    
    @Given("I select a contract that has investments in debt funds")
    public void selectContractWithDebtFunds() {
        fundCategoriesPage.selectContractWithDebtFunds();
    }
    
    @When("I expand the contract composition breakdown")
    public void expandCompositionBreakdown() {
        fundCategoriesPage.clickCompositionBreakdown();
    }
    
    @Then("the popup should display the breakdown with all applicable categories")
    public void verifyPopupDisplaysBreakdown() {
        assertTrue(fundCategoriesPage.isBreakdownPopupDisplayed());
        assertTrue(fundCategoriesPage.areAllCategoriesPresent());
    }
    
    @And("the debt funds category should be present and display the corresponding monetary total")
    public void verifyDebtFundsCategory() {
        assertTrue(fundCategoriesPage.isDebtFundsCategoryPresent());
        assertNotNull(fundCategoriesPage.getDebtFundsTotal());
        assertTrue(fundCategoriesPage.getDebtFundsTotal().matches("\\$\\d+\\.\\d{2}"));
    }
    
    @And("the hedge funds category should be present and display the corresponding monetary total")
    public void verifyHedgeFundsCategory() {
        assertTrue(fundCategoriesPage.isHedgeFundsCategoryPresent());
        assertNotNull(fundCategoriesPage.getHedgeFundsTotal());
        assertTrue(fundCategoriesPage.getHedgeFundsTotal().matches("\\$\\d+\\.\\d{2}"));
    }
    
    @And("the equity funds category should be present and display the corresponding monetary total")
    public void verifyEquityFundsCategory() {
        assertTrue(fundCategoriesPage.isEquityFundsCategoryPresent());
        assertNotNull(fundCategoriesPage.getEquityFundsTotal());
        assertTrue(fundCategoriesPage.getEquityFundsTotal().matches("\\$\\d+\\.\\d{2}"));
    }
    
    @And("the displayed values should match the data obtained from Lumina services")
    public void verifyValuesMatchLuminaData() {
        assertTrue(fundCategoriesPage.validateDebtFundsAgainstLumina());
        assertTrue(fundCategoriesPage.validateHedgeFundsAgainstLumina());
        assertTrue(fundCategoriesPage.validateEquityFundsAgainstLumina());
    }
    
    @When("I select a contract without investments in some fund types")
    public void selectContractWithoutSomeFunds() {
        fundCategoriesPage.closeBreakdownPopup();
        fundCategoriesPage.selectContractWithoutInvestments();
        fundCategoriesPage.clickCompositionBreakdown();
    }
    
    @Then("the fund categories without investments should display $0.00")
    public void verifyEmptyFundsShowZero() {
        String emptyFundValue = fundCategoriesPage.getEmptyFundCategoryValue();
        assertEquals("$0.00", emptyFundValue);
    }
}