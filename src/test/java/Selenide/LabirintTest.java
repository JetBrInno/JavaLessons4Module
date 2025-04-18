package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BrowserPerTestStrategyExtension.class)
public class LabirintTest {

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "firefox";
        Configuration.browserPosition = "2500x50";
        Configuration.browserSize = "1024x768";
        Configuration.timeout = 20000;
        Configuration.headless = false;
    }


    @Test
    public void testAddButton() {
        SelenideElement buttonToCart = $x("//a[contains(@class, 'btn-tocart')]");

        open("https://www.labirint.ru/");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));
        refresh();
        $x("//input[@id='search-field']").type("Java").pressEnter();
        buttonToCart.click();
        buttonToCart.shouldHave(text("Оформить")).click();
    }


    @Test
    public void testAddButtons() {
        ElementsCollection buttonsList = $$x("//a[contains(@class, 'btn-tocart')]");
        SelenideElement counter = $(".basket-in-cart-a");

        open("https://www.labirint.ru/");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));
        refresh();
        $x("//input[@id='search-field']").type("Java").pressEnter();
        buttonsList.get(0).click();
        buttonsList.get(1).click();
        buttonsList.get(2).click();
        buttonsList.get(2).shouldHave(text("Оформить")).click();
        counter.shouldHave(text("3"));
    }

}
