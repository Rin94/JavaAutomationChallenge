package webstore.pages;

import basepage.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GlobalVariables;
import webstore.objectRepository.HomePageObjects;
import webstore.objectRepository.ProductPageObjects;

public class ProductPage extends BasePage{

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = ProductPageObjects.XPATH_TXT_PRODUCT_TITLE)
	private WebElement txtProductTitle;

	@FindBy(xpath = ProductPageObjects.XPATH_TXT_PRODUCT_PRICE)
	private WebElement txtProductPriceDiscount;

	public boolean checkProductTitle(String productTitle) {
		waitUntilElementIsDisplayed(txtProductTitle, GlobalVariables.DELAY_LOW);
		String actualTitle = getElementText(txtProductTitle).trim().toUpperCase();
		return actualTitle.equalsIgnoreCase(productTitle.trim());
	}

	public boolean checkProductPrice(String productPrice) {
		waitUntilElementIsDisplayed(txtProductTitle, GlobalVariables.DELAY_LOW);
		String actualTitle = getElementText(txtProductTitle).trim().toUpperCase();
		return actualTitle.equalsIgnoreCase(productPrice.trim());
	}



}
