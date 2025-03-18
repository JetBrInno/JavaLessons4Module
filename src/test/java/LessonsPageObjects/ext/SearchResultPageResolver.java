package LessonsPageObjects.ext;

import LessonsPageObjects.page_object.CartPage;
import LessonsPageObjects.page_object.SearchResultPage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;

import static LessonsPageObjects.ext.ChromeDriverHelper.DRIVER;
import static LessonsPageObjects.ext.ChromeDriverHelper.getDriver;

public class SearchResultPageResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(SearchResultPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        WebDriver driver = (WebDriver) extensionContext.getStore(ChromeDriverHelper.namespace).getOrComputeIfAbsent(DRIVER, k -> getDriver());

        return new SearchResultPage(driver);
    }
}
