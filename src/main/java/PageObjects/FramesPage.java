package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FramesPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By links = By.xpath("//div[@class='example']/ul/li/a");
    private final List<String> linksNames = Arrays.asList("Nested Frames", "iFrame");

    public FramesPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> links() {
        return driver.findElements(links);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Frames");
        for (int i = 0; i < links().size(); i++) {
            assertEquals(links().get(i).getText(), linksNames.get(i));
            assertEquals(links().get(i).getTagName(), "a");
        }
    }

    public void clickNestedFramesLink() {
        for (int i = 0; i < links().size(); i++) {
            if (links().get(i).getText().equals("Nested Frames")) {
                links().get(i).click();
            }
        }
    }
}
