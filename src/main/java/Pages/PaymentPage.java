package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
    private final WebDriver driver;
    private JavascriptExecutor js;
    @FindBy(how = How.XPATH,using = "//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a/span")
    WebElement payByBankWire;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    private void scrollToView(WebElement element) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element );
        Thread.sleep(2000);
    }

    public SummaryPage clickOnPayByWire() throws InterruptedException {
        scrollToView(payByBankWire);
        payByBankWire.click();
        return new SummaryPage(driver);
    }


}
