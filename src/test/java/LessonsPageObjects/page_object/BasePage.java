package LessonsPageObjects.page_object;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    private final By searchField = By.xpath("//input[@id='search-field']");

    protected final WebDriver driver;

    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void findBook(String name) {
        driver.findElement(searchField).sendKeys(name, Keys.RETURN);
    }
}
