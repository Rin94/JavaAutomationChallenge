package webstore.objectRepository;

public class CatalogPageObjects {
	
	public static final String XPATH_TXT_PRODUCT_NAMES = "//h5[contains(@class, 'card-title a-card-description')]";
	public static final String XPATH_TXT_LINK_VIEW_MORE_SIZES = "//a[@id='Tamao']" ;
	public static final String XPATH_CHECKBOX_SIZES = "(//div[contains(@id,'Tamaocount')]//label[contains(text(),'REPLACE')]/parent::div//div)[1]";
	public static final String XPATH_CHECKBOX_BRAND = "(//div[contains(@id,'Marcascount')]//label[contains(text(),'REPLACE')]/parent::div//div)[1]";
	public static final String XPATH_RADIO_BTN_PRICE = "//div[contains(@class,'fiterl-prices')]//input[contains(@id,'sortPrice-REPLACE')]/parent::div";
	public static final String XPATH_TXT_VIEW_MORE_BRANDS =  "//a[@id='Marcas']";
	public static final String XPATH_TXT_PRODUCT_PRICES =  "//p[@class='a-card-discount']";

}
