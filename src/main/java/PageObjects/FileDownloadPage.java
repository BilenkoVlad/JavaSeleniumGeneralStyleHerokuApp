package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileDownloadPage {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By files = By.xpath("//div[@class='example']/a");
    private final List<String> filesNames = Arrays.asList("index.html", "test.txt", "demo_15508342432349874956.tmp",
            "Demo_text.txt", "2A592550-2FDC-4331-8D6B-23C38A543622.png", "tenor.gif",
            "testUpload.json", "demo_2050763511808935012.tmp", "not_empty.txt",
            "124645434_3045429375670698_4382159432565866220_n.jpg", "file.txt", "config.js",
            "demo_17911001976479123519.tmp", "some-file.txt", "IEEE Univrse.jpg", "Dropdown.py", "RoseFlower.jpg");

    public FileDownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private List<WebElement> files() {
        return driver.findElements(files);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "File Downloader");
        assertEquals(files().size(), 17);
        for (int i = 0; i < files().size(); i++) {
            assertEquals(files().get(i).getText(), filesNames.get(i));
            assertEquals(files().get(i).getTagName(), "a");
            assertTrue(files().get(i).isDisplayed() && files().get(i).isEnabled());
        }
    }

    public void downloadFiles() {
        for (int i = 0; i < files().size(); i++) {
            files().get(i).click();
        }
    }
}
