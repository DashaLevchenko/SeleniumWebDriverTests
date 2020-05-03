import categories.Categories;
import constants.FilterGroup;
import constants.WebDrivers;
import filteres.FilterProducts;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

        assertFilterResultsWereLoaded(color);
        assertNumberOfProductByFilter(color);

        List<WebElement> elements = filterProducts.getColorContainer();

        elements.forEach((webElement) -> {
            List<WebElement> listOfProductLinks = webElement.findElements(By.tagName("a"))
                    .stream()
                    .filter(webElement1 -> webElement1.getAttribute("href").contains("orange"))
                    .collect(Collectors.toList());
            Assertions.assertFalse(listOfProductLinks.isEmpty());
        });
    }

    @Test
    @Order(1)
    void filterProductBySize() {
        WebElement size = filterProducts.getFilterProductsBy("S", FilterGroup.SIZE);
        assertFilterResultsWereLoaded(size);
        assertNumberOfProductByFilter(size);
    }

    private void assertNumberOfProductByFilter(WebElement webElement) {
        String productNumberOnFilter = webElement.findElement(By.tagName("span")).getText();
        int expectedProductNumber = Integer.parseInt(productNumberOnFilter.replace("(", "").replace(")", ""));
        int actualProductNumber = filterProducts.getProductsContainer().size();

        Assertions.assertEquals(expectedProductNumber, actualProductNumber);
    }
    private void assertFilterResultsWereLoaded(WebElement element) {
        element.click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertFalse(getLoadingElement().isDisplayed());
    }

    private WebElement getLoadingElement() {
        return webDriver.findElement(By.id("center_column")).findElement(By.tagName("p"));
    }

}
