package test.web;

import automation.framework.utils.Utils;
import org.testng.annotations.*;
import test.BaseTest;

import static automation.framework.reports.ExtentListener.*;

public class WebTest extends BaseTest {
    @Test(description = "This test method will just open a chrome browser for test1",groups = {"Regression"})
    public void test1(){
        String url = Utils.globalProperties.getProperty("googleHomePage").toString();
        POM().navigateTo(url);
        writeLog("Navigate to google.ca");
        POM().googleHomePage.enterText(Utils.globalProperties.getProperty("searchBarText").toString());
        POM().googleHomePage.clickGoogleSearchButton();
        }

    @Test(description = "This test method to throw an exception")
    public void test2(){
        POM().navigateTo("http://www.google.ca");
            int i;
            i = 9 / 0;
    }
}