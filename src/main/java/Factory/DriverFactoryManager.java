package Factory;

import Factory.Drivers.ChromeDriverManager;
import Factory.Drivers.DriverManager;
import Factory.Drivers.FireFoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class DriverFactoryManager {
    DriverManager driverFactory;
    private WebDriver driver;
    private String host;




    public DriverManager initDriver(String appDriver) throws MalformedURLException {
        if(System.getProperty("HUB_HOST") != null){
        String host = System.getProperty("HUB_HOST");
        this.host = host;
        }

        String remoteUrl = "http://" + host + ":4444/wd/hub";

        switch (appDriver){

            case "Chrome":
                driverFactory = new ChromeDriverManager(remoteUrl);
                driverFactory.setDriver();
                break;
            case "Firefox":
                driverFactory = new FireFoxDriverManager(remoteUrl);
                driverFactory.setDriver();
                break;
        }
        return driverFactory;
    }

    public WebDriver getDriver(){
        driver = driverFactory.getDriver();
        return driver;
    }

}
