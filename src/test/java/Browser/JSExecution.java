package Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class JSExecution {

    private WebDriver driver;

    private static final String JS_REMOVE = "document.querySelector(\".main-home-banner\").remove()";

    public static final String JS_ADD_SCORE = "localStorage.setItem(\"bestScore\",\"HELLO!!!\")\n";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
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
    public void screenWithoutAd() {
        driver.get("https://ya.ru/");
        ((JavascriptExecutor) driver).executeScript(JS_REMOVE);
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
            .renameTo(Path.of("_output/screenshotJS.png").toFile());
    }

    @Test
    public void changeScore() {
        driver.get("https://2048game.com/");
        ((JavascriptExecutor) driver).executeScript(JS_ADD_SCORE);
        driver.navigate().refresh();
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
            .renameTo(Path.of("_output/addScore.png").toFile());
    }
}
