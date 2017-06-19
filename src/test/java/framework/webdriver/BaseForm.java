package framework.webdriver;

import org.openqa.selenium.WebDriver;
import framework.steam.services.GeneralFunctions;
import framework.webdriver.BaseEntity;
import framework.webdriver.browser.BrowserFactory;

import java.util.Properties;

/**
 * Created by User on 14.06.2017.
 */
public class BaseForm extends BaseEntity {
    private static final String MAIN_PROPERTY_PATH = "config.properties";
    private static final String LOCATOR_PROPERTY_PATH = "locators.properties";
    private static Properties properties;
    private static Properties locatorProperties;
    private static WebDriver driver;

    public BaseForm() {
    }

    public BaseForm(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void maximiseWindow() {
        BrowserFactory.maximiseWindow();
    }

    public void navigate(String url) {
        BrowserFactory.navigateUrl(url);
    }

    public void exit() {
        BrowserFactory.exit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Properties initProperties() {
        GeneralFunctions generalFunctions = new GeneralFunctions();
        properties = generalFunctions.readProperties(MAIN_PROPERTY_PATH);
        return properties;
    }

    public Properties initLocatorProperties() {
        GeneralFunctions generalFunctions = new GeneralFunctions();
        locatorProperties = generalFunctions.readProperties(LOCATOR_PROPERTY_PATH);
        return locatorProperties;
    }

    public void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static Properties getProperties() {
        return properties;
    }

    public Properties getLocatorProperties() {
        return locatorProperties;
    }
}
