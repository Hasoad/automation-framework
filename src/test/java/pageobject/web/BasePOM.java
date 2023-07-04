package pageobject.web;

import automation.framework.web.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePOM {
    public static WebAction webAction;
    static WebDriver driver;
    public BasePOM(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver= driver;
        webAction = new WebAction(driver);
    }
}
