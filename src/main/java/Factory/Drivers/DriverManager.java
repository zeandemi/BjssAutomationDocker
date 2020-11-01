package Factory.Drivers;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface DriverManager {

    WebDriver getDriver();
    void setDriver() throws MalformedURLException;
}
