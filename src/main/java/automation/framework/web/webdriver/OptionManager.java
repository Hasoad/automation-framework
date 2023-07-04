package automation.framework.web.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionManager {

    public static ChromeOptions getChromeOptions (){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                return options;
    }
}
