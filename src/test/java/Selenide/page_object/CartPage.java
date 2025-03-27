package Selenide.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {
    //public final By cartTitle = By.xpath("//span[@class='cart-title']");

    public final SelenideElement cartTitle = $x("//span[@class='cart-title']");

    public final SelenideElement cartCounter = $x("//span[contains(@class, 'j-cart-count')]");

    @Step("Открыть страница Корзина")
    public void open() {
        Selenide.open("https://www.labirint.ru/cart/");
    }
}
