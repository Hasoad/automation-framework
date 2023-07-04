package automation.framework.web.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesFactory extends OptionManager{
    static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static DesiredCapabilities getCapabilities(String browser){
        switch (browser){
            case "chrome":
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
                break;
            case "firefox":
                break;
        }
        return capabilities;
    }
}
