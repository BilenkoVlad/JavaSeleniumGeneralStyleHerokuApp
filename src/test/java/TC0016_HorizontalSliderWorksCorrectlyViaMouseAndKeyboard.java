import PageObjects.HomePage;
import PageObjects.HorizontalSliderPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0016_HorizontalSliderWorksCorrectlyViaMouseAndKeyboard extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void HorizontalSliderWorksCorrectlyViaMouseTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Horizontal Slider"));

        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver);
        horizontalSliderPage.verifyDefaultContent();
        horizontalSliderPage.moveSliderUpByMouse();
        horizontalSliderPage.moveSliderDownByMouse();
        refreshThePage();
        horizontalSliderPage.verifyDefaultContent();
    }

    @Test
    public void HorizontalSliderWorksCorrectlyViaKeyboardTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Horizontal Slider"));

        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver);
        horizontalSliderPage.verifyDefaultContent();
        horizontalSliderPage.moveSliderUpByKeyboard();
        horizontalSliderPage.moveSliderDownByKeyboard();
        refreshThePage();
        horizontalSliderPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
