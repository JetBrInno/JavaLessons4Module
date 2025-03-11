package Wait;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpliticitlyWait {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOld() throws InterruptedException {
        driver.get("http://uitestingplayground.com/progressbar");

        driver.findElement(By.cssSelector("#startButton")).click();
        // когда нашли элемент с 75% жмем кнопку
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']"));
        String value = null;
        long startTime = System.currentTimeMillis();
        long finishTime = startTime + 20000;
        while (System.currentTimeMillis() < finishTime) {
            sleep(100);
            value = progressBar.getText();
            System.out.println(value);
            if (value.equals("75%")) {
                break;
            }
        }
        driver.findElement(By.cssSelector("#stopButton")).click();
        assertEquals("75%", value);
    }


    @Test
    public void testReal() {
        driver.get("http://uitestingplayground.com/progressbar");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ZERO);
        driver.findElement(By.cssSelector("#startButton")).click();
        // когда нашли элемент с 75% жмем кнопку
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']"));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='progressBar']"), "75%"));
        String value = progressBar.getText();
        driver.findElement(By.cssSelector("#stopButton")).click();
        assertEquals("75%", value);
    }

    @Test
    public void testActiveMenuLi() {
        driver.get("https://www.labirint.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        WebElement bookMenuElement = driver.findElement(By.xpath("//li[@data-event-content='Книги']"));
        actions.moveToElement(bookMenuElement).perform();

        wait.until(ExpectedConditions.attributeContains(
            By.xpath("//li[@data-event-content='Книги']"),
            "class",
            "b-toggle-active")
        );
    }
}
