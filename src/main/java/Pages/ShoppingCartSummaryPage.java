package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSummaryPage {

    private final WebDriver driver;
    @FindBy(how = How.LINK_TEXT,using = "Proceed to checkout")
    WebElement proceedToCheckOutTab;
    @FindBy (how = How.XPATH, using = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
    WebElement proceedTab;
    private JavascriptExecutor js;
    private Actions action;

    public ShoppingCartSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public AddressesPage clickOnProceedToCheckOutTab() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", proceedToCheckOutTab);
        Thread.sleep(2000);
        action = new Actions(driver);
        action.moveToElement(proceedTab);
        proceedTab.click();
        Thread.sleep(2000);
        return getAddressPAge();
    }

    private AddressesPage getAddressPAge(){
        return new AddressesPage(driver);
    }


}
