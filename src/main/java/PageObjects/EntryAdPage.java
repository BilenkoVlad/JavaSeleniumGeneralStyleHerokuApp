package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EntryAdPage {
    private final WebDriver driver;
    private final By modalWindow = By.xpath("//div[@class='modal']");
    private final By modalWindowTitle = By.xpath("//div[@class='modal-title']/h3");
    private final By modalWindowText = By.xpath("//div[@class='modal-body']/p");
    private final By modalWindowClose = By.xpath("//div[@class='modal-footer']/p");
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By clickHereLink = By.xpath("//a[@id='restart-ad']");
    private final List<String> text = Arrays.asList("Displays an ad on page load.", "If closed, it will not appear " +
            "on subsequent page loads.", "To re-enable it, click here.");

    public EntryAdPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement modalWindow() {
        return driver.findElement(modalWindow);
    }

    private WebElement modalWindowTitle() {
        return driver.findElement(modalWindowTitle);
    }

    private WebElement modalWindowText() {
        return driver.findElement(modalWindowText);
    }

    private WebElement modalWindowClose() {
        return driver.findElement(modalWindowClose);
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> bodyText() {
        return driver.findElements(bodyText);
    }

    private WebElement clickHereLink() {
        return driver.findElement(clickHereLink);
    }

    private boolean waitForModalWindow() {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
            return true;
        } catch (TimeoutException timeoutException) {
            System.out.println("No modal window is shown");
            return false;
        }
    }

    public void verifyDefaultContentModalWindow() {
        waitForModalWindow();
        assertTrue(modalWindow().isDisplayed());
        assertEquals(modalWindowTitle().getText(), "This is a modal window".toUpperCase());
        assertEquals(modalWindowText().getText(), "It's commonly used to encourage a user to take an action " +
                "(e.g., give their e-mail address to sign up for something or disable their ad blocker).");
        assertEquals(modalWindowClose().getText(), "Close");
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Entry Ad");
        for (int i = 0; i < bodyText().size(); i++) {
            assertEquals(bodyText().get(i).getText(), text.get(i));
        }
        assertEquals(clickHereLink().getTagName(), "a");
        assertTrue(clickHereLink().isDisplayed() && clickHereLink().isEnabled());
    }

    public void clickCloseButton() {
        modalWindowClose().click();
    }

    public void clickOnHereLink() {
        while (!waitForModalWindow()) {
            clickHereLink().click();
        }
        verifyDefaultContentModalWindow();
    }

    public void refreshPage() {
        while (!waitForModalWindow()) {
            driver.navigate().refresh();
        }
        verifyDefaultContentModalWindow();
    }
}
