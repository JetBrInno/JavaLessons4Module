package Selenide.component;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.text;

public class BookCardComponent {

    private final SelenideElement element;

    private final By button = By.cssSelector(".btn-tocart");

    private final By title = By.cssSelector(".product-card__name");

    public BookCardComponent(SelenideElement element) {
        this.element = element;
    }

    public SelenideElement findButton() {
        return element.find(this.button);
    }

    public String getTitle() {
        return element.find(this.title).getText();
    }
}
