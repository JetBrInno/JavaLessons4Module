package Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class MouseActions {

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
    public void testMouse() {
        driver.get("https://jspaint.app/");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));
        WebElement tools = driver.findElement(By.cssSelector(".tools"));
        WebElement brush = tools.findElement(By.cssSelector("[title='Кисть']"));
        WebElement zoom = tools.findElement(By.cssSelector("[title='Масштаб']"));
        WebElement paint = tools.findElement(By.cssSelector("[title='Заливка']"));
        WebElement rect = tools.findElement(By.cssSelector("[title='Прямоугольник']"));
        WebElement color = driver.findElement(By.cssSelector("[data-color='rgb(128,128,255)']"));

        actions.click(canvas).pause(1000).perform();

        actions.clickAndHold(canvas)
            .moveByOffset(100, 0)
            .pause(150)
            .moveByOffset(0, 100)
            .pause(150)
            .moveByOffset(-100, 0)
            .pause(150)
            .moveByOffset(0, -100)
            .pause(1500)
            .perform();

    }

    @Test
    public void testHighlightButton() {
        driver.get("https://jspaint.app/");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));

        actions.click(canvas).pause(1000).perform();

        WebElement fullScreenButton = driver.findElement(By.xpath("//tr[@aria-label='Fullscreen']"));
        actions
            .moveToElement(driver.findElement(By.xpath("//div[contains(@id, 'menu-button-Вид')]")))
            .pause(500)
            .click()
            .moveToElement(fullScreenButton, 5, 3)
            .pause(2000)
            //.click()
            //.pause(1500)
            .perform();

        String buttonClass = fullScreenButton.getDomAttribute("class");
        System.out.println(buttonClass);
        assertEquals("menu-row menu-item highlight",buttonClass);
    }

    @Test
    public void testColorPaint() {
        driver.get("https://jspaint.app/");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));
        WebElement redColor = driver.findElement(By.xpath("//div[@data-color='rgb(255,0,0)']"));

        actions
            .contextClick(redColor)
            .pause(1500)
            .contextClick(canvas)
            .pause(1500)
            .doubleClick(canvas)
            .perform();
    }

}
