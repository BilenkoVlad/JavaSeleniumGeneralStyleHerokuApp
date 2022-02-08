package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class DropdownPage {
    private final WebDriver driver;
    private final By headerPage = By.xpath("//div[@class='example']/h3");
    private final By dropdown = By.xpath("//select[@id='dropdown']");
    private final By dropdownOptions = By.xpath("//select[@id='dropdown']//option");
    private final By selectedOption = By.xpath("//select[@id='dropdown']//option[@selected]");
    private final List<String> expectedOptions = Arrays.asList("Please select an option", "Option 1", "Option 2");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headerPage() {
        return driver.findElement(headerPage);
    }

    private WebElement dropdown() {
        return driver.findElement(dropdown);
    }

    private List<WebElement> dropdownOptions() {
        return driver.findElements(dropdownOptions);
    }

    private WebElement selectedOption() {
        return driver.findElement(selectedOption);
    }

    public void verifyDefaultContent() {
        assertEquals(headerPage().getText(), "Dropdown List");
        assertTrue(dropdown().isDisplayed());
        assertTrue(dropdown().isEnabled());
        assertEquals(dropdownOptions().get(0).getAttribute("selected"), "true");
        for (int i = 0; i < dropdownOptions().size(); i++) {
            assertEquals(dropdownOptions().get(i).getText(), expectedOptions.get(i));
        }
    }

    public void selectOptionFromDropDown(String value) {
        Select select = new Select(dropdown());
        select.selectByVisibleText(value);
        assertEquals(selectedOption().getText(), value);
        assertTrue(selectedOption().isSelected());
        assertFalse(dropdownOptions().get(0).isEnabled());
    }
}
