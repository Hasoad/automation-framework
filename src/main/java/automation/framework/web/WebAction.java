package automation.framework.web;

import automation.framework.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAction {

    static WebDriver driver;
    public WebAction(WebDriver driver){
        this.driver=driver;
    }
    public static void clickOnElement(WebElement element){
        new WebDriverWait(driver, Utils.DEFAULT_WAIT).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void clickOnElement(By element){
        new WebDriverWait(driver, Utils.DEFAULT_WAIT).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void enterText(WebElement element,String textToEnter){
        new WebDriverWait(driver, Utils.DEFAULT_WAIT).until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToEnter);
    }
}
