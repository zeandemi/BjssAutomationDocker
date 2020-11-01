package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddressesPage {

    private final WebDriver driver;
    private JavascriptExecutor js;
    private Actions action;
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/form/p/button/span")
    WebElement proceedTab;

    public AddressesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ShippingPage clickOnAddressPageProceedToCheckOut() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", proceedTab);
        Thread.sleep(2000);
        action = new Actions(driver);
        action.moveToElement(proceedTab);
        proceedTab.click();
        Thread.sleep(2000);
        return getAddressPage();
    }

    private ShippingPage getAddressPage(){
        return new ShippingPage(driver);
    }


}
