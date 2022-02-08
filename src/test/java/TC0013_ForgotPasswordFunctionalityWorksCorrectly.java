import PageObjects.ForgotPasswordPage;
import PageObjects.HomePage;
import PageObjects.MailinatorPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0013_ForgotPasswordFunctionalityWorksCorrectly extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void ForgotPasswordFunctionalityWorksCorrectlyTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Forgot Password"));

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.verifyDefaultContent();
        forgotPasswordPage.enterEmailForRestorePassword();

        MailinatorPage mailinatorPage = new MailinatorPage(driver);
        mailinatorPage.goToEmailSite();
        mailinatorPage.enterEmail(forgotPasswordPage.email);
        mailinatorPage.clickOnReceivedEmail();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
