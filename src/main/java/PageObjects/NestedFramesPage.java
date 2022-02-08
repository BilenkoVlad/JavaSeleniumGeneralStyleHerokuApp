package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class NestedFramesPage {
    private final WebDriver driver;
    private final By middleFrameset = By.xpath("//frame[@name='frame-top']");
    private final By leftFrame = By.xpath("//frame[@name='frame-left']");
    private final By middleFrame = By.xpath("//frame[@name='frame-middle']");
    private final By rightFrame = By.xpath("//frame[@src='/frame_right']");
    private final By bottomFrame = By.xpath("//frame[@name='frame-bottom']");
    private final By middleText = By.xpath("//div[@id='content']");
    private final By text = By.xpath("//body");

    public NestedFramesPage(WebDriver driver) {
        this.driver = driver;
    }

    private void leftFrame() {
        driver.switchTo().frame(driver.findElement(middleFrameset));
        driver.switchTo().frame(driver.findElement(leftFrame));
        assertEquals(driver.findElement(text).getText().trim(), "LEFT");
        driver.switchTo().defaultContent();
    }

    private void middleFrame() {
        driver.switchTo().frame(driver.findElement(middleFrameset));
        driver.switchTo().frame(driver.findElement(middleFrame));
        assertEquals(driver.findElement(middleText).getText().trim(), "MIDDLE");
        driver.switchTo().defaultContent();
    }

    private void rightFrame() {
        driver.switchTo().frame(driver.findElement(middleFrameset));
        driver.switchTo().frame(driver.findElement(rightFrame));
        assertEquals(driver.findElement(text).getText().trim(), "RIGHT");
        driver.switchTo().defaultContent();
    }

    private void bottomFrame() {
        driver.switchTo().frame(driver.findElement(bottomFrame));
        assertEquals(driver.findElement(text).getText().trim(), "BOTTOM");
        driver.switchTo().defaultContent();
    }

    public void checkNames() {
        leftFrame();
        middleFrame();
        rightFrame();
        bottomFrame();
    }
}
