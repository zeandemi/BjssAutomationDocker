package Utility;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class CaptureResult{


    private final WebDriver driver;
    String filepath = "C:\\Users\\oades\\proj\\BjssAutomation\\ScreenShots\\";

    public CaptureResult(WebDriver driver){
        this.driver = driver;

    }

    public void takeScreenshot(WebDriver driver, String methodName) throws Exception {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filepath+methodName+".png"));
    }
}
