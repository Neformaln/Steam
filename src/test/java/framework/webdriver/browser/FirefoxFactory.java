package framework.webdriver.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 15.06.2017.
 */
public class FirefoxFactory extends BrowserFactory {
    @Override
    public WebDriver getDriver() {
        return null;
    }

    private static final String driverName = "webdriver.gecko.driver";
    private static final String driverKeyLin = "driver_firefox_location_lin";
    private static final String driverKeyWin = "driver_firefox_location_win";
    private static FirefoxFactory instance = null;
    private static FirefoxDriver driver;

    public static synchronized WebDriver getInstance() {
        if (instance == null)
            instance = new FirefoxFactory();
        if(driver==null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private FirefoxFactory() {
        osHelper(driverName, driverKeyWin, driverKeyLin);
    }

}


