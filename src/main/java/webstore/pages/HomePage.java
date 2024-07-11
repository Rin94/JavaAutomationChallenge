package webstore.pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basepage.BasePage;
import utils.GlobalVariables;
import webstore.objectRepository.HomePageObjects;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = HomePageObjects.XPATH_TXT_SEARCH_BAR)
	private WebElement txtSearchBar;

	@FindBy(xpath = HomePageObjects.XPATH_TXT_HAMBURGER_MENU)
	private WebElement txtHamburgerMenu;

	public CatalogPage searchProducts(String product) {
		enterText(txtSearchBar, product);
		enterText(txtSearchBar, Keys.ENTER);
		return new CatalogPage(driver);
	}

	public CatalogPage searchByCategories(String category, String subCategory){
		waitUntilElementIsDisplayed(txtHamburgerMenu, GlobalVariables.DELAY_MEDIUM);
		clickElement(txtHamburgerMenu);
		delay(GlobalVariables.DELAY_LOW);
		WebElement categoryLink = findElementByXpathGivenAString(HomePageObjects.XPATH_TXT_CATEGORY_LINK,category);
		hoverToElement(categoryLink);
		WebElement subCategoryLink = findElementByXpathGivenAString(HomePageObjects.XPATH_TXT_SUB_CATEGORY_LINK,subCategory);
		clickElement(subCategoryLink);
		return new CatalogPage(driver);
	}

	public void goTo() {
		driver.get(GlobalVariables.WEB_URL);
		waitUntilElementIsDisplayed(txtSearchBar, GlobalVariables.DELAY_HIGH);
	}

}
