package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.*;

public class CheckboxesPage {
    private final WebDriver driver;
    private final By headerPage = By.xpath("//div[@class='example']/h3");
    private final By checkboxes = By.xpath("//input[@type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement headerPageText() {
        return driver.findElement(headerPage);
    }

    public List<WebElement> checkboxesList() {
        return driver.findElements(checkboxes);
    }

    public void verifyDefaultContent() {
        assertEquals(headerPageText().getText(), "Checkboxes");
        assertFalse(checkboxesList().get(0).isSelected());
        assertTrue(checkboxesList().get(1).isSelected());
    }

    public void selectFirstCheckbox() {
        checkboxesList().get(0).click();
        assertTrue(checkboxesList().get(0).isSelected());
    }

    public void unselectCheckboxes() {
        assertTrue(checkboxesList().get(0).isSelected());
        assertTrue(checkboxesList().get(1).isSelected());
        for (int i = 0; i < checkboxesList().size(); i++) {
            checkboxesList().get(i).click();
            assertFalse(checkboxesList().get(i).isSelected());
        }
    }
}
