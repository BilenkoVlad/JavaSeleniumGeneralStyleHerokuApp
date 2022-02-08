package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class NewWindowPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");

    public NewWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "New Window");
    }
}
