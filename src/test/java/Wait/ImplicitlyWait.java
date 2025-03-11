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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ImplicitlyWait {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
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
    public void testList() {
        driver.get("https://sky-todo-list.herokuapp.com/");
        // sleep(20000);
        List<WebElement> toDoElements = driver.findElements(By.xpath("//td"));
        assertTrue(toDoElements.size() > 0);
    }

    @Test
    public void testText() {
        driver.get("http://uitestingplayground.com/ajax");
        // sleep(15000);
        driver.findElement(By.cssSelector("#ajaxButton")).click();
        String text = driver.findElement(By.cssSelector(".bg-success")).getText();
        assertEquals("Data loaded with AJAX get request.", text);
        System.out.println(text);
    }
}
