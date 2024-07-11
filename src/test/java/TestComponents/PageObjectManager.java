package TestComponents;

import org.openqa.selenium.WebDriver;
import webstore.pages.*;

public class PageObjectManager {

    public HomePage homePage;
    public CatalogPage catalogPage;
    public ProductPage productPage;
    public WebDriver driver;
    public PageObjectManager(WebDriver driver)
    {
        this.driver = driver;
    }
    public HomePage getLandingPage() {
        homePage= new HomePage(driver);
        return homePage;
    }
    public CatalogPage getCatalogPage() {
        catalogPage = new CatalogPage(driver);
        return catalogPage;
    }

    public ProductPage getProductPage() {
        productPage = new ProductPage(driver);
        return productPage;
    }
}