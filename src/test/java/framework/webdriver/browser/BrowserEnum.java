package framework.webdriver.browser;

/**
 * Created by User on 17.06.2017.
 */
public enum BrowserEnum {

    CHROME("Chrome"),
    FIREFOX("Firefox");
    public String value;

    BrowserEnum(final String values){
        value = values;
    }

    public String toString(){
        return value;
    }
}
