import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStepdefs {

    private WebDriver driver;
    private WebDriverWait wait;
    private By buttonXpath = By.xpath("//a[contains(@class, 'btn-tocart')]");

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("Я открываю главную страницу")
    public void openMainPage() {

        driver.get("https://www.labirint.ru/");
    }

    @And("Разрешаю куки")
    public void acceptCookie() {
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        driver.navigate().refresh();
    }



    @And("Добавляем в корзину")
    public void objectContainsJava() {
        WebElement buttonToCart = driver.findElement(buttonXpath);
        buttonToCart.click();
        // нужна задержка
        wait.until(ExpectedConditions.textToBe(buttonXpath, "оформить"));
        buttonToCart.click();
    }



    @Дано("Новый шаг")
    public void новыйШаг() {
    }

    @И("Я выполняю поиск по слову {string}")
    public void яВыполняюПоискПоСлову(String name) {
        driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys(name, Keys.RETURN);
    }
}
