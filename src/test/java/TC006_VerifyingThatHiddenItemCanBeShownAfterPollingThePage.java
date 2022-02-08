import PageObjects.DisappearingElementsPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC006_VerifyingThatHiddenItemCanBeShownAfterPollingThePage extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void VerifyingThatHiddenItemCanBeShownAfterPollingThePageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Disappearing Elements"));

        DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage(driver);
        disappearingElementsPage.verifyDefaultContent();
        disappearingElementsPage.waitFotPresenceOfHiddenElement();
        disappearingElementsPage.waitForAbsenceOfHiddenElement();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
