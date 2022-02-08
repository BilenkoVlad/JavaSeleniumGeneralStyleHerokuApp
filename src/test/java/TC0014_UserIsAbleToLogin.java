import PageObjects.FormAuthenticationPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0014_UserIsAbleToLogin extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Form Authentication"));

        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);
        formAuthenticationPage.verifyDefaultContent();
        formAuthenticationPage.enterInvalidCredentials();
        formAuthenticationPage.enterValidCredentials();
        formAuthenticationPage.clickLogoutButton();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
