package test;

import automation.framework.exception.ExceptionHandling;
import automation.framework.reports.ExtentTestManager;
import automation.framework.utils.Utils;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.web.POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import static automation.framework.reports.ExtentListener.getScreenshot;
import static automation.framework.reports.ExtentListener.writeLog;
import static automation.framework.web.webdriver.DriverFactory.*;

public class BaseTest {

    static ThreadLocal<POM> pom;
    public static POM POM(){
        return pom.get();
    }
    String pathToConfigFile = System.getProperty("user.dir")+"/gradle.properties";
  //  @Parameters(value = {"browser","threadCount","groupName"})
    @BeforeSuite
    public void beforeSuite(/*@Optional("") String browser,@Optional("") String threadCount, @Optional("")String groupName*/){
        pom = new ThreadLocal<>();
        try {
            Utils.globalProperties.load(new FileInputStream(pathToConfigFile));
        }catch (IOException e){
            ExceptionHandling.writeException("IOException in global properties");
        }
        ExtentTestManager.createExtentReports();
      /*  if(!browser.isEmpty()){
            Utils.globalProperties.setProperty("browser",browser);
        }
        if(!threadCount.isEmpty()){
            Utils.globalProperties.setProperty("threadCount",threadCount);
        }
        if(!groupName.isEmpty()){
            Utils.globalProperties.setProperty("groupName",groupName);
        }*/
       // ExtentTestManager.setEnvironmentDetails(browser,Utils.globalProperties.getProperty("executionURL"));
    }
    @BeforeMethod
    public void beforeMethod(Method method, ITestResult result){
        setBrowser("chrome");
        ExtentTestManager.startTest(method.getName(),result.getMethod().getDescription());
       // ExtentListener.writeLog("Open a Chrome Browser");
    }

    @AfterMethod
    public void afterTest(ITestResult result){
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                System.out.println("======PASS=====");
                break;
            case ITestResult.FAILURE:
                System.out.println("======FAIL=====");
                String testResult = result.getThrowable().toString();
                writeLog(Status.FAIL,testResult,getScreenshot(getDriver()));
                break;
            case ITestResult.SKIP:
                System.out.println("======SKIP BLOCKED=====");
                break;
            default:
                throw new RuntimeException("Invalid status");
        }
        closeBrowser();
    }

    @AfterSuite
    public void afterSuite(){
        ExtentTestManager.extentReports.flush();
    }
}
