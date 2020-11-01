package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div/p/strong")
    WebElement confirmationMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
    WebElement logOutTab;

    public OrderConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public OrderConfirmationPage confirmOrderMessage() throws InterruptedException {
        String expectedMessage = "Your order on My Store is complete.";
        String actualMessage = confirmationMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
        return this;
    }

    public AuthenticationPage logOut() throws InterruptedException {
        logOutTab.click();
        Thread.sleep(200);
        return new AuthenticationPage(driver);
    }


}
