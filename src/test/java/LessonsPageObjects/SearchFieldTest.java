package LessonsPageObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import LessonsPageObjects.page_object.CartPage;
import LessonsPageObjects.page_object.MainPage;
import LessonsPageObjects.page_object.SearchResultPage;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFieldTest {
    private WebDriver driver;

    private WebDriverWait wait;

    private MainPage mainPage;

    private SearchResultPage searchResultPage;

    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
        mainPage = new MainPage(driver);
        searchResultPage = new SearchResultPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchField() {
        mainPage.open();
        mainPage.findBook("Java");
        String foundedHeader = searchResultPage.getHeaderText();
        assertEquals("Все, что мы нашли в Лабиринте по запросу «Java»", foundedHeader);
    }

    @Test
    public void testSearchFieldInCart() {
        cartPage.open();
        cartPage.findBook("Selenium");
        String foundedHeader = searchResultPage.getHeaderText();
        assertEquals("Все, что мы нашли в Лабиринте по запросу «Selenium»", foundedHeader);
    }
}
