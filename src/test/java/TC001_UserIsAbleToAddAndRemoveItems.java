import PageObjects.AddRemoveElementsPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC001_UserIsAbleToAddAndRemoveItems extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void UserIsAbleToAddAndRemoveItemsTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Add/Remove Elements"));

        AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage(driver);
        addRemoveElementsPage.addButtonClick();
        addRemoveElementsPage.deleteButtonIsDisplayed();
        addRemoveElementsPage.deleteButtonClick();
        addRemoveElementsPage.addButtonClick10Times();
        addRemoveElementsPage.deleteButtonClick10Times();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
