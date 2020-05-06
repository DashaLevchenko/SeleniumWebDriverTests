import categories.Categories;
import constants.FilterGroup;
import constants.WebDrivers;
import filteres.FilterProducts;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilterTest {
    private static WebDriver webDriver = WebDrivers.CHROME_WEB_DRIVER;
    FilterProducts filterProducts = new FilterProducts(webDriver);

    @BeforeAll
    static void clickOnCategory() {
        new Categories(webDriver).clickOnCategoryElement("dresses");
    }

    @AfterAll
    static void closeBrowser() {
        webDriver.close();
    }

    @Test
    @Order(2)
    void filterProductByColor() {
        WebElement color = filterProducts.getFilterProductsBy("Orange", FilterGroup.COLOR);

        color.click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(color);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);

        filterProducts.getColorContainer().forEach(webElement ->
                assertFalse(filterProducts.getListOfColorLinks(webElement).isEmpty()));
    }

    @Test
    @Order(1)
    void filterProductBySize() {
        WebElement size = filterProducts.getFilterProductsBy("S", FilterGroup.SIZE);
        size.click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(size);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);
    }
}
