package test.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.steam.forms.*;
import test.steam.menus.Menu;
import test.webdriver.elements.BaseEntity;
import test.webdriver.browser.BrowserFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

/**
 * Created by User on 15.06.2017.
 */
public class SteamTest {
    WebDriver driver;
    BaseEntity baseEntity;

    @BeforeTest
    public void setUp() {
        BaseForm basePage = new BaseForm();
        Properties properties = basePage.initProperties();
        basePage.initLocatorProperties();
        String br = properties.getProperty("brouser_type");
        driver = BrowserFactory.getMyDriver(br);
        String mainPage = properties.getProperty("main_page_url");
        basePage = new BaseForm(driver);
        basePage.maximaseWindow();
        basePage.navigate(mainPage);
        baseEntity = new BaseEntity();
    }

    @Test
    public void shouldWork() throws UnsupportedEncodingException {


        MainPage startPage = new MainPage();
        startPage.changeLanguage();
        startPage.moveToGamesMenu();
        startPage.clickOnActionSubmenu(Menu.ACTION);

        ActionPage actionPage = new ActionPage();
        actionPage.clickOnSpecials();
        List<String> list = actionPage.getDiscount();

        DescriptionPage descriptionPage = new DescriptionPage();
        descriptionPage.chooseAgeIfExist();


        baseEntity.assertTrue(descriptionPage.isSimilarPriceDiscount(list));
        descriptionPage.clickOnInstall();

        InstallPage installPage = new InstallPage();
        baseEntity.assertTrue(installPage.isFullDownload());


    }

    @AfterTest
    public void exit() {
        BaseForm basePage = new BaseForm(driver);
        basePage.exit();
    }
}
