package pageobject.web;

import automation.framework.utils.Utils;
import org.openqa.selenium.WebDriver;
import pageobject.web.page.HomePage;

import java.util.Properties;

import static automation.framework.web.webdriver.DriverFactory.getDriver;

public class POM {
    static WebDriver driver;
    public HomePage homePage;
    public POM(String propertiesPath){
        System.out.println("********** inside POM **********");
        if(!Utils.globalProperties.getProperty("executionType").equalsIgnoreCase("api")) {
            this.driver = getDriver();
            webPageInit(driver);
        }else {

        }
    }

    private void webPageInit(WebDriver driver){
        homePage = new HomePage(driver);
    }

    public void navigateTo(String url){
        driver.navigate().to(url);
    }
}
