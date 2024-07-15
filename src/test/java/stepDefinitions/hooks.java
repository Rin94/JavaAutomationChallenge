package stepDefinitions;
import io.cucumber.java.After;
import TestComponents.TestContextSetup;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;

public class hooks {

    TestContextSetup testContextSetup;
    public hooks(TestContextSetup testContextSetup){
        this.testContextSetup = testContextSetup;
    }
    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            File sourcePath = ((TakesScreenshot)testContextSetup.baseTest.driver).getScreenshotAs(OutputType.FILE);
            byte [] fileContent=FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }
    @After
    public void closeApplication(){
        testContextSetup.baseTest.tearDown();
    }
}
