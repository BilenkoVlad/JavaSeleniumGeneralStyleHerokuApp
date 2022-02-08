import PageObjects.HomePage;
import PageObjects.MultipleWindowsPage;
import PageObjects.NewWindowPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0022_UserIsAbleToOpenAndNavigateToTheNewWindow extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToOpenAndNavigateToTheNewWindowTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Multiple Windows"));

        MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage(driver);
        multipleWindowsPage.verifyDefaultContent();
        multipleWindowsPage.clickOnLink();

        NewWindowPage newWindowPage = new NewWindowPage(driver);
        newWindowPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
