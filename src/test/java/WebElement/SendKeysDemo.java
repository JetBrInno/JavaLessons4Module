package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class SendKeysDemo {

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
        System.out.println(driver.findElement(By.tagName("body")).getText());
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals(buttonName, newButtonName);
    }

    @Test
    public void sendKeysAndBackSpace() {
        driver.get("http://www.uitestingplayground.com/textinput");
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        inputName.sendKeys(Keys.BACK_SPACE);
        inputName.sendKeys(Keys.BACK_SPACE);

        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals("Knop", newButtonName);
    }

    @Test
    public void copyPaste() {
        driver.get("http://www.uitestingplayground.com/textinput");
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        inputName.sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP));
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));

        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals("KnopkaKnopkaKnopka", newButtonName);
    }


    @Test
    public void uploadFile() {
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement inputFile = driver.findElement(By.cssSelector("#file-upload"));
        inputFile.sendKeys("C:\\Users\\Sammy\\Desktop\\работа\\4 модуль\\JavaLessons4Module\\src\\test\\resources\\file.html");

        WebElement button = driver.findElement(By.cssSelector("#file-submit"));
        button.click();
        String uploadedName = driver.findElement(By.cssSelector("#uploaded-files")).getText();
        assertEquals("file.html", uploadedName);
    }


}
