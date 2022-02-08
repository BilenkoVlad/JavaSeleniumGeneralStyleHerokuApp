package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class BasicAuthPage {
    private final WebDriver driver;
    private final By pageNameText = By.xpath("//div[@class='example']/h3");
    private final By pageBodyText = By.xpath("//div[@class='example']/p");

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement pageNameText() {
        return driver.findElement(pageNameText);
    }

    public WebElement pageBodyText() {
        return driver.findElement(pageBodyText);
    }

    public void basicAuthWithCredentials(Boolean auth) {
        String urlWithBasicAuth = "the-internet.herokuapp.com/basic_auth";
        if (auth) {
            String correctUserName = "admin";
            String correctPassword = "admin";
            driver.get(String.format("https://%s:%s@%s", correctUserName, correctPassword, urlWithBasicAuth));
        } else {
            String incorrectUserName = "incorrect";
            String incorrectPassword = "incorrect";
            driver.get(String.format("https://%s:%s@%s", incorrectUserName, incorrectPassword, urlWithBasicAuth));
        }
    }

    public void verifyPageText() {
        assertEquals(pageNameText().getText(), "Basic Auth");
        assertEquals(pageBodyText().getText().trim(), "Congratulations! You must have the proper credentials.");
    }
}
