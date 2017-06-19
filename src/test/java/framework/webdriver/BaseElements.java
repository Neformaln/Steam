package framework.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by User on 14.06.2017.
 */
public abstract class BaseElements extends BaseEntity {
    WebDriver driver;

    public BaseElements() {
    }

    public abstract void click();
    public abstract String getText();
    public abstract String getAttribute(String str);
    public abstract void moveTo();

    public List<WebElement> findElements(String by) {
        return getDriver().findElements(By.xpath(by));
    }

    public WebElement findElement(By locator) {
        return getDriver().findElement(locator);
    }
}

