package categories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Categories {
    private WebDriver driver;

    public Categories(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCategoryElement(String category) {
        List<WebElement> categories = driver.findElement(By.id("block_top_menu"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"))
                .stream()
                .filter(webElement -> webElement.getText().equals(category.toUpperCase()))
                .collect(Collectors.toList());
        categories.get(0).findElement(By.tagName("a")).click();
    }
}
