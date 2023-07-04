package automation.framework.web.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static synchronized void setBrowser(String browser){
        switch (browser){
            case "chrome":
                driver = ThreadLocal.withInitial(() -> {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver(CapabilitiesFactory.getCapabilities(browser));
                });
                break;
        }
    }

    public static synchronized WebDriver getDriver(){
        return driver.get();
    }

    public static void closeBrowser(){
        getDriver().quit();
    }
}
