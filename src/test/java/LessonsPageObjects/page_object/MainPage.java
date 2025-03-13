package LessonsPageObjects.page_object;

import LessonsPageObjects.component.BookCardComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    public BookCardComponent bookCardComponent;

    public MainPage(WebDriver driver) {
        super(driver);
        bookCardComponent = new BookCardComponent(driver, wait);
    }

    public void open() {
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        driver.navigate().refresh();
    }
}
