import PageObjects.ContextMenuPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TC005_UserShouldSeeAlertOnThePageAfterRightClickingOnSpecificArea extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void UserShouldSeeAlertOnThePageAfterRightClickingOnSpecificAreaTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Context Menu"));

        ContextMenuPage contextMenuPage = new ContextMenuPage(driver);
        contextMenuPage.verifyDefaultContent();
        contextMenuPage.leftClickOnBox();
        contextMenuPage.rightClickOutBox();
        contextMenuPage.rightClickOnBox();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
