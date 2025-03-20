package LessonsPageObjects.page_object;

import java.time.Duration;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
   // private final By searchField = By.xpath("//input[@id='search-field']");

    @FindBy(xpath = "//input[@id='search-field']")
    public WebElement searchField;

    // 1. Короче (не надо писать driver.findElement)
    // 2. Не нужно переживать о том, что вы вызовете driver.findElement слишком рано

    public final WebDriver driver;

    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @Step("Найти книгу {name}")
    public void findBook(String name) {
        searchField.sendKeys(name, Keys.RETURN);
    }

    @Attachment(value = "data", type = "text/plain", fileExtension = ".txt")
    public String attachData(String text) {
        return text;
    }

    @Attachment(value = "data", type = "image/png", fileExtension = ".png")
    public byte[] attachImage(WebElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Несовпадение пикселей", type = "image/png", fileExtension = ".png")
    public byte[] attachPng(byte[] image) {
        return image;
    }
}
