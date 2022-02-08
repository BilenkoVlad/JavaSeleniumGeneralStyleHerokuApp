package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JQueryUIMenuPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='description']/p");
    private final By jQueryLink = By.xpath("//div[@class='description']/p/a");
    private final By menu = By.xpath("//div[@class='description']/ul[@id='menu']");
    private final By disabledOption = By.xpath("//div[@class='description']/ul[@id='menu']/li[@id='ui-id-1']");
    private final By enabledOption = By.xpath("//div[@class='description']/ul[@id='menu']/li[@id='ui-id-3']");
    private final By enableExpandedMenu = By.xpath("//*[@id='ui-id-3']/ul");
    private final By downloadExpandedMenu = By.xpath("//*[@id='ui-id-4']/ul");

    public JQueryUIMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> bodyText() {
        return driver.findElements(bodyText);
    }

    private WebElement jQueryLink() {
        return driver.findElement(jQueryLink);
    }

    private WebElement menu() {
        return driver.findElement(menu);
    }

    private WebElement disabledOption() {
        return driver.findElement(disabledOption);
    }

    private WebElement enabledOption() {
        return driver.findElement(enabledOption);
    }

    private WebElement enableExpandedMenu() {
        return driver.findElement(enableExpandedMenu);
    }

    private WebElement downloadExpandedMenu() {
        return driver.findElement(downloadExpandedMenu);
    }

    private WebElement options(String option) {
        By options = By.xpath(String.format("//a[text()='%s']", option));
        return driver.findElement(options);
    }

    private void waitForElement(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        assertTrue(element.isDisplayed());
    }

    private void moveToElement(WebElement elementToMove, WebElement elementToWait) {
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToMove).build().perform();
        waitForElement(elementToWait);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "JQueryUI - Menu");
        assertEquals(bodyText().get(0).getText().trim(), "JQuery UI Menus are a nice UI element from " +
                "a user perspective, but poses an interesting automation challenge since it requires mouse operations " +
                "and synchronization between them.");
        assertEquals(bodyText().get(1).getText().trim(), "Another 'fun' aspect is that the visibility of " +
                "elements is actually not in the html itself, but done magically by JQuery so you cannot trust exactly " +
                "what the html is telling you. A user cannot fire click events at certain UI elements, but you " +
                "might -- if you have a big enough hammer to hit it with.");
        assertTrue(menu().isDisplayed());
        assertTrue(disabledOption().isEnabled());
        assertTrue(enabledOption().isEnabled());

        moveToElement(enabledOption(), enableExpandedMenu());
        assertTrue(options("Downloads").isDisplayed() & options("Downloads").isEnabled());
        assertEquals(options("Downloads").getText(), "Downloads");
        assertTrue(options("Back to JQuery UI").isDisplayed() & options("Back to JQuery UI").isEnabled());
        assertEquals(options("Back to JQuery UI").getText(), "Back to JQuery UI");

        moveToElement(options("Downloads"), downloadExpandedMenu());
        assertTrue(options("PDF").isDisplayed() & options("PDF").isEnabled());
        assertEquals(options("PDF").getText(), "PDF");
        assertTrue(options("CSV").isDisplayed() & options("CSV").isEnabled());
        assertEquals(options("CSV").getText(), "CSV");
        assertTrue(options("Excel").isDisplayed() & options("Excel").isEnabled());
        assertEquals(options("Excel").getText(), "Excel");
    }

    public void clickOnJQueryLink() {
        jQueryLink().click();
    }

    public void downloadAllFiles() {
        moveToElement(enabledOption(), enableExpandedMenu());
        moveToElement(options("Downloads"), downloadExpandedMenu());
        options("PDF").click();
        options("CSV").click();
        options("Excel").click();
    }

    public void clickOnMenuOption(String element) {
        options(element).click();
        assertEquals(headersPage().getText(), "JQuery UI");
        assertEquals(bodyText().get(0).getText(), "JQuery UI is many things, but one thing specifically " +
                "that causes automation challenges is their set of Widgets");
    }
}
