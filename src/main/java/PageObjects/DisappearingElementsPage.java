package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DisappearingElementsPage {
    private final WebDriver driver;
    private final By headerPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By buttons = By.xpath("//ul//li");
    private final By hiddenButton = By.xpath("//ul//li//*[contains(text(), 'Gallery')]");
    private final List<String> buttonsNames = Arrays.asList("Home", "About", "Contact Us", "Portfolio", "Gallery");

    public DisappearingElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headerPage() {
        return driver.findElement(headerPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement hiddenButton() {
        return driver.findElement(hiddenButton);
    }

    private List<WebElement> buttons() {
        return driver.findElements(buttons);
    }

    public void verifyDefaultContent() {
        assertEquals(headerPage().getText(), "Disappearing Elements");
        assertEquals(bodyText().getText(), "This example demonstrates when elements on a page change " +
                "by disappearing/reappearing on each page load.");
        for (int i = 0; i < buttons().size(); i++) {
            assertTrue(buttons().get(i).isDisplayed());
            assertEquals(buttons().get(i).getText(), buttonsNames.get(i));
        }
    }

    public void waitFotPresenceOfHiddenElement() {
        while (buttons().get(0).isDisplayed()) {
            driver.navigate().refresh();
            if (buttons().size() == 5) {
                assertTrue(hiddenButton().isDisplayed());
                assertEquals(hiddenButton().getText(), buttonsNames.get(4));
                break;
            }
        }
    }

    public void waitForAbsenceOfHiddenElement() {
        while (buttons().get(0).isDisplayed()) {
            driver.navigate().refresh();
            if (buttons().size() == 4) {
                assertEquals(buttons().get(buttons().size() - 1).getText(), buttonsNames.get(3));
                break;
            }
        }
    }
}
