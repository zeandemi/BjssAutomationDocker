package PageTests;

import Pages.HomePage;
import Utility.CaptureResult;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.ParseException;

public class HomePageTest extends BaseTest {
    WebDriver driver;
    HomePage homePage;

    @Test(priority = 1)
    @Parameters({"username", "password", "size"})
    public void purchase2Items(String username, String password, String size) throws InterruptedException {
        driver = getDriver();
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getTitle(), "My Store");
        homePage.logIn(username, password).clickOnBlouseQuickView().changeBlouseSize(size).clickOnContinueShoppingTab()
                .clickOnPrintedDressQuickView().viewBasketAndConfirm().clickOnCheckOutTab()
                .clickOnProceedToCheckOutTab().clickOnAddressPageProceedToCheckOut().clickOnShippingPageProceedToCheckOut()
                .clickOnPayByWire().clickOnIConfirmOrder().confirmOrderMessage().logOut();
    }

    @Test(priority = 2)
    @Parameters({"username", "password"})
    public void reviewPreviousItemAndAddMessage(String username, String password) throws InterruptedException, ParseException {
        driver = getDriver();
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getTitle(), "Login - My Store");
        homePage.logInAndViewPreviousItems(username, password).clickOrderItemHistory().verifyOrderDate().addComment().logOut();
    }

    @Test
    @Parameters({"username", "password"})
    public void reviewPreviousImageAndGetFailAssert(String username, String password) throws InterruptedException {
        driver = getDriver();
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getTitle(), "My Store");
        homePage.logInAndViewPreviousItems(username, password).clickOrderItemHistory().verifyOrderDateFailScenario().logOut();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult iTestResult) throws Exception {
        if(iTestResult.getStatus()==ITestResult.FAILURE){
            CaptureResult captureResult = new CaptureResult(driver);
            captureResult.takeScreenshot(driver,iTestResult.getTestName());
        }


    }


}
