package Pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SummaryPage {
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id=\"columns\"]/div[1]/span[2]")
    WebElement summaryPageMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"cart_navigation\"]/a")
    WebElement otherPaymentMethods;
    @FindBy(how = How.XPATH, using = "//*[@id=\"cart_navigation\"]/button/span")
    WebElement confirmOrder;
    private JavascriptExecutor js;

    public SummaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private SummaryPage verifySummaryPage() {
        String expectedMessage = "Bank-wire payment.";
        String actualMessage = summaryPageMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
        return this;
    }

    private void scrollToView(WebElement element) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(2000);
    }

    public OrderConfirmationPage clickOnIConfirmOrder() throws InterruptedException {
        verifySummaryPage().scrollToView(otherPaymentMethods);
        confirmOrder.click();
        return new OrderConfirmationPage(driver);

    }


}
