package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MultipleWindowsPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By clickHereLink = By.xpath("//div[@class='example']/a");

    public MultipleWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement clickHereLink() {
        return driver.findElement(clickHereLink);
    }

    private Set<String> allTabs() {
        return driver.getWindowHandles();
    }

    private void switchToNewWindow() {
        driver.switchTo().window(getTheLastOpenedWindow());
    }

    private String getTheLastOpenedWindow() {
        String window = null;
        for (String s : allTabs()) {
            window = s;
        }
        return window;
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Opening a new window");
        assertEquals(clickHereLink().getText(), "Click Here");
        assertTrue(clickHereLink().isDisplayed() && clickHereLink().isEnabled());
    }

    public void clickOnLink() {
        clickHereLink().click();
        switchToNewWindow();
    }
}
