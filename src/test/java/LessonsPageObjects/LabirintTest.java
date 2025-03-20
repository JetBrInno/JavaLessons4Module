package LessonsPageObjects;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LessonsPageObjects.component.BookCardComponent;
import LessonsPageObjects.ext.CartPageResolver;
import LessonsPageObjects.ext.ChromeDriverHelper;
import LessonsPageObjects.ext.MainPageResolver;
import LessonsPageObjects.ext.SearchResultPageResolver;
import LessonsPageObjects.page_object.CartPage;
import LessonsPageObjects.page_object.SearchResultPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverHelper.class)
@Owner("John Doe")
@Epic("Онлайн-магазин книг")
@Feature("Поисковая система")
@DisplayName("Тест для добавления товаров из поиска")
public class LabirintTest {

    @Test
    @DisplayName("Добавление товара из поиска")
    @Description("Вводим в поиск слово, находим товар, добавляем в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @Link(url = "https://ya.ru", name = "Яндекс")
    @Tag("Позитивный")
    public void testList(SearchResultPage searchResultPage, CartPage cartPage) {
        cartPage.open();
        cartPage.driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        cartPage.driver.navigate().refresh();
        cartPage.findBook("Java");
        BookCardComponent bookCardComponent = searchResultPage.getBookCardComponent();
        WebElement buttonToCart = bookCardComponent.findButton();
        step("Жмем на кнопку Добавить в корзину", buttonToCart::click);
        step("Жмем на кнопку Оформить", () -> {
            bookCardComponent.waitButtonChanged(cartPage.wait);
            cartPage.attachImage(buttonToCart);
            buttonToCart.click();
        });
        cartPage.attachData("Прикладываем такой вот текст select * from table;");

        assertTrue(cartPage.cartTitle.isDisplayed());
        assertEquals("3", cartPage.driver.findElement(cartPage.cartCounter).getText());
    }

    @Test
    public void testThreeButtons(SearchResultPage searchResultPage, CartPage cartPage) {
        cartPage.open();
        cartPage.driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        cartPage.driver.navigate().refresh();
        cartPage.findBook("Java");

        List<BookCardComponent> bookCardComponents = searchResultPage.getBookCardComponents();
        BookCardComponent bookCardComponent1 = bookCardComponents.get(1);
        bookCardComponent1.findButton().click();
        BookCardComponent bookCardComponent2 = bookCardComponents.get(2);
        bookCardComponent2.findButton().click();
        BookCardComponent bookCardComponent3 = bookCardComponents.get(3);
        bookCardComponent3.findButton().click();
        System.out.println(bookCardComponent2.getTitle());
        System.out.println(bookCardComponent3.getTitle());
    }
}
