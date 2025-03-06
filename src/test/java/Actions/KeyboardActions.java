package Actions;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class KeyboardActions {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/User-Agent-Switcher-for-Chrome-Chrome.crx"));
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
    public void testKeyboard()  {
        driver.get("http://www.uitestingplayground.com/textinput");
        Actions actions = new Actions(driver);
        //String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));

//        Keys command;
//        if (Platform.getCurrent().is(Platform.WINDOWS)) {
//            command = Keys.CONTROL;
//        }
//        else {
//            command = Keys.COMMAND;
//        }

        Keys command = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;


        actions
            .keyDown(command)
            .sendKeys(driver.findElement(By.cssSelector("body")), "a")
            .sendKeys("c")
            .sendKeys(inputName,"v")
            .keyUp(command)
            .pause(2000)
            .perform();
    }

}
