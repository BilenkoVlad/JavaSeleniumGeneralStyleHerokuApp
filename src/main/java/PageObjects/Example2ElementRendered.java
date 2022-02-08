package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Example2ElementRendered {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/h4");
    private final By startButton = By.xpath("//div[@id='start']/button");
    private final By loader = By.xpath("//div[@id='loading']");
    private final By hiddenText = By.xpath("//div[@id='finish']/h4");

    public Example2ElementRendered(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement startButton() {
        return driver.findElement(startButton);
    }

    private WebElement loader() {
        return driver.findElement(loader);
    }

    private WebElement hiddenText() {
        return driver.findElement(hiddenText);
    }

    private void waitForHiddenText() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(hiddenText));
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Dynamically Loaded Page Elements");
        assertEquals(bodyText().getText(), "Example 2: Element rendered after the fact");
        assertTrue(startButton().isDisplayed() && startButton().isEnabled());
    }

    public void clickStartButton() {
        startButton().click();
        assertEquals(loader().getText(), "Loading...");
        assertTrue(loader().isDisplayed());
        waitForHiddenText();
        assertTrue(hiddenText().isDisplayed());
        assertEquals(hiddenText().getText(), "Hello World!");
    }
}
