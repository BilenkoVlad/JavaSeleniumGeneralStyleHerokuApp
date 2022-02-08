import PageObjects.BasicAuthPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TC002_UserIsAbleToAuthorizeIntoAppByBasicAuth extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void BasicAuthValidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Basic Auth"));

        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        basicAuthPage.basicAuthWithCredentials(true);
        basicAuthPage.verifyPageText();
    }

    @Test
    public void BasicAuthInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Basic Auth"));

        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        basicAuthPage.basicAuthWithCredentials(false);
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
