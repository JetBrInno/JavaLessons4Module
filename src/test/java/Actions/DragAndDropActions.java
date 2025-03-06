package Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
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
import org.openqa.selenium.interactions.Actions;

public class DragAndDropActions {

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
    public void testMultipleElements() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(driver);
        List<WebElement> columns = driver.findElements(By.cssSelector(".column"));
       // System.out.println(columns.get(0));
      //  System.out.println(columns.get(1));
        assertTrue(columns.get(0).isDisplayed());
        System.out.println(columns.size());
//        actions.
//
//            .perform();
    }

    @Test
    public void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(driver);
      //  WebElement columnA = driver.findElement(By.cssSelector("#column-a"));
      //  WebElement columnB = driver.findElement(By.cssSelector("#column-b"));

        actions
            .clickAndHold(driver.findElement(By.cssSelector("#column-a")))
            .moveToElement(driver.findElement(By.cssSelector("#column-b")))
            .pause(100)
            .release()
            .pause(100)
            .perform();

        List<WebElement> columns = driver.findElements(By.cssSelector(".column"));
        assertEquals("B", columns.get(0).getText());
        assertEquals("A", columns.get(1).getText());
        driver.navigate().refresh();

        actions
            .dragAndDrop(driver.findElement(By.cssSelector("#column-a")), driver.findElement(By.cssSelector("#column-b")))
            .pause(2000)
            .perform();
        columns = driver.findElements(By.cssSelector(".column"));
        assertEquals("B", columns.get(0).getText());
        assertEquals("A", columns.get(1).getText());
    }
}
