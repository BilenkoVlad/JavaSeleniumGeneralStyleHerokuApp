package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h2");
    private final By fieldLabel = By.xpath("//label[@for='email']");
    private final By emailField = By.xpath("//input[@id='email']");
    private final By retrievePasswordButton = By.xpath("//button[@id='form_submit']");
    private final By buttonName = By.xpath("//button[@id='form_submit']/i");
    private final By sentNotification = By.xpath("//div[@id='content']");
    public String email = emailGenerator() + "@mailinator.com";

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement fieldLabel() {
        return driver.findElement(fieldLabel);
    }

    private WebElement emailField() {
        return driver.findElement(emailField);
    }

    private WebElement retrievePasswordButton() {
        return driver.findElement(retrievePasswordButton);
    }

    private WebElement buttonName() {
        return driver.findElement(buttonName);
    }

    private WebElement sentNotification() {
        return driver.findElement(sentNotification);
    }

    private String emailGenerator() {
        String symbolsForGenerator = "abcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int) (symbolsForGenerator.length() * Math.random());
            stringBuilder.append(symbolsForGenerator.charAt(index));
        }
        return stringBuilder.toString();
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Forgot Password");
        assertEquals(fieldLabel().getText(), "E-mail");
        assertTrue(emailField().isDisplayed() && emailField().isEnabled());
        assertEquals(buttonName().getText(), "Retrieve password");
        assertTrue(retrievePasswordButton().isDisplayed() && retrievePasswordButton().isEnabled());
    }

    public void enterEmailForRestorePassword() {
        emailField().sendKeys(email);
        retrievePasswordButton().click();
        assertTrue(sentNotification().isDisplayed());
        assertEquals(sentNotification().getText().trim(), "Your e-mail's been sent!");
    }
}
