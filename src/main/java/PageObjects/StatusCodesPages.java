package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StatusCodesPages {
    private final WebDriver driver;
    private final By headersPage = By.xpath("//div[@class='example']/h3");
    private final By bodyText = By.xpath("//div[@class='example']/p");
    private final By codesLink = By.xpath("//div[@class='example']//li/a");
    private final List<String> codesStatuses = Arrays.asList("200", "301", "404", "500");


    public StatusCodesPages(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement headersPage() {
        return driver.findElement(headersPage);
    }

    private WebElement bodyText() {
        return driver.findElement(bodyText);
    }

    private List<WebElement> codesLink() {
        return driver.findElements(codesLink);
    }

    public void verifyDefaultContent() {
        assertEquals(headersPage().getText(), "Status Codes");
        assertEquals(bodyText().getText(), "HTTP status codes are a standard set of numbers used to " +
                "communicate from a web server to your browser to indicate the outcome of the request being made " +
                "(e.g. Success, Redirection, Client Error, Server Error). For a complete list of status codes, go here." +
                "\n\n" +
                "Some standard status codes you will run into include but are not limited to:");
        for (int i = 0; i < codesLink().size(); i++) {
            assertEquals(codesLink().get(i).getText(), codesStatuses.get(i));
        }
    }

    public void clickOn200Code() throws IOException {
        codesLink().get(0).click();
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        assertEquals(connection.getResponseCode(), 200);
    }

    public void clickOn301Code() throws IOException {
        codesLink().get(1).click();
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        assertEquals(connection.getResponseCode(), 301);
    }

    public void clickOn404Code() throws IOException {
        codesLink().get(2).click();
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        assertEquals(connection.getResponseCode(), 404);
    }

    public void clickOn500Code() throws IOException {
        codesLink().get(3).click();
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        assertEquals(connection.getResponseCode(), 500);
    }
}
