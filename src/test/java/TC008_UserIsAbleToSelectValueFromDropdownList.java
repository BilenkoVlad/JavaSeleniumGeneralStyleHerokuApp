import PageObjects.DropdownPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC008_UserIsAbleToSelectValueFromDropdownList extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToSelectValueFromDropdownListTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Dropdown"));

        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.verifyDefaultContent();
        dropdownPage.selectOptionFromDropDown("Option 1");
        dropdownPage.selectOptionFromDropDown("Option 2");
        refreshThePage();
        dropdownPage.verifyDefaultContent();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
