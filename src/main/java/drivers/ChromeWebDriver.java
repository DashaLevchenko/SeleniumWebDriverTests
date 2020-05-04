package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {
    private WebDriver driver;

    public ChromeWebDriver(String url){
        System.setProperty("webdriver.chrome.driver", "src\\main\\java\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }


}
