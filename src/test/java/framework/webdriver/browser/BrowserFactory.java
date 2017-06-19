package framework.webdriver.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.steam.services.GeneralFunctions;

import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.time.zone.ZoneRulesProvider.refresh;

/**
 * Created by User on 14.06.2017.
 */
public abstract class BrowserFactory {
    private static final String MAIN_PROPERTY_PATH= "config.properties";
    public abstract WebDriver getDriver();
    private static WebDriver driver;
    private static Properties properties;
    static  Long started;


    public static WebDriver getMyDriver(final BrowserEnum type) throws Exception {
        switch (type) {
            case CHROME: {
                driver = ChromeFactory.getInstance();
                initProperties();
                return driver;
            }
            case FIREFOX: {
                driver = FirefoxFactory.getInstance();
                initProperties();
                return driver;
            }
            default:throw new Exception("Browser not found");
        }
    }

    public static WebDriver getMyDriver(final String type) throws Exception {
        for (BrowserEnum t : BrowserEnum.values()){
            if (t.toString().equalsIgnoreCase(type)){
                return getMyDriver(t);
            }
        }
        throw new NamingException("Wrong name");
    }

    public void osHelper(String driverName, String driverKeyWin, String driverKeyLin) {
        GeneralFunctions generalFunctions = new GeneralFunctions();
        String osType = System.getProperty("os.name").toLowerCase();
        if(osType.indexOf( "win" ) >= 0){
            System.setProperty(driverName, generalFunctions.readProperties("config.properties").getProperty(driverKeyWin));
        }
        else if(osType.indexOf( "nix") >=0 || osType.indexOf( "nux") >=0) {
            System.setProperty(driverName, generalFunctions.readProperties("config.properties").getProperty(driverKeyLin));
        }
    }

    public static void initProperties(){
        GeneralFunctions generalFunctions = new GeneralFunctions();
        properties = generalFunctions.readProperties(MAIN_PROPERTY_PATH);
    }

    public static void waitJavascript(){
        WebDriverWait wait1 = new WebDriverWait(driver, getTimeForLoadPage());
        try {
            wait1.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver d) {
                    if (!(d instanceof JavascriptExecutor)) {
                        return true;
                    }
                    Object result = ((JavascriptExecutor) d).executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                    if (result != null && result instanceof Boolean && (Boolean) result) {
                        long now = System.currentTimeMillis();
                        if (now - started > getTimeForLoadPage()) {
                            return true;
                        }else {
                            started = System.currentTimeMillis();
                        }
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            refresh();

        }
    }

    public static void waitWithIgnoring(){
        Wait<WebDriver> wait = new WebDriverWait(driver, getTimeForLoadElement())
                .ignoring(java.util.NoSuchElementException.class, ElementNotVisibleException.class);
    }

    public static void waitImplicitly(){
        driver.manage().timeouts().implicitlyWait(getTimeForLoadElement(), TimeUnit.SECONDS);
    }

    public static void waitElementExplicide(String locator){
        WebDriverWait wait = new WebDriverWait(driver, getTimeForLoadElement());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public static void waitElementsExplicide(String locator){
        WebDriverWait wait = new WebDriverWait(driver, getTimeForLoadElement());
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }


    public static void maximiseWindow(){
        driver.manage().window().maximize();
    }

    public static void navigateUrl(String url){
        driver.navigate().to(url);
    }

    public static Long getTimeForLoadPage(){
        return Long.parseLong(properties.getProperty("timeoutJs"));
    }

    public static Long getTimeForLoadElement(){
        return Long.parseLong(properties.getProperty("timeout"));
    }

    public static void waitPageToLoad(){
        try {
            Thread.sleep(getTimeForLoadPage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void exit(){
        driver.close();
    }
}
