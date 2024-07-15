package webstore.pages;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basepage.BasePage;
import org.testng.Assert;
import org.testng.Reporter;
import utils.ExtentReporterNG;
import utils.GlobalVariables;
import utils.PageScroll;
import webstore.objectRepository.CatalogPageObjects;

public class CatalogPage extends BasePage{

	public CatalogPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = CatalogPageObjects.XPATH_TXT_PRODUCT_NAMES)
	private List <WebElement> txtProductNamesList;

	@FindBy(xpath = CatalogPageObjects.XPATH_TXT_PRODUCT_NAMES)
	private WebElement txtFirstProductName;

	@FindBy(xpath = CatalogPageObjects.XPATH_TXT_LINK_VIEW_MORE_SIZES)
	private WebElement txtLinkViewMoreSizes;
	@FindBy(xpath = CatalogPageObjects.XPATH_TXT_PRODUCT_PRICES)
	private List<WebElement> txtProductPricesList;
	@FindBy(xpath = CatalogPageObjects.XPATH_CHECKBOX_SIZES)
	private WebElement checkboxSizes;
	@FindBy(xpath = CatalogPageObjects.XPATH_CHECKBOX_BRAND)
	private WebElement checkBoxBrand;
	@FindBy(xpath = CatalogPageObjects.XPATH_RADIO_BTN_PRICE)
	private WebElement radioBtnPrice;
	@FindBy(xpath = CatalogPageObjects.XPATH_TXT_VIEW_MORE_BRANDS)
	private WebElement txtLinkViewMoreBrands;



	public boolean verifyProductIsProductCatalog(List <String> suggestionList ) {
		waitUntilElementIsDisplayed(txtFirstProductName, GlobalVariables.DELAY_HIGH);
		boolean flag = true;
		for (int i =0; i< txtProductNamesList.size(); i++){
			String result = getElementText(txtProductNamesList.get(i)).trim().toUpperCase();
			int count=0;
			for (int j=0; j<suggestionList.size();j++){
				String expectedString =  suggestionList.get(j);
                if (StringUtils.containsIgnoreCase(result,expectedString)) {
					Reporter.log(result +" -- is in --"+ expectedString);
					System.out.println(result +" -- is in --"+ expectedString);
					flag=true;
					break;
                }
				count=count+1;
				if(count>=suggestionList.size()){
					Reporter.log(result +" -- is not --"+ expectedString,0);
					System.out.println(result +" -- is not --"+ expectedString);
					flag = false;
					break;
				}
            }
		}
		return flag;
	}

	public boolean verifyProductIsProductCatalog(String suggestion ) {
		List<String> suggestionList = new ArrayList<>();
		suggestionList.add(suggestion);
		waitUntilElementIsDisplayed(txtFirstProductName, GlobalVariables.DELAY_HIGH);
		boolean flag = true;
		for (int i =0; i< txtProductNamesList.size(); i++){
			String result = getElementText(txtProductNamesList.get(i)).trim().toUpperCase();
			int count=0;
			for (int j=0; j<suggestionList.size();j++){
				String expectedString =  suggestionList.get(j);
				if (StringUtils.containsIgnoreCase(result,expectedString)) {
					System.out.println(result +" -- is in --"+ expectedString);
					flag=true;
					break;
				}
				count=count+1;
				if(count>=suggestionList.size()){
					System.out.println(result +" -- is not --"+ expectedString);
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	public void filterBySize(String size){
		waitUntilElementIsDisplayed(txtLinkViewMoreSizes, GlobalVariables.DELAY_MEDIUM);
		clickElement(txtLinkViewMoreSizes);
		WebElement checkboxLocator = findElementByXpathGivenAString(CatalogPageObjects.XPATH_CHECKBOX_SIZES,size);
		clickElement(checkboxLocator);
		delay(GlobalVariables.DELAY_LOW);
	}

	public void filterByPrice(String price){
		WebElement radioButtonPriceLocator = findElementByXpathGivenAString(CatalogPageObjects.XPATH_RADIO_BTN_PRICE,price);
		PageScroll.scrollToElement(radioButtonPriceLocator,driver);
		clickUsingActions(radioButtonPriceLocator);
		delay(GlobalVariables.DELAY_LOW);
	}

	public void filterByBrand(String brand){
		WebElement checkBoxBrand= findElementByXpathGivenAString(CatalogPageObjects.XPATH_CHECKBOX_BRAND,brand);
		clickElement(checkBoxBrand);
		delay(GlobalVariables.DELAY_LOW);
	}

	public boolean priceInCatalog(double filterPrice ){
		boolean flag = true;
		for (WebElement productPrice : txtProductPricesList){
			String price = getElementText(productPrice).replace("$", "").replace(",","");
			price = StringUtils.substring(price, 0, price.length() - 2);
			Double priceNumber = Double.parseDouble(price);
			if (priceNumber> filterPrice){
				System.out.println(priceNumber + " is greater than " + filterPrice);
				flag = true;
			}
			else{
				System.out.println(priceNumber + " is lower than " + filterPrice);
				flag = false;
				break;
			}
		}
		return flag;
	}
	public boolean checkCountInResults(int expectedCount){
		long actualCount = txtProductPricesList.stream().count();
		if (expectedCount == actualCount){
			return true;
		}
		else {
			return false;
		}
	}
	public void clickViewMoreBrandsLink(){
		waitUntilElementIsDisplayed(txtLinkViewMoreBrands, GlobalVariables.DELAY_MEDIUM);
		clickElement(txtLinkViewMoreBrands);
	}

	public ProductPage clickOnFirstMatch(){
		clickElement(txtProductNamesList.get(0));
		return new ProductPage(driver);
	}

	public String retrieveFirstMatchName(){
		return getElementText(txtProductNamesList.get(0)) ;
	}

	public String retrieveFirstMatchDiscountPrice(){
		String price = getElementText(txtProductPricesList.get(0));
		price = StringUtils.substring(price, 0, price.length() - 2);
		return price ;
	}

}
