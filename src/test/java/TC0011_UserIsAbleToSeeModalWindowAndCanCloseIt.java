import PageObjects.EntryAdPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0011_UserIsAbleToSeeModalWindowAndCanCloseIt extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToSeeModalWindowAndCanCloseItTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Entry Ad"));

        EntryAdPage entryAdPage = new EntryAdPage(driver);
        entryAdPage.verifyDefaultContentModalWindow();
        entryAdPage.clickCloseButton();
        entryAdPage.verifyDefaultContent();
        entryAdPage.clickOnHereLink();
        entryAdPage.verifyDefaultContentModalWindow();
        entryAdPage.clickCloseButton();
        entryAdPage.refreshPage();
        entryAdPage.verifyDefaultContentModalWindow();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
