package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import TestComponents.TestContextSetup;
import io.cucumber.java.en.When;
import org.testng.Assert;
import webstore.pages.CatalogPage;
import webstore.pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class CatalogPageStepDefinitions {
    TestContextSetup testContextSetup;
    CatalogPage catalogPage;
    ProductPage productPage;
    public CatalogPageStepDefinitions(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
        this.catalogPage = testContextSetup.pageObjectManager.catalogPage;
        this.productPage = testContextSetup.pageObjectManager.getProductPage();
    }

    @Then("all product in catalog entries contains {string} or {string} or {string}")
    public void the_all_catalog_entries_contains_or_or(String keyword1, String keyword2, String keyword3) {
        // Write code here that turns the phrase above into concrete actions
        List<String> suggestions = new ArrayList<>();
        suggestions.add(keyword1);
        suggestions.add(keyword2);
        suggestions.add(keyword3);
        boolean match = catalogPage.verifyProductIsProductCatalog(suggestions);
        Assert.assertTrue(match);

    }

    @And("click in the first product")
    public void click_in_a_product() {
        this.testContextSetup.pageObjectManager.productPage=catalogPage.clickOnFirstMatch();
    }

    @And("^the user filter by size (.+)$")
    public void the_user_filter_by_size(String size) {
        catalogPage.filterBySize(size);

    }
    @And("^the user filter by price (.+)$")
    public void the_user_filter_by_price(String price) {
        catalogPage.filterByPrice(price);
    }
    @And("^the user filter by brand (.+)$")
    public void the_user_filter_by_brand(String brand) {
        catalogPage.filterByBrand(brand);
    }
    @When("^the products are retrieved with (.+), (.+), (.+)$")
    public void the_products_are_retrieved(String size, String brand, String price) {
        Assert.assertTrue(catalogPage.verifyProductIsProductCatalog(size));
        Assert.assertTrue(catalogPage.priceInCatalog(Double.parseDouble(price)));
        Assert.assertTrue(catalogPage.verifyProductIsProductCatalog(brand));
    }
    @Then("^total count in the catalog is (.+)$")
    public void the_product_name_and_price_are_displayed_in_the_product_page(String count) {
        boolean actualCounting =catalogPage.checkCountInResults(Integer.parseInt(count));
        Assert.assertTrue(actualCounting);
    }

    @Then("^all product contains the same brand (.+)$")
    public void allProductContainsTheSameBrandBrand(String brand) {
        boolean match =catalogPage.verifyProductIsProductCatalog(brand);
        Assert.assertTrue(match);
    }

    @And("click in view more brands")
    public void clickInViewMoreBrands() {
        catalogPage.clickViewMoreBrandsLink();
    }
}
