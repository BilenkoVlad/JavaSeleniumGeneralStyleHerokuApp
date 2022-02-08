package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SortableDataTablesPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By exampleTitle = By.xpath("//div[@class='example']/h4");
    private final By tableOne = By.xpath("//table[@id='table1']");
    private final By tableOneHeaders = By.xpath("//table[@id='table1']//th");
    private final By tableOneRecords = By.xpath("//table[@id='table1']//td");
    private final By tableTwo = By.xpath("//table[@id='table2']");
    private final By tableTwoHeaders = By.xpath("//table[@id='table2']//th");
    private final By tableTwoRecords = By.xpath("//table[@id='table2']//td");

    public SortableDataTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> bodyText() {
        return driver.findElements(bodyText);
    }

    private List<WebElement> exampleTitle() {
        return driver.findElements(exampleTitle);
    }

    private WebElement tableOne() {
        return driver.findElement(tableOne);
    }

    private List<WebElement> tableOneHeaders() {
        return driver.findElements(tableOneHeaders);
    }

    private List<WebElement> tableOneRecords() {
        return driver.findElements(tableOneRecords);
    }

    private WebElement tableTwo() {
        return driver.findElement(tableTwo);
    }

    private List<WebElement> tableTwoHeaders() {
        return driver.findElements(tableTwoHeaders);
    }

    private List<WebElement> tableTwoRecords() {
        return driver.findElements(tableTwoRecords);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Data Tables");
        assertEquals(bodyText().get(0).getText(), "Often times when you see a table it contains data which " +
                "is sortable -- sometimes with actions that can be taken within each row (e.g. edit, delete). And it " +
                "can be challenging to automate interaction with sets of data in a table depending on how it is constructed.");
        for (int i = 0; i < exampleTitle().size(); i++) {
            assertEquals(exampleTitle().get(i).getText(), String.format("Example %s", i + 1));
        }
        assertEquals(bodyText().get(1).getText(), "No Class or ID attributes to signify groupings of rows and columns");
        assertEquals(bodyText().get(2).getText(), "Class and ID attributes to signify groupings of rows and columns");
        assertTrue(tableOne().isDisplayed() & tableTwo().isDisplayed());
        assertEquals(tableOneRecords().size(), tableTwoRecords().size());
        for (int i = 0; i < tableOneHeaders().size(); i++) {
            assertEquals(tableOneHeaders().get(i).getText(), tableTwoHeaders().get(i).getText());
        }
        for (int i = 0; i < tableOneRecords().size(); i++) {
            assertEquals(tableOneRecords().get(i).getText(), tableTwoRecords().get(i).getText());
        }
    }

    public void sortTableByEachHeaderAsc() {
        for (int i = 0; i < tableOneHeaders().size(); i++) {
            tableOneHeaders().get(i).click();
            tableTwoHeaders().get(i).click();
            for (int j = 0; j < tableOneRecords().size(); j++) {
                assertEquals(tableOneRecords().get(j).getText(), tableTwoRecords().get(j).getText());
            }
        }
    }

    public void sortTableByEachHeaderDesc() {
        for (int i = 0; i < tableOneHeaders().size(); i++) {
            tableOneHeaders().get(i).click();
            tableTwoHeaders().get(i).click();
            for (int j = 0; j < tableOneRecords().size(); j++) {
                assertEquals(tableOneRecords().get(j).getText(), tableTwoRecords().get(j).getText());
            }
        }
    }
}
