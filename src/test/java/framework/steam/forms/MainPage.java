package framework.steam.forms;

import framework.webdriver.BaseEntity;
import framework.webdriver.BaseForm;
import org.openqa.selenium.By;
import framework.steam.menus.Menu;
import framework.steam.services.GeneralFunctions;
import framework.webdriver.browser.BrowserFactory;
import framework.webdriver.elements.Select;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by User on 14.06.2017.
 */
public class MainPage extends BaseForm {
    Menu menu;
    Select selGamesmenu;
    Select selAction;
    Select languageSelect;
    Select concretelanguageSelect;
    Properties locatorProperties;
    private static final String TEXT_LOCATORS_PROPERTIES = "%s_text.properties";
    String gamesMenuLocatorKey = "gamesMenuLocator";
    String actionLocatorKey = "actionLocator";
    String languageLocatorKey = "languageLocator";
    String russionOptionLocatorKey = "russionOptionLocator";



    public MainPage() {
        menu = new Menu();
    }

    public void moveToGamesMenu() throws UnsupportedEncodingException {
        BaseEntity.logger.info("Move to games Menu");
        BrowserFactory.waitImplicitly();
        BrowserFactory.waitPageToLoad();
        selGamesmenu = new Select(By.xpath(locatorProperties.getProperty(gamesMenuLocatorKey)));
        selGamesmenu.moveTo();
    }

    public void changeLanguage() throws UnsupportedEncodingException {
        BaseEntity.logger.info("Select a language depending on the settings");
        GeneralFunctions generalFunctions = new GeneralFunctions();
        String language = getProperties().getProperty("language");
        locatorProperties = getLocatorProperties();
        String locat = locatorProperties.getProperty(languageLocatorKey);
        languageSelect = new Select(By.xpath(locat));
        String lang = languageSelect.getText();

        if ((language.equals("ru") && !lang.equals("язык")) || (language.equals("en") && !lang.equals("language"))) {
            String filename = String.format(TEXT_LOCATORS_PROPERTIES, language);
            Properties textLocatorsProp = generalFunctions.readProperties(filename);
            String locator = new String(textLocatorsProp.getProperty("language").getBytes("ISO-8859-1"), "UTF-8");
            locator = String.format(locatorProperties.getProperty(russionOptionLocatorKey), locator);
            languageSelect.click();
            concretelanguageSelect = new Select(By.xpath(locator));
            concretelanguageSelect.click();
        }
    }

    public void clickOnActionSubmenu(String id) throws UnsupportedEncodingException {
        BaseEntity.logger.info("Click on action menu");
        String locator = new String(locatorProperties.getProperty(actionLocatorKey).getBytes("ISO-8859-1"), "UTF-8");
        selAction = new Select(By.xpath(String.format(locator, id)));
        selAction.click();
    }
}