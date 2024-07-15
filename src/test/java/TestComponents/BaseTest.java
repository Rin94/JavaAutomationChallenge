package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.CurrentDateAndTime;
import utils.GlobalVariables;
import webstore.pages.HomePage;;

public class BaseTest {
	public WebDriver driver;
	public HomePage homePage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(GlobalVariables.SYSTEM_DIR_PATH+GlobalVariables.PROPERTIES_PATH);
		properties.load(fis);
		String browserName =System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
		String headless = System.getProperty("headless")!= null ? System.getProperty("headless") : properties.getProperty("headless");

		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-geolocation");
			if(headless.equals("yes")){
				options.addArguments("--headless=new");
			}
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//drivers//chromedriver");
			driver= new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariables.DELAY_VERYHIGH));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if(headless.equals("yess")){
				options.addArguments("-headless");

			}
			driver= new FirefoxDriver(options);
		}
		driver.manage().window().maximize();
		fis.close();
		return driver;
	}

	public  String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String fileNamePath = GlobalVariables.REPORTS_PATH+ testCaseName +"-"+CurrentDateAndTime.getCurrentTime()+ ".png";
		File file = new File(fileNamePath);
		FileUtils.copyFile(source, file );
		return ts.getScreenshotAs(OutputType.BASE64);
	}

	@BeforeMethod(alwaysRun = true)
	public HomePage lauchApplication() throws IOException {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		homePage.goTo();
		return new HomePage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
		//driver.quit();
	}
}
