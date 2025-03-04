package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClickDemo {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testText() throws InterruptedException {
        driver.get("https://habr.com/ru");
        WebElement webElement = driver.findElement(By.xpath("//span[@class='tm-header__logo-wrap']"));
        webElement.click();
    }

    @Test
    public void testClickWithScroll() throws InterruptedException {
        driver.get("https://habr.com/ru");
        WebElement webElement = driver.findElement(By.xpath("//a[@href='/ru/feedback/']"));
        webElement.click();
    }

    @Test
    public void testElementClickInterceptedException() throws InterruptedException {
        driver.get("https://www.labirint.ru/");
        WebElement webElement = driver.findElement(By.xpath("//p[text()='Все для успешного изучения английского со скидкой 50%.']"));
        webElement.click();
    }

    @Test
    public void testElementNotInteractableException() throws InterruptedException {
        driver.get("https://habr.com/ru/feed/");
        WebElement webElement = driver.findElement(By.xpath("//div[@class='tm-articles-list__after-article'][3]"));
        webElement.click();
    }

}
