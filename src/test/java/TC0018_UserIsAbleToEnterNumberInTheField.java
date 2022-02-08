import PageObjects.HomePage;
import PageObjects.InputsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0018_UserIsAbleToEnterNumberInTheField extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToEnterNumberInTheFieldViaKeyboard() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Inputs"));

        InputsPage inputsPage = new InputsPage(driver);
        inputsPage.verifyDefaultContent();
        inputsPage.enterChars();
        inputsPage.enterCharsViaArrows();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
