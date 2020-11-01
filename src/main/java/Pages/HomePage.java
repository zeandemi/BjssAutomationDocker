package Pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    private final WebDriver driver;
    private static Actions action;
    @FindBy(how = How.CLASS_NAME, using = "login")
    WebElement signIn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"layer_cart\"]/div[1]/div[1]/span")
    WebElement closeCartView;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b")
    WebElement cart;
    @FindBy(how = How.XPATH, using = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[2]/span")
    WebElement blouseQuickImage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img")
    WebElement blouseImage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[2]/span")
    WebElement printedDressQuickImage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img")
    WebElement printedDressImage;
    @FindBy(how = How.LINK_TEXT, using = "BEST SELLERS")
    WebElement bestSellerView;
    @FindBy(how = How.CLASS_NAME, using = "fancybox-iframe")
    WebElement iFrameElement;
    @FindBy(how = How.XPATH, using = "//*[@id=\"group_1\"]")
    WebElement sizeDropDown;
    @FindBy(how = How.XPATH, using = "//*[@id=\"add_to_cart\"]/button")
    WebElement addToBasket;
    @FindBy(how = How.XPATH, using = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span")
    WebElement continueShoppingElement;
    @FindBy(how = How.XPATH, using = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
    WebElement successfulMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[1]/div/div[2]/a")
    WebElement firstItemSizeAndColour;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[1]/div/span")
    WebElement firstItemAmount;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/div/div[2]/a")
    WebElement secondItemSizeAndColour;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/div/span")
    WebElement secondItemAmount;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[1]")
    WebElement totalAmount;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[1]/span[1]")
    WebElement shippingAmount;
    @FindBy(how = How.XPATH, using = "//*[@id=\"button_order_cart\"]/span")
    WebElement checkOut;
    String expectedFirstItem = "Black, M";
    String expectedSecondItem = "Orange, S";
    String expectedFirstItemAmount = "$27.00";
    String expectedSecondItemAmount = "$26.00";
    String expectedTotalAmountPlusShipping = "$55.00";
    String actualFirstItem;
    String actualSecondItem;
    String actualFirstItemAmount;
    String actualSecondItemAmount;
    String sAmount;
    String tAmount;
    Double expectedTotalOfItem;
    JavascriptExecutor js;
    AuthenticationPage authenticationPage;
    Select select;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        action = new Actions(driver);
    }

    public HomePage logIn(String username, String password) throws InterruptedException {
        authenticationPage = clickSignIn();
        authenticationPage.verifyAuthenticationPageTitle().signIn(username, password).verifyAccountPageTitle().clickOnReturnToHomeButton();
        return this;
    }

    public AccountPage logInAndViewPreviousItems(String username, String password) throws InterruptedException {
        authenticationPage = clickSignIn();
        authenticationPage.verifyAuthenticationPageTitle().signIn(username, password).verifyAccountPageTitle();
        return new AccountPage(driver);
    }

    private AuthenticationPage clickSignIn() {
        signIn.click();
        return new AuthenticationPage(driver);
    }

    public HomePage clickOnBlouseQuickView() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", bestSellerView);
        Thread.sleep(2000);
        action.moveToElement(blouseImage).perform();
        Thread.sleep(2000);
        action.moveToElement(blouseQuickImage).perform();
        Thread.sleep(2000);
        try {
            if (blouseQuickImage.isDisplayed() && blouseQuickImage.isEnabled()) {
                blouseQuickImage.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;

    }

    private void verifySuccessfulCartMessage() {
        String expectedMessage = "Product successfully added to your shopping cart";
        String actualMessage = successfulMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    public HomePage changeBlouseSize(String size) throws InterruptedException {
        Thread.sleep(2000);
        driver.switchTo().frame(iFrameElement);
        select = new Select(sizeDropDown);
        select.selectByVisibleText(size);
        addItemToBasket();
        return this;
    }

    public HomePage clickOnContinueShoppingTab() {
        continueShoppingElement.click();
        return this;
    }

    public void addItemToBasket() throws InterruptedException {
        action.moveToElement(addToBasket);
        addToBasket.click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        verifySuccessfulCartMessage();
    }

    public HomePage clickOnPrintedDressQuickView() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", bestSellerView);
        Thread.sleep(2000);
        action = new Actions(driver);
        action.moveToElement(printedDressImage).perform();
        Thread.sleep(2000);
        action.moveToElement(printedDressQuickImage).perform();
        Thread.sleep(2000);
        try {
            if (printedDressQuickImage.isDisplayed() && printedDressQuickImage.isEnabled()) {
                printedDressQuickImage.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(iFrameElement);
        addItemToBasket();
        return this;
    }

    public HomePage viewBasketAndConfirm() throws InterruptedException {
        closeCartViewMessage();
        viewBasket();
        confirmItems();
        return this;
    }

    private void confirmItems() throws InterruptedException {
        actualFirstItem = firstItemSizeAndColour.getText();
        actualSecondItem = secondItemSizeAndColour.getText();
        actualFirstItemAmount = firstItemAmount.getText();
        actualSecondItemAmount = secondItemAmount.getText();

        Thread.sleep(2000);
        Assert.assertEquals(expectedFirstItem, actualFirstItem);
        Assert.assertEquals(expectedSecondItem, actualSecondItem);
        Assert.assertEquals(expectedFirstItemAmount, actualFirstItemAmount);
        Assert.assertEquals(expectedSecondItemAmount, actualSecondItemAmount);
        totalOfItemsPlusShipping();
        totalOfItems();

    }

    private String stringUtil(String s) {
        StringBuilder sb = new StringBuilder(s);
        String s1 = sb.deleteCharAt(0).toString();
        return s1;
    }

    private void totalOfItemsPlusShipping() {
        tAmount = stringUtil(totalAmount.getText());
        sAmount = stringUtil(shippingAmount.getText());
        String aTotalAmount = stringUtil(expectedTotalAmountPlusShipping);

        double actualTotalIncludingShipping = Double.valueOf(tAmount);
        double expectedTotalAmount = Double.valueOf(aTotalAmount);
        double diff = 0.00;
        Assert.assertEquals(expectedTotalAmount, actualTotalIncludingShipping, diff);
    }

    private void totalOfItems() {
        String aFirstItemAmount = stringUtil(actualFirstItemAmount);
        String aSecondItemAmount = stringUtil(actualSecondItemAmount);
        String tAmount = stringUtil(totalAmount.getText());
        expectedTotalOfItem = Double.valueOf(aFirstItemAmount) + Double.valueOf(aSecondItemAmount);
        Double actualTotalOfItem = Double.valueOf(tAmount);
        Double difference = actualTotalOfItem - expectedTotalOfItem;
        Assert.assertEquals(expectedTotalOfItem, actualTotalOfItem, difference);
    }

    private void viewBasket() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-1500)", "");
        Thread.sleep(2000);
        action = new Actions(driver);
        action.moveToElement(cart).perform();
        Thread.sleep(2000);
    }

    private void closeCartViewMessage() throws InterruptedException {
        closeCartView.click();
        Thread.sleep(2000);
    }

    public ShoppingCartSummaryPage clickOnCheckOutTab() {
        checkOut.click();
        return new ShoppingCartSummaryPage(driver);
    }


}
