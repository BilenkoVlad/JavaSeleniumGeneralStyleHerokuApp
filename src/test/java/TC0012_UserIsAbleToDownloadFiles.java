import PageObjects.FileDownloadPage;
import PageObjects.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0012_UserIsAbleToDownloadFiles extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToDownloadFilesTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("File Download"));

        FileDownloadPage fileDownloadPage = new FileDownloadPage(driver);
        fileDownloadPage.verifyDefaultContent();
        fileDownloadPage.downloadFiles();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
