package framework.steam.menus;

import org.openqa.selenium.WebElement;
import framework.steam.services.GeneralFunctions;
import framework.webdriver.BaseEntity;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by User on 14.06.2017.
 */
public class Menu extends BaseEntity {
    WebElement menu;
    private static final String TEXT_LOCATORS_PATH = "%s_text.properties";
    private static final String MAIN_PROPERTIES_PATH = "config.properties";
    public static String GAMES;
    public static String ACTION;

    public Menu() {
        initItems();
    }

    public void initItems() {
        GeneralFunctions generalFunctions = new GeneralFunctions();
        Properties mainProp = generalFunctions.readProperties(MAIN_PROPERTIES_PATH);
        String lang = mainProp.getProperty("language");
        Properties textLocatorProp = generalFunctions.readProperties(String.format(TEXT_LOCATORS_PATH, lang));
        try {
            ACTION = new String(textLocatorProp.getProperty("gamesAction").getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
