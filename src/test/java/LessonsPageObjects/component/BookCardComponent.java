package LessonsPageObjects.component;

import LessonsPageObjects.page_object.BasePage;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookCardComponent  {

    protected final WebDriver driver;

    protected final WebDriverWait wait;

    private final By button = By.xpath("//a[contains(@class, 'buy-link')]");

    public BookCardComponent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement findButton() {
        return driver.findElement(this.button);
    }

    public void waitButtonChanged() {
        wait.until(ExpectedConditions.textToBe(this.button, "оформить"));
    }
}
