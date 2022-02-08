import PageObjects.HomePage;
import PageObjects.HoversPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0017_UserIsAbleToSeeAdditionalInformationAfterHoverTheMouseOntoAvatar extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToSeeAdditionalInformationAfterHoverTheMouseOntoAvatarTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Hovers"));

        HoversPage hoversPage = new HoversPage(driver);
        hoversPage.verifyDefaultContent();
        hoversPage.hoverEachAvatar();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
