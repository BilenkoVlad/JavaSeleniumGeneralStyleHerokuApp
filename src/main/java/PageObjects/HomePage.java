package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void getHomeUrl() {
        driver.get("https://the-internet.herokuapp.com");
    }

    public By getLinkByName(String linkName) {
        return By.xpath(String.format("//*[contains(text(), '%s')]", linkName));
    }
}
