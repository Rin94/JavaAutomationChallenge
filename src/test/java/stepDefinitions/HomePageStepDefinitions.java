package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import TestComponents.TestContextSetup;
import webstore.pages.CatalogPage;
import webstore.pages.HomePage;

public class HomePageStepDefinitions{
    TestContextSetup testContextSetup;
    HomePage homePage;

    public HomePageStepDefinitions(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
        this.homePage =testContextSetup.pageObjectManager.getLandingPage();
    }
    @Given("I landed on liverpool page")
    public void i_landed_on_liverpool_page() {
        // Write code here that turns the phrase above into concrete actions
        homePage.goTo();
    }

    @When("an user search for {string}")
    public void an_user_search_for(String product) {
        // Write code here that turns the phrase above into concrete actions
        testContextSetup.pageObjectManager.catalogPage =homePage.searchProducts(product);
    }

    @Given("^the user search by (.+) and (.+)")
    public void the_user_search_by_belleza_and_perfumes_hombre(String category, String subCategory) {
        // Write code here that turns the phrase above into concrete actions
        testContextSetup.pageObjectManager.catalogPage =homePage.searchByCategories(category,subCategory);
    }

}