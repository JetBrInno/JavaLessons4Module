import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCloseOneWindow() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.close();
    }

    @Test
    public void testInfo() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();
    }

    @Test
    public void testRedirect() {
        driver.get("http://yandex.ru/");
        assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }

    @Test
    public void testNavigate() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/windows");
        sleep(500);
        driver.navigate().refresh();
        sleep(500);
        driver.navigate().back();
        sleep(500);
        driver.navigate().forward();
    }

    @Test
    public void testWindow() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/windows");
        Point point = driver.manage().window().getPosition();
        //driver.manage().window().setSize(new Dimension(300, 300));

        Dimension dimension = driver.manage().window().getSize();
    }

    @Test
    public void testAlert() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Alert alert = driver.switchTo().alert();
        assertEquals("I am a JS Alert", alert.getText());
        alert.accept();
    }

    @Test
    public void testCookie() {
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        Set<Cookie> cookieSet = driver.manage().getCookies();
        driver.navigate().refresh();
    }

    @Test
    public void takeScreen() {
        driver.get("https://www.labirint.ru/");
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(Path.of("_output/result.png").toFile());
    }
}
