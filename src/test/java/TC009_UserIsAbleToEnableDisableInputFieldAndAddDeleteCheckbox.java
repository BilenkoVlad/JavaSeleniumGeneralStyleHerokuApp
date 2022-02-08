import PageObjects.DynamicControlsPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC009_UserIsAbleToEnableDisableInputFieldAndAddDeleteCheckbox extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToEnableDisableInputFieldAndAddDeleteCheckboxTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Dynamic Controls"));

        DynamicControlsPage dynamicControlsPage = new DynamicControlsPage(driver);
        dynamicControlsPage.verifyDefaultContent();
        dynamicControlsPage.selectCheckbox();
        dynamicControlsPage.clickOnRemoveButton();
        dynamicControlsPage.clickOnAddButton();
        dynamicControlsPage.clickOnEnableButton();
        dynamicControlsPage.clickOnDisableButton();
        refreshThePage();
        dynamicControlsPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
