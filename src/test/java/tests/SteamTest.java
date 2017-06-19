package tests;

import framework.webdriver.BaseForm;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import framework.steam.forms.*;
import framework.steam.menus.Menu;
import framework.webdriver.BaseEntity;
import framework.webdriver.browser.BrowserFactory;

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
    public void setUp() throws Exception {
        BaseForm baseForm = new BaseForm();
        Properties properties = baseForm.initProperties();
        baseForm.initLocatorProperties();
        String br = properties.getProperty("browser_type");
        driver = BrowserFactory.getMyDriver(br);
        String mainPage = properties.getProperty("main_page_url");
        baseForm = new BaseForm(driver);
        baseForm.maximiseWindow();
        baseForm.navigate(mainPage);
        baseEntity = new BaseEntity();
    }

    @Test
    public void startTest() throws UnsupportedEncodingException {
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
