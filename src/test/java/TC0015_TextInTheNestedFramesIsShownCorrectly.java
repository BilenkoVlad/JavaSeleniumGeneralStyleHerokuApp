import PageObjects.FramesPage;
import PageObjects.HomePage;
import PageObjects.NestedFramesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0015_TextInTheNestedFramesIsShownCorrectly extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void TextInTheNestedFramesIsShownCorrectlyTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Frames"));

        FramesPage framesPage = new FramesPage(driver);
        framesPage.verifyDefaultContent();
        framesPage.clickNestedFramesLink();

        NestedFramesPage nestedFramesPage = new NestedFramesPage(driver);
        nestedFramesPage.checkNames();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
