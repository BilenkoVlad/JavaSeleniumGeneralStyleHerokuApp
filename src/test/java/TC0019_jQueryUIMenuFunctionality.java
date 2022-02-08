import PageObjects.HomePage;
import PageObjects.JQueryUIMenuPage;
import PageObjects.jQueryPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0019_jQueryUIMenuFunctionality extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void jQueryUIMenuFunctionalityTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("JQuery UI Menus"));

        JQueryUIMenuPage jQueryUIMenuPage = new JQueryUIMenuPage(driver);
        jQueryUIMenuPage.verifyDefaultContent();
        jQueryUIMenuPage.clickOnJQueryLink();

        jQueryPage jQueryPage = new jQueryPage(driver);
        jQueryPage.verifyJQueryPage();

        navigateBackInPage();
        jQueryUIMenuPage.verifyDefaultContent();
        jQueryUIMenuPage.downloadAllFiles();
        jQueryUIMenuPage.clickOnMenuOption("Back to JQuery UI");
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
