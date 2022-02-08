package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HorizontalSliderPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/h4");
    private final By slider = By.xpath("//input");
    private final By rangeNumber = By.xpath("//span[@id='range']");
    private final List<String> rangeValuesKeyboard = Arrays.asList("0", "0.5", "1", "1.5", "2", "2.5",
            "3", "3.5", "4", "4.5", "5");
    private final List<String> rangeValuesUpMouse = Arrays.asList("0", "0.5", "1", "1.5", "2", "2.5",
            "3", "3.5", "3.5", "4", "4.5", "5");
    private final List<String> rangeValuesDown = Arrays.asList("0", "0.5", "0.5", "1.5", "1.5", "2", "2.5",
            "3", "3.5", "4", "4", "5");
    private Actions actions;

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private WebElement slider() {
        return driver.findElement(slider);
    }

    private WebElement rangeNumber() {
        return driver.findElement(rangeNumber);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Horizontal Slider");
        assertEquals(bodyText().getText(), "Set the focus on the slider (by clicking on it) and use the" +
                " arrow keys to move it right and left. Or click and drag the slider with your mouse. It will " +
                "indicate the value of the slider to the right.");
        assertTrue(slider().isDisplayed() && slider().isEnabled());
        assertEquals(rangeNumber().getText(), "0");
    }

    public void moveSliderUpByMouse() {
        actions = new Actions(driver);
        int j = 0;
        for (int i = -25; i <= 30; i = i + 5) {
            actions.moveToElement(slider(), i, 0);
            actions.moveByOffset(i, 0).click().build().perform();
            assertEquals(rangeNumber().getText(), rangeValuesUpMouse.get(j));
            j++;
        }
    }

    public void moveSliderDownByMouse() {
        actions = new Actions(driver);
        int j = rangeValuesDown.size() - 1;
        for (int i = 25; i >= -30; i = i - 5) {
            actions.moveToElement(slider(), i, 0);
            actions.moveByOffset(i, 0).click().build().perform();
            assertEquals(rangeNumber().getText(), rangeValuesDown.get(j));
            j--;
        }
    }

    public void moveSliderUpByKeyboard() {
        actions = new Actions(driver);
        for (int i = 0; i < 50; i = i + 5) {
            actions.moveToElement(slider(), -25, 0);
            assertEquals(rangeNumber().getText(), rangeValuesKeyboard.get(i / 5));
            slider().sendKeys(Keys.ARROW_UP);
        }
    }

    public void moveSliderDownByKeyboard() {
        actions = new Actions(driver);
        for (int i = 50; i > 0; i = i - 5) {
            actions.moveToElement(slider(), -25, 0);
            assertEquals(rangeNumber().getText(), rangeValuesKeyboard.get(i / 5));
            slider().sendKeys(Keys.ARROW_DOWN);
        }
    }
}
