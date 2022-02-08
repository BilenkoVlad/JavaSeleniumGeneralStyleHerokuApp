import PageObjects.HomePage;
import PageObjects.JavaScriptAlertsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0020_UserIsAbleToWorkWithAlerts extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToWorkWithAlertsTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("JavaScript Alerts"));

        JavaScriptAlertsPage jsAlertsPage = new JavaScriptAlertsPage(driver);
        jsAlertsPage.verifyDefaultContent();
        jsAlertsPage.clickOnJSAlert();

        jsAlertsPage.clickOnJSConfirm("OK");
        jsAlertsPage.clickOnJSConfirm("Cancel");

        jsAlertsPage.clickOnJSPromptWithText("OK", "Test text with !@#$%^&*()");

        jsAlertsPage.clickOnJSPromptWithText("Cancel", "Test text with !@#$%^&*()");

        jsAlertsPage.clickOnJSPromptWithoutText("OK");
        jsAlertsPage.clickOnJSPromptWithoutText("Cancel");
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
