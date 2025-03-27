package Selenide.page_object;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;

public abstract class BasePage {
   // private final By searchField = By.xpath("//input[@id='search-field']");

    public SelenideElement searchField = $x("//input[@id='search-field']");

    // 1. Короче (не надо писать driver.findElement)
    // 2. Не нужно переживать о том, что вы вызовете driver.findElement слишком рано

    @Step("Найти книгу {name}")
    public void findBook(String name) {
        searchField.setValue(name).pressEnter();
    }

    @Attachment(value = "data", type = "text/plain", fileExtension = ".txt")
    public String attachData(String text) {
        return text;
    }

    @Attachment(value = "data", type = "image/png", fileExtension = ".png")
    public byte[] attachImage(SelenideElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Несовпадение пикселей", type = "image/png", fileExtension = ".png")
    public byte[] attachPng(byte[] image) {
        return image;
    }

    public void acceptCookies() {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));
        refresh();
    }
}
