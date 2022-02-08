package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FormAuthenticationPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h2");
    private final By bodyText = By.xpath("//div[@class='example']/h4");
    private final By credentials = By.xpath("//div[@class='example']/h4/em");
    private final By usernameLabel = By.xpath("//label[@for='username']");
    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordLabel = By.xpath("//label[@for='password']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button");
    private final By loginButtonLabel = By.xpath("//button/i");
    private final By message = By.xpath("//div[@id='flash']");
    private final By logoutButton = By.xpath("//a[@class='button secondary radius']");
    private final By logoutButtonLabel = By.xpath("//a[@class='button secondary radius']/i");

    public FormAuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private List<WebElement> credentials() {
        return driver.findElements(credentials);
    }

    private WebElement usernameLabel() {
        return driver.findElement(usernameLabel);
    }

    private WebElement usernameField() {
        return driver.findElement(usernameField);
    }

    private WebElement passwordLabel() {
        return driver.findElement(passwordLabel);
    }

    private WebElement passwordField() {
        return driver.findElement(passwordField);
    }

    private WebElement loginButton() {
        return driver.findElement(loginButton);
    }

    private WebElement loginButtonLabel() {
        return driver.findElement(loginButtonLabel);
    }

    private WebElement message() {
        return driver.findElement(message);
    }

    private WebElement logoutButton() {
        return driver.findElement(logoutButton);
    }

    private WebElement logoutButtonLabel() {
        return driver.findElement(logoutButtonLabel);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Login Page");
        assertEquals(bodyText().getText(), "This is where you can log into the secure area. Enter tomsmith" +
                " for the username and SuperSecretPassword! for the password. If the information is wrong you should " +
                "see error messages.");

        assertEquals(usernameLabel().getText(), "Username");
        assertTrue(usernameField().isDisplayed() && usernameField().isEnabled());

        assertEquals(passwordLabel().getText(), "Password");
        assertTrue(passwordField().isDisplayed() && passwordField().isEnabled());

        assertEquals(loginButtonLabel().getText().trim(), "Login");
        assertTrue(loginButton().isDisplayed() && loginButton().isEnabled());
    }

    public void enterInvalidCredentials() {
        usernameField().sendKeys("invalidUsername");
        passwordField().sendKeys("invalidPassword");
        loginButton().click();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText().trim(), "Your username is invalid!\n×");
    }

    public void enterValidCredentials() {
        usernameField().sendKeys(credentials().get(0).getText());
        passwordField().sendKeys(credentials().get(1).getText());
        loginButton().click();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText().trim(), "You logged into a secure area!\n×");
        assertEquals(headersPage().getText().trim(), "Secure Area");
        assertEquals(bodyText().getText(), "Welcome to the Secure Area. When you are done click logout below.");

        assertEquals(logoutButtonLabel().getText().trim(), "Logout");
        assertTrue(logoutButton().isDisplayed() && logoutButton().isEnabled());
    }

    public void clickLogoutButton() {
        logoutButton().click();
        assertTrue(message().isDisplayed());
        assertEquals(message().getText().trim(), "You logged out of the secure area!\n×");
        verifyDefaultContent();
    }
}
