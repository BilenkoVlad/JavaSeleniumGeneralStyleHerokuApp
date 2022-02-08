import PageObjects.DynamicLoadingPage;
import PageObjects.Example1HiddenElementsPage;
import PageObjects.Example2ElementRendered;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0010_VerifyThatElementsDynamicallyLoaded extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void VerifyThatElementsDynamicallyLoadedTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Dynamic Loading"));

        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.verifyDefaultContent();
        dynamicLoadingPage.clickOnFirstLink();

        Example1HiddenElementsPage example1HiddenElementsPage = new Example1HiddenElementsPage(driver);
        example1HiddenElementsPage.verifyDefaultContent();
        example1HiddenElementsPage.clickStartButton();
        navigateBackInPage();
        dynamicLoadingPage.clickOnSecondLink();

        Example2ElementRendered example2ElementRendered = new Example2ElementRendered(driver);
        example2ElementRendered.verifyDefaultContent();
        example2ElementRendered.clickStartButton();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
