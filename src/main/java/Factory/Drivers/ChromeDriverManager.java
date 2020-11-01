package Factory.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager implements DriverManager {

    private final String remoteUrl;
    WebDriver driver;

    public ChromeDriverManager(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver() throws MalformedURLException {
        URL url = new URL(remoteUrl);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);
        this.driver = driver;
    }
}
