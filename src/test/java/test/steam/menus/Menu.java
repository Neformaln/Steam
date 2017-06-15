package test.steam.menus;

import org.openqa.selenium.WebElement;
import test.steam.services.CommonFunctions;
import test.webdriver.elements.BaseEntity;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by User on 14.06.2017.
 */
public class Menu extends BaseEntity {
    WebElement menu;



    private static final String TEXT_LOCATORS_PATH = "%s_text.properties";
    private static final String MAIN_PROPERTIES_PATH = "brouser.properties";

    public static String GAMES;
    public static String ACTION;



    public Menu() {
        initItems();
    }


    public void initItems() {
        CommonFunctions commonFunctions = new CommonFunctions();
        Properties mainProp = commonFunctions.readProperties(MAIN_PROPERTIES_PATH);
        String lang = mainProp.getProperty("language");
        Properties textLocatorProp = commonFunctions.readProperties(String.format(TEXT_LOCATORS_PATH, lang));
        try {
            ACTION = new String(textLocatorProp.getProperty("gamesAction").getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
