package filteres;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterProducts {
    private WebDriver driver;
    private By leftColumn = By.id("left_column");
    private By centerColumn = By.id("center_column");
    private By colorContainer = By.xpath("//div[@class='color-list-container']");
    private By productContainer = By.xpath("//div[@class='product-container']");

    public FilterProducts(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getFilterProductsBy(String filter, String idAttributeGroup) {
        return driver
                .findElement(leftColumn)
                .findElement(By.id(idAttributeGroup))
                .findElements(By.tagName("a"))
                .stream()
                .filter(webElement -> webElement.getText().startsWith(filter + " ("))
                .collect(Collectors.toList()).get(0);
    }

    public List<WebElement> getColorContainer() {
        return getCenterColumn()
                .findElements(colorContainer);
    }

    public List<WebElement> getProductsContainer() {
        return getCenterColumn()
                .findElements(productContainer);
    }

    public int getNumberProductsWereFiltered(WebElement webElement){
        String productNumberOnFilter = webElement.findElement(By.tagName("span")).getText();
        return Integer.parseInt(productNumberOnFilter.replace("(", "").replace(")", ""));
    }

    public WebElement getLoadingElement() {
        return getCenterColumn().findElement(By.tagName("p"));
    }

    private WebElement getCenterColumn(){
        return driver
                .findElement(centerColumn);
    }

}
