import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTestWithRemoteWebDriver {

    private RemoteWebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserVersion", "124.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
            put("enableVNC", true);

        }});
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
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
