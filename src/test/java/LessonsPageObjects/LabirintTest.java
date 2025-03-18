package LessonsPageObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        WebElement buttonToCart = searchResultPage.bookCardComponent.findButton();
        buttonToCart.click();
        // нужна задержка
        searchResultPage.bookCardComponent.waitButtonChanged();
        buttonToCart.click();
        assertTrue(cartPage.driver.findElement(cartPage.cartTitle).isDisplayed());
        assertEquals("1", cartPage.driver.findElement(cartPage.cartCounter).getText());
    }
}
