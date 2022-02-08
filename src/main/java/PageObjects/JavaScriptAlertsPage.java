package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JavaScriptAlertsPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By resultText = By.xpath("//div[@class='example']/h4");
    private final By resultMessage = By.xpath("//p[@id='result']");
    private final By jsButtons = By.xpath("//button");
    private final By jsAlert = By.xpath("//button[@onclick='jsAlert()']");
    private final By jsConfirm = By.xpath("//button[@onclick='jsConfirm()']");
    private final By jsPrompt = By.xpath("//button[@onclick='jsPrompt()']");
    private final List<String> jsNames = Arrays.asList("Click for JS Alert", "Click for JS Confirm", "Click for JS Prompt");

    public JavaScriptAlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement resultText() {
        return driver.findElement(resultText);
    }

    private List<WebElement> jsButtons() {
        return driver.findElements(jsButtons);
    }

    private WebElement resultMessage() {
        return driver.findElement(resultMessage);
    }

    private WebElement jsAlert() {
        return driver.findElement(jsAlert);
    }

    private WebElement jsConfirm() {
        return driver.findElement(jsConfirm);
    }

    private WebElement jsPrompt() {
        return driver.findElement(jsPrompt);
    }

    private void waitForAlert() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.alertIsPresent());
    }

    private String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "JavaScript Alerts");
        assertEquals(bodyText().getText(), "Here are some examples of different JavaScript alerts which " +
                "can be troublesome for automation");
        for (int i = 0; i < jsButtons().size(); i++) {
            assertTrue(jsButtons().get(i).isDisplayed() && jsButtons().get(i).isEnabled());
            assertEquals(jsButtons().get(i).getText(), jsNames.get(i));
        }
        assertEquals(resultText().getText(), "Result:");
        assertEquals(resultMessage().getText(), "");
    }

    public void clickOnJSAlert() {
        jsAlert().click();
        waitForAlert();
        assertEquals(getAlertText(), "I am a JS Alert");
        driver.switchTo().alert().accept();
        assertEquals(resultMessage().getText(), "You successfuly clicked an alert");
    }

    public void clickOnJSConfirm(String action) {
        jsConfirm().click();
        waitForAlert();
        assertEquals(getAlertText(), "I am a JS Confirm");
        if (action.equals("OK")) {
            driver.switchTo().alert().accept();
            assertEquals(resultMessage().getText(), "You clicked: Ok");
        } else {
            driver.switchTo().alert().dismiss();
            assertEquals(resultMessage().getText(), "You clicked: Cancel");
        }
    }

    public void clickOnJSPromptWithText(String action, String text) {
        jsPrompt().click();
        waitForAlert();
        assertEquals(getAlertText(), "I am a JS prompt");
        if (action.equals("OK")) {
            driver.switchTo().alert().sendKeys(text);
            driver.switchTo().alert().accept();
            assertEquals(resultMessage().getText(), String.format("You entered: %s", text));
        } else {
            driver.switchTo().alert().sendKeys(text);
            driver.switchTo().alert().dismiss();
            assertEquals(resultMessage().getText(), "You entered: null");
        }
    }

    public void clickOnJSPromptWithoutText(String action) {
        jsPrompt().click();
        waitForAlert();
        assertEquals(getAlertText(), "I am a JS prompt");
        if (action.equals("OK")) {
            driver.switchTo().alert().accept();
            assertEquals(resultMessage().getText(), "You entered:");
        } else {
            driver.switchTo().alert().dismiss();
            assertEquals(resultMessage().getText(), "You entered: null");
        }
    }
}
