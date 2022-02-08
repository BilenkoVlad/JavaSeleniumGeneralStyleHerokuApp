package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class jQueryPage {
    private final WebDriver driver;
    private final By pageTitle = By.xpath("//h1[@class='entry-title']");
    private final String jQueryMenuUrl = "https://api.jqueryui.com/menu/";

    public jQueryPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement pageTitle() {
        return driver.findElement(pageTitle);
    }

    public void verifyJQueryPage() {
        assertEquals(pageTitle().getText(), "Menu Widget");
        assertEquals(driver.getCurrentUrl(), jQueryMenuUrl);
    }
}
