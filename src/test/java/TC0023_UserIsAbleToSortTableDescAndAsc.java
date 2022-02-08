import PageObjects.HomePage;
import PageObjects.SortableDataTablesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC0023_UserIsAbleToSortTableDescAndAsc extends BaseClass {
    @BeforeTest
    public void browserInitialize() throws IOException {
        driver = DriverInitialization();
    }

    @Test
    public void UserIsAbleToSortTableDescAndAscTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getHomeUrl();
        clickOnLink(homePage.getLinkByName("Sortable Data Tables"));

        SortableDataTablesPage sortableDataTablesPage = new SortableDataTablesPage(driver);
        sortableDataTablesPage.verifyDefaultContent();
        sortableDataTablesPage.sortTableByEachHeaderAsc();
        sortableDataTablesPage.sortTableByEachHeaderDesc();
    }

    @AfterTest
    public void browserFinalization() {
        driver.quit();
        driver = null;
    }
}
