package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class DynamicControlsPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h4");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By removeButton = By.xpath("//button[text()='Remove']");
    private final By addButton = By.xpath("//button[text()='Add']");
    private final By message = By.xpath("//p[@id='message']");
    private final By inputLoading = By.xpath("//form[@id='input-example']//div[@id='loading']");
    private final By checkboxLoading = By.xpath("//form[@id='checkbox-example']//div[@id='loading']");
    private final By enableButton = By.xpath("//button[text()='Enable']");
    private final By disableButton = By.xpath("//button[text()='Disable']");
    private final By checkbox = By.xpath("//input[@type='checkbox']");
    private final By textField = By.xpath("//input[@type='text']");
    private final List<String> expectedHeaders = Arrays.asList("Dynamic Controls", "Remove/add", "Enable/disable");

    public DynamicControlsPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> headersPage() {
        return driver.findElements(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement removeButton() {
        return driver.findElement(removeButton);
    }

    private WebElement enableButton() {
        return driver.findElement(enableButton);
    }

    private WebElement disableButton() {
        return driver.findElement(disableButton);
    }

    private WebElement checkbox() {
        return driver.findElement(checkbox);
    }

    private WebElement textField() {
        return driver.findElement(textField);
    }

    private WebElement addButton() {
        return driver.findElement(addButton);
    }

    private WebElement message() {
        return driver.findElement(message);
    }

    private void waitForMessage() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(message));
    }

    private WebElement inputLoading() {
        return driver.findElement(inputLoading);
    }

    private List<WebElement> checkboxLoading() {
        return driver.findElements(checkboxLoading);
    }

    private boolean isElementPresent(By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    public void verifyDefaultContent() {
        for (int i = 0; i < headersPage().size(); i++) {
            assertEquals(headersPage().get(i).getText(), expectedHeaders.get(i));
        }
        assertEquals(bodyText().getText(), "This example demonstrates when elements " +
                "(e.g., checkbox, input field, etc.) are changed asynchronously.");
        assertFalse(checkbox().isSelected());
        assertTrue(removeButton().isDisplayed() && removeButton().isEnabled());
        assertFalse(textField().isEnabled());
        assertTrue(enableButton().isDisplayed() && enableButton().isEnabled());
    }

    public void selectCheckbox() {
        checkbox().click();
        assertTrue(checkbox().isSelected());
    }

    public void clickOnRemoveButton() {
        removeButton().click();
        assertTrue(checkboxLoading().get(0).isDisplayed());
        waitForMessage();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText(), "It's gone!");
        assertFalse(isElementPresent(checkbox));
        assertTrue(addButton().isDisplayed() && addButton().isEnabled());
    }

    public void clickOnAddButton() {
        addButton().click();
        assertTrue(checkboxLoading().get(0).isDisplayed());
        waitForMessage();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText(), "It's back!");
        assertTrue(checkbox().isDisplayed());
        assertFalse(checkbox().isSelected());
    }

    public void clickOnEnableButton() {
        enableButton().click();
        assertTrue(inputLoading().isDisplayed());
        waitForMessage();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText(), "It's enabled!");
        assertTrue(textField().isEnabled());
        assertTrue(disableButton().isEnabled() && disableButton().isDisplayed());
    }

    public void enterTextIntoField(String text) {
        textField().sendKeys(text);
        assertEquals(textField().getAttribute("value"), text);
    }

    public void clickOnDisableButton() {
        enterTextIntoField("Test text is automatically added");
        disableButton().click();
        assertTrue(inputLoading().isDisplayed());
        waitForMessage();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText(), "It's disabled!");
        assertFalse(textField().isEnabled());
        assertTrue(enableButton().isEnabled() && enableButton().isDisplayed());
        assertEquals(textField().getAttribute("value"), "Test text is automatically added");
    }

}
