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

    private final WebElement element;

    private final By button = By.cssSelector(".btn-tocart");

    private final By title = By.cssSelector(".product-card__name");

    public BookCardComponent(WebElement element) {
        this.element = element;
    }

    public WebElement findButton() {
        return element.findElement(this.button);
    }

    public String getTitle() {
        return element.findElement(this.title).getText();
    }

    public void waitButtonChanged(WebDriverWait wait) {
        wait.until(ExpectedConditions.textToBe(this.button, "оформить"));
    }
}
