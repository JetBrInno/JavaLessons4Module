package LessonsPageObjects.page_object;

import LessonsPageObjects.component.BookCardComponent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasePage {
    private final By bookCardComponent = By.cssSelector(".product-card");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public BookCardComponent getBookCardComponent() {
        WebElement bookCardElement = driver.findElement(bookCardComponent);
        return new BookCardComponent(bookCardElement);
    }

    public List<BookCardComponent> getBookCardComponents() {
        List<BookCardComponent> bookCardComponents = new ArrayList<>();
        List<WebElement> bookCardElements = driver.findElements(bookCardComponent);

        bookCardElements.forEach(bookCardElement -> bookCardComponents.add(new BookCardComponent(bookCardElement)));

        return bookCardComponents;
    }

    public String getHeaderText() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}
