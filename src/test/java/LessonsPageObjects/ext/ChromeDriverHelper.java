package LessonsPageObjects.ext;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeDriverHelper implements AfterEachCallback {

    public static ExtensionContext.Namespace namespace = ExtensionContext.Namespace.create("my_store");

    public static final String DRIVER = "driver";

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = (WebDriver) context.getStore(ChromeDriverHelper.namespace).get(DRIVER);

        if (driver != null) {
            driver.quit();
            context.getStore(namespace).remove(DRIVER);
        }
    }

    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }
}
