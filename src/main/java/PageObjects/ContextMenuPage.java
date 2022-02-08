package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.*;

public class ContextMenuPage {
    private final WebDriver driver;
    private final By headerPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By contextBox = By.cssSelector("#hot-spot");
    private Actions actions;


    public ContextMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headerPageText() {
        return driver.findElement(headerPage);
    }

    private List<WebElement> bodyTextList() {
        return driver.findElements(bodyText);
    }

    private WebElement contextMenuBox() {
        return driver.findElement(contextBox);
    }

    public void verifyDefaultContent() {
        assertEquals(headerPageText().getText(), "Context Menu");
        assertEquals(bodyTextList().get(0).getText(), "Context menu items are custom " +
                "additions that appear in the right-click menu.");
        assertEquals(bodyTextList().get(1).getText(), "Right-click in the box below to see " +
                "one called 'the-internet'. When you click it, it will trigger a JavaScript alert.");
        assertTrue(contextMenuBox().isDisplayed());
    }

    public boolean alertIsPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void leftClickOnBox() {
        driver.findElement(contextBox).click();
        assertFalse(alertIsPresent());
    }

    public void rightClickOutBox() {
        actions = new Actions(driver);
        actions.contextClick(headerPageText()).perform();
        assertFalse(alertIsPresent());
    }

    public void rightClickOnBox() {
        actions = new Actions(driver);
        actions.contextClick(contextMenuBox()).perform();
        assertTrue(alertIsPresent());
        assertEquals(driver.switchTo().alert().getText(), "You selected a context menu");
        driver.switchTo().alert().accept();
        assertFalse(alertIsPresent());
    }
}
