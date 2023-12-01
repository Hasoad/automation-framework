package test.web;

import automation.framework.utils.Utils;
import org.testng.annotations.*;
import test.BaseTest;

import static automation.framework.reports.ExtentListener.*;

public class HomePageTest extends BaseTest {
    @Test(description = "This test method will just open a chrome browser for test1",groups = {"Regression"})
    public void test1(){
      String url = Utils.globalProperties.getProperty("googleHomePage").toString();
      POM().navigateTo(url);
      writeLog("Navigate to google.ca");
      POM().homePage.enterText(Utils.globalProperties.getProperty("searchBarText").toString());
      POM().homePage.clickGoogleSearchButton();
    }
}