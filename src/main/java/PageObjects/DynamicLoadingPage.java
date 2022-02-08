package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DynamicLoadingPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By bodyLinks = By.xpath("//div[@class='example']/a");
    private final String text1 = "It's common to see an action get triggered that returns a result dynamically. " +
            "It does not rely on the page to reload or finish loading. The page automatically gets updated " +
            "(e.g. hiding elements, showing elements, updating copy, etc) through the use of JavaScript.";
    private final String text2 = "There are two examples. One in which an element already exists on the page but it" +
            " is not displayed. And anonther where the element is not on the page and gets added in.";
    private final List<String> linksText = Arrays.asList("Example 1: Element on page that is hidden",
            "Example 2: Element rendered after the fact");
    private final List<String> bodyTexts = Arrays.asList(text1, text2);

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> bodyText() {
        return driver.findElements(bodyText);
    }

    private List<WebElement> bodyLinks() {
        return driver.findElements(bodyLinks);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Dynamically Loaded Page Elements");
        for (int i = 0; i < bodyLinks().size(); i++) {
            assertEquals(bodyLinks().get(i).getText(), linksText.get(i));
        }
        for (int i = 0; i < bodyText().size(); i++) {
            assertEquals(bodyText().get(i).getText(), bodyTexts.get(i));
        }
    }

    public void clickOnFirstLink() {
        bodyLinks().get(0).click();
    }

    public void clickOnSecondLink() {
        bodyLinks().get(1).click();
    }
}
