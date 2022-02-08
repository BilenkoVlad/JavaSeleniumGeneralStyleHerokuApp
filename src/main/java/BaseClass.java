import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Properties properties;

    public WebDriver DriverInitialization() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\browserSelection.properties");
        properties.load(fileInputStream);
        String browser_name = properties.getProperty("browser_name");

        switch (browser_name) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                String downloadFilepath = "C:\\Users\\vladyslav.bilenko\\Desktop\\My projects\\JavaSeleniumTests\\herokuApp\\src\\main\\resources\\downloads";
                HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
                chromePreferences.put("profile.default_content_settings.popups", 0);
                chromePreferences.put("download.default_directory", downloadFilepath);
                chromePreferences.put("disable-popup-blocking", true);
                chromePreferences.put("download.prompt_for_download", false);
                chromePreferences.put("safebrowsing.enabled", true);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePreferences);
                chromeOptions.addArguments("--test-type");
                chromeOptions.addArguments("--disable-extensions"); //to disable browser extension popup


                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                driver.manage().window().maximize();
                break;
        }
        return driver;
    }

    public void refreshThePage() {
        driver.navigate().refresh();
    }

    public void navigateBackInPage() {
        driver.navigate().back();
    }

    public void clickOnLink(By element) {
        driver.findElement(element).click();
    }
}
