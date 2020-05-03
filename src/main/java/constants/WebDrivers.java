package constants;

import org.openqa.selenium.WebDriver;
import drivers.ChromeWebDriver;

public class WebDrivers {
    public static final WebDriver CHROME_WEB_DRIVER = new ChromeWebDriver(URL.BASE_URL).getDriver();
}
