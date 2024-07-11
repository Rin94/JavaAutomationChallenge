package webstore.objectRepository;

public class HomePageObjects {
	
	public static final String XPATH_TXT_SEARCH_BAR = "(//input[@id='mainSearchbar'])[1]";

    public static final String XPATH_TXT_HAMBURGER_MENU = "//span//i[contains(@class,'hammenu')]" ;

    public static final String XPATH_TXT_CATEGORY_LINK = "(//li[contains(@class,'category')]//a[contains(@href,'REPLACE')])[2]";

    public static final String XPATH_TXT_SUB_CATEGORY_LINK = "//a[contains(text(),'REPLACE')]";
}
