package Pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AccountPage {

    private final WebDriver driver;
    private JavascriptExecutor js;
    private Actions action;
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/h1")
    WebElement myAccountPageElement;
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span")
    WebElement orderHistoryTab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"columns\"]/div[1]/a/i")
    WebElement homeElement;
    @FindBy(how = How.XPATH, using = "//*[@id=\"order-list\"]/tbody/tr[1]/td[2]")
    WebElement orderedDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"order-list\"]/tbody/tr[1]/td[7]/a[1]/span")
    WebElement orderRef;
    @FindBy(how = How.XPATH, using = "//*[@id=\"sendOrderMessage\"]/p[3]/textarea")
    WebElement addMessageTextBox;
    @FindBy(how = How.XPATH, using = "//*[@id=\"sendOrderMessage\"]/div/button/span")
    WebElement sendTab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"block-order-detail\"]/div[5]/table/tbody/tr/td[2]")
    WebElement savedMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
    WebElement logOutTab;
    Date today;
    Date date;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HomePage clickOnReturnToHomeButton() {
        homeElement.click();
        return new HomePage(driver);
    }

    public AccountPage verifyAccountPageTitle() throws InterruptedException {
        Thread.sleep(2000);
        String actualMessage = myAccountPageElement.getText();
        String expectedMessage = "MY ACCOUNT";
        Assert.assertEquals(actualMessage, expectedMessage);
        return new AccountPage(driver);
    }

    public AccountPage clickOrderItemHistory() {
        orderHistoryTab.click();
        return this;
    }

    public AccountPage verifyOrderDate() throws InterruptedException {
        Thread.sleep(2000);
        today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = String.valueOf(dateFormat.format(today));
        String orderDate = orderedDate.getText();
        Assert.assertEquals(currentDate, orderDate);
        return this;
    }

    public AccountPage verifyOrderDateFailScenario() throws InterruptedException {
        Thread.sleep(2000);
        today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = String.valueOf(dateFormat.format(today));
        String orderDate = orderedDate.getText();
        Assert.assertEquals(currentDate, orderDate);
        return this;
    }

    public AccountPage addComment() throws InterruptedException {
        String message = "I love this item";
        orderRef.click();
        Thread.sleep(2000);
        scrollToView(addMessageTextBox);
        Thread.sleep(1000);
        addMessageTextBox.sendKeys(message);
        sendTab.click();
        Thread.sleep(2000);
        Assert.assertEquals(savedMessage.getText(), message);
        return this;
    }

    private void scrollToView(WebElement element) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(2000);
    }

    public AuthenticationPage logOut() {
        logOutTab.click();
        return new AuthenticationPage(driver);
    }


}
