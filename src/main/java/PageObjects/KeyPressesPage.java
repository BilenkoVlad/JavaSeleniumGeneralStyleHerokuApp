package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.*;

public class KeyPressesPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By inputField = By.xpath("//input[@id='target']");
    private final By result = By.xpath("//p[@id='result']");

    public KeyPressesPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement inputField() {
        return driver.findElement(inputField);
    }

    private WebElement result() {
        return driver.findElement(result);
    }

    public void pressKeysOnPage() {
        Actions actions = new Actions(driver);

        actions.sendKeys(Keys.ARROW_UP).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: UP");

        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: DOWN");

        actions.sendKeys(Keys.ARROW_LEFT).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: LEFT");

        actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: RIGHT");

        actions.sendKeys(Keys.ENTER).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: ENTER");

        actions.sendKeys(Keys.CONTROL).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: CONTROL");

        actions.sendKeys(Keys.SHIFT).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: SHIFT");

        actions.sendKeys(Keys.TAB).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: TAB");

        actions.sendKeys(Keys.ESCAPE).build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: ESCAPE");
    }

    public void pressCharsOnPage() {
        Actions actions = new Actions(driver);

        actions.sendKeys("a").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: A");

        actions.sendKeys("q").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: Q");

        actions.sendKeys("c").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: C");

        actions.sendKeys(",").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: COMMA");

        actions.sendKeys("/").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: SLASH");

        actions.sendKeys("[").build().perform();
        assertTrue(result().isDisplayed());
        assertEquals(result().getText(), "You entered: OPEN_BRACKET");
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Key Presses");
        assertEquals(bodyText().getText(), "Key presses are often used to interact with a website " +
                "(e.g., tab order, enter, escape, etc.). Press a key and see what you inputted.");
        assertTrue(inputField().isDisplayed() && inputField().isEnabled());
        assertFalse(result().isDisplayed());
        assertEquals(result().getText(), "");
    }

}
