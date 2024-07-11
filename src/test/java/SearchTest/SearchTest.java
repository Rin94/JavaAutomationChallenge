package SearchTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import utils.DataReader;
import webstore.pages.*;

public class SearchTest extends BaseTest{

	@Test(dataProvider = "getData")
	public void test_searching_product_and_verify_product_suggestion(HashMap<String, String>input) {
		List<String> suggestions = new ArrayList<>();
		suggestions.add(input.get("suggestion1"));
		suggestions.add(input.get("suggestion2"));
		suggestions.add(input.get("suggestion3"));
		CatalogPage catalogPage = homePage.searchProducts(input.get("search1"));
		boolean match =catalogPage.verifyProductIsProductCatalog(suggestions);
		Assert.assertTrue(match);
		String retrieveFirstMatch = catalogPage.retrieveFirstMatchName();
		String retrieveFirstPrice = catalogPage.retrieveFirstMatchDiscountPrice();
		ProductPage productPage = catalogPage.clickOnFirstMatch();
		productPage.checkProductTitle(retrieveFirstMatch);
		productPage.checkProductPrice(retrieveFirstPrice);
	}
	@Test(dataProvider = "getData")
	public void test_verify_search_with_filters(HashMap<String,String>input){
		CatalogPage catalogPage = homePage.searchProducts(input.get("search2"));
		catalogPage.filterBySize(input.get("tvSize"));
		catalogPage.filterByPrice(input.get("tvPrice"));
		catalogPage.filterByBrand(input.get("tvBrand"));
		Assert.assertTrue(catalogPage.verifyProductIsProductCatalog(input.get("tvSize")));
		Assert.assertTrue(catalogPage.priceInCatalog(Double.parseDouble(input.get("tvPrice"))));
		Assert.assertTrue(catalogPage.verifyProductIsProductCatalog(input.get("tvBrand")));
		boolean actualCounting =catalogPage.checkCountInResults(Integer.parseInt(input.get("expectedCount")));
		Assert.assertTrue(actualCounting);
	}
	@Test(dataProvider = "getData")
	public void test_verify_search_by_category(HashMap<String,String>input){
		CatalogPage catalogPage =homePage.searchByCategories(input.get("category"), input.get("subCategory"));
		catalogPage.clickViewMoreBrandsLink();
		catalogPage.filterByBrand(input.get("lotionBrand"));
		boolean match =catalogPage.verifyProductIsProductCatalog(input.get("lotionBrand"));
		Assert.assertTrue(match);
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>>  data = DataReader.getJsonDataToMap("//testData//testData.json");
		return new Object[][]{{data.get(0)}};
	}
}
