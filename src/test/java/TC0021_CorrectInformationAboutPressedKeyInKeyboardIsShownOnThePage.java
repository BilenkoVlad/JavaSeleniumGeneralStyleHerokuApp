import PageObjects.HomePage;
import PageObjects.KeyPressesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0021_CorrectInformationAboutPressedKeyInKeyboardIsShownOnThePage extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void CorrectInformationAboutPressedKeyInKeyboardIsShownOnThePageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Key Presses"));

        KeyPressesPage keyPressesPage = new KeyPressesPage(driver);
        keyPressesPage.verifyDefaultContent();
        keyPressesPage.pressKeysOnPage();
        keyPressesPage.pressCharsOnPage();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
