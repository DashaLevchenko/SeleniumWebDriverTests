package filteres;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FilterProducts {
    private WebDriver driver;

    public  FilterProducts(WebDriver driver){
        this.driver=driver;
    }


    public  WebElement getFilterProductsBy(String filter, String idAttributeGroup) {
        return driver
                .findElement(By.id("left_column"))
                .findElement(By.id(idAttributeGroup))
                .findElements(By.tagName("a"))
                .stream()
                .filter(webElement -> webElement.getText().startsWith(filter + " ("))
                .collect(Collectors.toList()).get(0);
    }

    public  List<WebElement> getColorContainer() {
       return driver
                .findElement(By.id("center_column"))
                .findElements(By.xpath("//div[@class='color-list-container']"));
    }

    public  List<WebElement> getProductsContainer() {
       return driver
                .findElement(By.id("center_column"))
                .findElements(By.xpath("//div[@class='product-container']"));
    }

}
