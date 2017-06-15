package test.webdriver.elements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 14.06.2017.
 */
public class BaseEntity {

    private static WebDriver driver;

    public BaseEntity() {
    }

    public BaseEntity(WebDriver driver1) {
        driver = driver1;
    }

    public  WebDriver getDriver() {
        return driver;
    }

    public void assertTrue(boolean isTrue){
        Assert.assertTrue(isTrue);
    }
}




