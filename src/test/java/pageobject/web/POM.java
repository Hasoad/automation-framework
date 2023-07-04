package pageobject.web;

import org.openqa.selenium.WebDriver;
import pageobject.web.page.GoogleHomePage;

import static automation.framework.web.webdriver.DriverFactory.getDriver;

public class POM {
    static WebDriver driver;
    public POM(){
        this.driver = getDriver();
    }

    public GoogleHomePage googleHomePage = new GoogleHomePage(driver);
    public void navigateTo(String url){
        driver.navigate().to(url);
    }
}
