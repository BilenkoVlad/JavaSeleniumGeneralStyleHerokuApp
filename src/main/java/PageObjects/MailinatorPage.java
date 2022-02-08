package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class MailinatorPage {
    private final WebDriver driver;
    private final By enterEmailField = By.xpath("//input[@id='addOverlay']");
    private final By goButton = By.xpath("//button[@id='go-to-public']");
    private final By emailMessage = By.xpath("//tr[@class='even pointer ng-scope']");

    public MailinatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToEmailSite() {
        driver.get("https://www.mailinator.com");
    }

    private WebElement enterEmailField() {
        return driver.findElement(enterEmailField);
    }

    private WebElement goButton() {
        return driver.findElement(goButton);
    }

    private List<WebElement> emailMessage() {
        return driver.findElements(emailMessage);
    }

    public void enterEmail(String email) {
        System.out.println(email);
        enterEmailField().sendKeys(email);
        goButton().click();
    }

    public void clickOnReceivedEmail() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(emailMessage));
        assertTrue(emailMessage().get(0).isDisplayed());
    }
}
