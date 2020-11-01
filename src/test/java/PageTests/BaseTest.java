package PageTests;

import BaseSetup.BrowserSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public abstract class BaseTest {

    BrowserSetUp browserSetUp = new BrowserSetUp();
    public  static WebDriver driver;

    @BeforeTest
    @Parameters({"appType"})
    public void startBrowser(String appType) throws MalformedURLException {
        driver = browserSetUp.startBrowser(appType);
    }

    @AfterTest
    public void quitBrowser(){
        browserSetUp.closeBrowser();
    }

    public static WebDriver getDriver(){
        return driver;
    }

}
