package framework.webdriver.elements;


import framework.webdriver.BaseElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by User on 14.06.2017.
 */
public class Button extends BaseElements {
    WebElement button;

    public Button() {
    }

    public Button(By by) {
        this.button = findElement(by);
    }

    public Button(WebElement button) {
        this.button = button;
    }

    @Override
    public void click() {
        button.click();
    }

    public String getAttribute(String name) {
        return button.getAttribute(name);
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void moveTo() {
    }
}
