import PageObjects.HomePage;
import PageObjects.StatusCodesPages;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0025_VerifyStatusCodes extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void VerifyStatusCodesTest() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Status Codes"));

        StatusCodesPages statusCodesPages = new StatusCodesPages(driver);
        statusCodesPages.verifyDefaultContent();
        statusCodesPages.clickOn200Code();
        navigateBackInPage();
        statusCodesPages.clickOn301Code();
        navigateBackInPage();
        statusCodesPages.clickOn404Code();
        navigateBackInPage();
        statusCodesPages.clickOn500Code();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
