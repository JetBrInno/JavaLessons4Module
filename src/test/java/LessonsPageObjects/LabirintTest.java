package LessonsPageObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LessonsPageObjects.ext.CartPageResolver;
import LessonsPageObjects.ext.ChromeDriverResolver;
import LessonsPageObjects.ext.MainPageResolver;
import LessonsPageObjects.ext.SearchResultPageResolver;
import LessonsPageObjects.page_object.CartPage;
import LessonsPageObjects.page_object.MainPage;
import LessonsPageObjects.page_object.SearchResultPage;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverResolver.class)
public class LabirintTest {

    @Test
    public void testList(MainPage mainPage, SearchResultPage searchResultPage, CartPage cartPage, ChromeDriver driver) {
        mainPage.open();
        mainPage.findBook("Java");

        WebElement buttonToCart = searchResultPage.bookCardComponent.findButton();
        buttonToCart.click();
        // нужна задержка
        searchResultPage.bookCardComponent.waitButtonChanged();
        buttonToCart.click();
        assertTrue(driver.findElement(cartPage.cartTitle).isDisplayed());
        assertEquals("1", driver.findElement(cartPage.cartCounter).getText());
    }
}
