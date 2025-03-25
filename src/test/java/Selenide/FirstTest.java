package Selenide;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "firefox";
        Configuration.browserPosition = "2500x50";
        Configuration.browserSize = "1024x768";
        Configuration.timeout = 20000;

    }

    @Test
    public void testYa() {
        step("Открыть страницу", () -> open("https://www.ya.ru"));
        System.out.println(WebDriverRunner.url());
        System.out.println(WebDriverRunner.source());
        System.out.println(WebDriverRunner.getWebDriver().getTitle());
    }

//    @Test
//    public void testText() {
//        driver.get("http://uitestingplayground.com/ajax");
//        // sleep(15000);
//        driver.findElement(By.cssSelector("#ajaxButton")).click();
//        String text = driver.findElement(By.cssSelector(".bg-success")).getText();
//        assertEquals("Data loaded with AJAX get request.", text);
//        System.out.println(text);
//    }

    @Test
    public void testText() {
        open("http://uitestingplayground.com/ajax");
        // sleep(15000);
        //SelenideElement selenideElement = $("#ajaxButton");
        //SelenideElement selenideElement = $(By.xpath("//button[@id='ajaxButton']"));
        SelenideElement selenideElement = $x("//button[@id='ajaxButton']");

        selenideElement.click();
        $(".bg-success").shouldHave(text("Data loaded with AJAX get request.fasfasfafafa"));
        $(".bg-success").shouldHave(Condition.text("Data loaded with AJAX get request.fasfasfafafa"));
    }

    @Test
    public void testToDo() {
        step("Открыть страницу", () -> open("https://sky-todo-list.herokuapp.com/"));
        $$("tr").shouldHave(CollectionCondition.sizeGreaterThan(100), Duration.ofSeconds(3));
    }
}
