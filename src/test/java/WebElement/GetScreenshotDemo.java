package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetScreenshotDemo {

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
    public void sendKeys() {
        driver.get("http://www.uitestingplayground.com/textinput");
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        assertTrue(inputName.getScreenshotAs(OutputType.FILE).renameTo(Path.of("inputName.png").toFile()));

        inputName.clear();
        inputName.getScreenshotAs(OutputType.FILE).renameTo(Path.of("inputNameAfterClear.png").toFile());
        inputName.sendKeys("abc");

    }
}
