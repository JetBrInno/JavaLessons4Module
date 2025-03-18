package LessonsPageObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LessonsPageObjects.component.BookCardComponent;
import LessonsPageObjects.ext.CartPageResolver;
import LessonsPageObjects.ext.ChromeDriverHelper;
import LessonsPageObjects.ext.MainPageResolver;
import LessonsPageObjects.ext.SearchResultPageResolver;
import LessonsPageObjects.page_object.CartPage;
import LessonsPageObjects.page_object.SearchResultPage;
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
public class LabirintTest {

    @Test
    public void testList(SearchResultPage searchResultPage, CartPage cartPage) {
        cartPage.open();
        cartPage.driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        cartPage.driver.navigate().refresh();
        cartPage.findBook("Java");

        BookCardComponent bookCardComponent = searchResultPage.getBookCardComponent();
        WebElement buttonToCart = bookCardComponent.findButton();
        buttonToCart.click();
        // нужна задержка
        bookCardComponent.waitButtonChanged(cartPage.wait);
        buttonToCart.click();
        assertTrue(cartPage.cartTitle.isDisplayed());
        assertEquals("1", cartPage.driver.findElement(cartPage.cartCounter).getText());
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
