package PageObjects;

import org.openqa.selenium.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InputsPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='large-6 small-12 columns large-centered']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By inputField = By.xpath("//input");

    public InputsPage(WebDriver driver) {
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

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Inputs");
        assertEquals(bodyText().getText(), "Number");
        assertEquals(inputField().getAttribute("type"), "number");
        assertTrue(inputField().isDisplayed() && inputField().isEnabled());
    }

    private String getVal(WebElement webElement) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return String.valueOf(javascriptExecutor.executeScript("return arguments[0].value", webElement));
    }

    public void enterChars() {
        inputField().sendKeys("any test chars");
        inputField().sendKeys("eee");
        inputField().clear();
        inputField().sendKeys("123");
        inputField().clear();
        inputField().sendKeys("4561e4641");
        inputField().clear();
    }

    public void enterCharsViaArrows() {
        inputField().click();
        for (int i = 0; i < 50; i++) {
            inputField().sendKeys(Keys.ARROW_UP);
        }
        inputField().clear();
        for (int i = 0; i < 50; i++) {
            inputField().sendKeys(Keys.ARROW_DOWN);
        }
    }
}
