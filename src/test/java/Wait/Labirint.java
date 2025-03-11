package Wait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Labirint {
    private WebDriver driver;

    private WebDriverWait wait;

    private By buttonXpath = By.xpath("//a[contains(@class, 'btn-tocart')]");

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testList() {
        driver.get("https://www.labirint.ru/");
        driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys("Java", Keys.RETURN);

        WebElement buttonToCart = driver.findElement(buttonXpath);
        buttonToCart.click();
        // нужна задержка
        wait.until(ExpectedConditions.textToBe(buttonXpath, "оформить"));
        buttonToCart.click();
        WebElement cartTitle = driver.findElement(By.xpath("//span[@class='cart-title']"));
        assertTrue(cartTitle.isDisplayed());
    }

}
