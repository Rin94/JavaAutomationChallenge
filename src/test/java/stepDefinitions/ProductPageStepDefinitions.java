package stepDefinitions;

import TestComponents.TestContextSetup;
import io.cucumber.java.en.Then;
import webstore.pages.ProductPage;

public class ProductPageStepDefinitions {

    TestContextSetup testContextSetup;
    ProductPage productPage;

    public ProductPageStepDefinitions(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
        this.productPage =testContextSetup.pageObjectManager.getProductPage();
    }

    @Then("^the product name (.+) and price (.+) are displayed in the product page$")
    public void theProductNameNameAndPricePriceAreDisplayedInTheProductPage(String productTitle, String productPrice) {
        productPage.checkProductTitle(productTitle);
        productPage.checkProductPrice(productPrice);
    }
}
