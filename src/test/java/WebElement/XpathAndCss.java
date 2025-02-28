package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class XpathAndCss {

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
    public void testText() throws InterruptedException {
        driver.get("file:///C:/Users/Sammy/Desktop/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0/4%20%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8C/JavaLessons4Module/src/test/resources/file.html");
        WebElement webElement = driver.findElement(By.xpath("//p[text() = 'Очень вкусно.']"));
        String actualTitle = webElement.getText();
        //                     //body/p    //p     //html/body/p    //html//p
        assertEquals("Очень вкусно.", actualTitle);
    }


    @Test
    public void testButton() throws InterruptedException {
        driver.get("file:///C:/Users/Sammy/Desktop/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0/4%20%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8C/JavaLessons4Module/src/test/resources/file.html");
        driver.findElement(By.xpath("//button[text() = 'Заказать']")).click();
        //                                         //button[@class = "red"]
    }

    //     //b[contains(.,'Привет')]
}
