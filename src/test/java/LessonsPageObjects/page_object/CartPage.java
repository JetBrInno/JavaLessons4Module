package LessonsPageObjects.page_object;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    //public final By cartTitle = By.xpath("//span[@class='cart-title']");

    @FindBy(xpath = "//span[@class='cart-title']")
    public WebElement cartTitle;

    public final By cartCounter = By.xpath("//span[contains(@class, 'j-cart-count')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.labirint.ru/cart/");
    }
}
