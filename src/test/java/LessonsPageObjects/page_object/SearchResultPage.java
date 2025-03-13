package LessonsPageObjects.page_object;

import LessonsPageObjects.component.BookCardComponent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasePage {

    public BookCardComponent bookCardComponent;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        bookCardComponent = new BookCardComponent(driver, wait);
    }

    public String getHeaderText() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}
