package pageobject.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.web.BasePOM;

public class HomePage extends BasePOM {
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")
    WebElement googleSearchButton;

    @FindBy(id = "APjFqb")
    WebElement textBar;

    public void clickGoogleSearchButton(){
        webAction.clickOnElement(googleSearchButton);
    }

    public void enterText(String textToEnter){
        webAction.enterText(textBar,textToEnter);
    }
}
