package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NotificationMessagesPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By clickHereLink = By.xpath("//div[@class='example']/p/a");
    private final By notification = By.xpath("//div[@id='flash']");
    private final String[] text =
            {
                    "The message displayed above the heading is a notification message. " +
                            "It is often used to convey information about an action previously taken by the user.",
                    "",
                    "Some rudimentary examples include 'Action successful', 'Action unsuccessful, please try again', etc.",
                    "",
                    "Click here to load a new message."
            };
    private final List<String> notificationText = Arrays.asList("Action unsuccesful, please try again×",
            "Action successful×");

    public NotificationMessagesPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement clickHereLink() {
        return driver.findElement(clickHereLink);
    }

    private WebElement notification() {
        return driver.findElement(notification);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Notification Message");
        String[] textTrim = bodyText().getText().split("\n");
        assertEquals(Arrays.toString(textTrim), Arrays.toString(text));
        for (int i = 0; i < 2; i++) {
            if (notification().getText().trim().equals(notificationText.get(i))) {
                assertEquals(notification().getText().trim(), notificationText.get(i));
                assertTrue(notification().isDisplayed());
            }
        }
        assertTrue(clickHereLink().isDisplayed() && clickHereLink().isEnabled());
    }
}
