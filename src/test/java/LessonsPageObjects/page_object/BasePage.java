package LessonsPageObjects.page_object;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
   // private final By searchField = By.xpath("//input[@id='search-field']");

    @FindBy(xpath = "//input[@id='search-field']")
    private WebElement searchField;

    // 1. Короче (не надо писать driver.findElement)
    // 2. Не нужно переживать о том, что вы вызовете driver.findElement слишком рано

    public final WebDriver driver;

    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void findBook(String name) {
        searchField.sendKeys(name, Keys.RETURN);
    }
}
