import PageObjects.HomePage;
import PageObjects.NotificationMessagesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0026_VerifyCorrectNotificationMessages extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void VerifyCorrectNotificationMessagesTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Notification Messages"));

        NotificationMessagesPage notificationMessagesPage = new NotificationMessagesPage(driver);
        notificationMessagesPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
