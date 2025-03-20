package LessonsPageObjects;

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
import org.openqa.selenium.OutputType;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverHelper.class)
@Owner("John Doe")
@Epic("Онлайн-магазин книг")
@Feature("Поисковая система")
@DisplayName("Тест для добавления товаров из поиска")
public class LabirintScreenTest {

    @Test
    @DisplayName("Скриншотный тест")
    @Description("Скриншотим поисковую строку")
    @Tag("Позитивный")
    public void screenTest(SearchResultPage searchResultPage, CartPage cartPage) throws IOException {
        cartPage.open();
       // cartPage.searchField.getScreenshotAs(OutputType.FILE).renameTo(Path.of("toBe.png").toFile());
        byte[] screenshotExpected = Files.readAllBytes(Path.of("toBe.png"));  // берем скрин как ДОЛЖНО БЫТЬ
        cartPage.searchField.sendKeys("aaa");
        byte[] screenshotActual = cartPage.searchField.getScreenshotAs(OutputType.BYTES);
        assertArrayEquals(screenshotExpected, screenshotActual);
    }

    @Test
    @DisplayName("Скриншотный тест")
    @Description("Скриншотим поисковую строку")
    @Tag("Позитивный")
    public void screenTestAshot(SearchResultPage searchResultPage, CartPage cartPage) throws IOException {
        cartPage.open();
       // BufferedImage screenshot = new AShot().takeScreenshot(cartPage.driver, cartPage.searchField).getImage();
       // ImageIO.write(screenshot, "png", Path.of("toBeAShot.png").toFile());
        cartPage.searchField.sendKeys("aaa");
        BufferedImage actual = new AShot().takeScreenshot(cartPage.driver, cartPage.searchField).getImage();
        BufferedImage toBe = ImageIO.read(Path.of("toBeAShot.png").toFile());
        ImageDiff diff = new ImageDiffer().makeDiff(toBe, actual);

        ImageIO.write(diff.getDiffImage(), "png", Path.of("diffImage.png").toFile());
        ImageIO.write(diff.getMarkedImage(), "png", Path.of("markedImage.png").toFile());
        ImageIO.write(diff.getTransparentMarkedImage(), "png", Path.of("transparentImage.png").toFile());


        byte[] screenTransparent = Files.readAllBytes(Path.of("transparentImage.png"));
        cartPage.attachPng(screenTransparent);
        assertFalse(diff.hasDiff(), "Сравнение изображения поисковой строки");
    }

}
