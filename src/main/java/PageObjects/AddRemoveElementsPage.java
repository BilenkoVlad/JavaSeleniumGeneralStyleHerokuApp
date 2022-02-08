package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddRemoveElementsPage {
    private final WebDriver driver;
    private final By addButton = By.xpath("//button[@onclick='addElement()']");
    private final By deleteButtons = By.xpath("//button[@onclick='deleteElement()']");

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addButtonClick() {
        assertTrue(driver.findElement(addButton).isEnabled());
        driver.findElement(addButton).click();
    }

    public void addButtonClick10Times() {
        assertTrue(driver.findElement(addButton).isEnabled());
        for (int i = 0; i < 10; i++) {
            driver.findElement(addButton).click();
            assertTrue(deleteButtonsList().get(i).isDisplayed());
        }
        assertEquals(deleteButtonsList().size(), 10);
    }

    public void deleteButtonClick() {
        driver.findElement(deleteButtons).click();
    }

    public void deleteButtonClick10Times() {
        for (int i = deleteButtonsList().size() - 1; i >= 0; i--) {
            driver.findElements(deleteButtons).get(i).click();
            assertEquals(deleteButtonsList().size(), i);
        }
        deleteButtonsList();
        assertEquals(deleteButtonsList().size(), 0);
    }

    public void deleteButtonIsDisplayed() {
        assertTrue(driver.findElement(deleteButtons).isDisplayed());
    }

    public List<WebElement> deleteButtonsList() {
        return driver.findElements(deleteButtons);
    }
}
