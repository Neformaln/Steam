package test.steam.forms;

import org.openqa.selenium.WebDriver;
import test.steam.services.CommonFunctions;
import test.webdriver.elements.BaseEntity;
import test.webdriver.browser.BrowserFactory;

import java.util.Properties;

/**
 * Created by User on 14.06.2017.
 */
public class BaseForm extends BaseEntity {

    private static final String MAIN_PROPERTY_PATH = "brouser.properties";
    private static final String LOCATOR_PROPERTY_PATH = "templates.properties";
    private static Properties properties;
    private static Properties locatorProperties;
    private static WebDriver driver;


    public BaseForm() {

    }

    public BaseForm(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }

    public void maximaseWindow() {
        BrowserFactory.maximaseWindow();
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
        CommonFunctions commonFunctions = new CommonFunctions();
        properties = commonFunctions.readProperties(MAIN_PROPERTY_PATH);
        return properties;
    }

    public Properties initLocatorProperties() {
        CommonFunctions commonFunctions = new CommonFunctions();
        locatorProperties = commonFunctions.readProperties(LOCATOR_PROPERTY_PATH);
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
