package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class HoversPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By avatars = By.xpath("//div[@class='figure']");
    private final By avatarsNames = By.xpath("//div[@class='figcaption']/h5");
    private final By avatarsLinks = By.xpath("//div[@class='figcaption']/a");
    private final List<String> avatarName = Arrays.asList("name: user1", "name: user2", "name: user3");

    public HoversPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private List<WebElement> avatars() {
        return driver.findElements(avatars);
    }

    private List<WebElement> avatarsNames() {
        return driver.findElements(avatarsNames);
    }

    private List<WebElement> avatarsLinks() {
        return driver.findElements(avatarsLinks);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Hovers");
        assertEquals(bodyText().getText(), "Hover over the image for additional information");
        for (int i = 0; i < avatars().size(); i++) {
            assertTrue(avatars().get(i).isDisplayed());
            assertFalse(avatarsNames().get(i).isDisplayed() && avatarsLinks().get(i).isDisplayed());
        }
    }

    public void hoverEachAvatar() {
        Actions actions = new Actions(driver);
        for (int i = 0; i < avatars().size(); i++) {
            actions.moveToElement(avatars().get(i)).build().perform();
            assertTrue(avatarsNames().get(i).isDisplayed() && avatarsLinks().get(i).isDisplayed());
            assertEquals(avatarsNames().get(i).getText(), avatarName.get(i));
            assertEquals(avatarsLinks().get(i).getText(), "View profile");
        }
    }
}
