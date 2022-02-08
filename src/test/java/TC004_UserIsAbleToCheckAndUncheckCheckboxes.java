import PageObjects.CheckboxesPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TC004_UserIsAbleToCheckAndUncheckCheckboxes extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void UserIsAbleToCheckAndUncheckCheckboxesTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Checkboxes"));

        CheckboxesPage checkboxesPage = new CheckboxesPage(driver);
        checkboxesPage.verifyDefaultContent();
        checkboxesPage.selectFirstCheckbox();
        checkboxesPage.unselectCheckboxes();
        checkboxesPage.selectFirstCheckbox();
        refreshThePage();
        checkboxesPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
