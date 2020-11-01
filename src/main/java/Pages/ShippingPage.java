package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {
    private final WebDriver driver;
    private JavascriptExecutor js;
    private Actions action;
    @FindBy(how = How.LINK_TEXT,using = "Proceed to checkout")
    WebElement proceedToCheckOutTab;
    @FindBy(how = How.XPATH,using = "//*[@id=\"form\"]/p/button/span")
    WebElement proceedTab;
    @FindBy(how = How.XPATH,using = "//*[@id=\"form\"]/div/p[1]")//*[@id="form"]/div/p[1]
    WebElement proceedToTermOfService;
    @FindBy(how = How.XPATH,using = "//*[@id=\"cgv\"]")
    WebElement checkBox;

    public ShippingPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public PaymentPage clickOnShippingPageProceedToCheckOut() throws InterruptedException {
        scrollToView(proceedToTermOfService);
        tickTermsAndConditions();
        action.moveToElement(proceedTab);
        proceedTab .click();
        Thread.sleep(2000);
        return new PaymentPage(driver);
    }

    private void scrollToView(WebElement element) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element );
        Thread.sleep(2000);
        action = new Actions(driver);

    }

    private void tickTermsAndConditions(){
        checkBox.click();
    }
}
