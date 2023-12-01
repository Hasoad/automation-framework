package test;

import automation.framework.exception.ExceptionHandling;
import automation.framework.reports.ExtentTestManager;
import automation.framework.utils.Utils;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
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

    static ThreadLocal<POM> pom = new ThreadLocal<POM>();
    public static POM POM(){
        return pom.get();
    }
    String pathToConfigFile = System.getProperty("user.dir")+"/gradle.properties";

    @BeforeSuite
    public void beforeSuite(/*@Optional("") String browser,@Optional("") String threadCount, @Optional("")String groupName*/){
        System.out.println("********** inside before suite **********");
        try {
            Utils.globalProperties.load(new FileInputStream(pathToConfigFile));
        }catch (IOException e){
            ExceptionHandling.writeException("IOException in global properties");
        }
        ExtentTestManager.createExtentReports();
    }
    @BeforeMethod
    public void beforeMethod(Method method, ITestResult result){
        if(!Utils.globalProperties.getProperty("executionType").equalsIgnoreCase("api")) {
            setBrowser(Utils.globalProperties.getProperty("browser"));
        }else{
            RestAssured.baseURI=Utils.globalProperties.getProperty("apiBaseURL");
        }
        pom.set(new POM(pathToConfigFile));
        ExtentTestManager.startTest(method.getName(),result.getMethod().getDescription());
        writeLog("Open a Chrome Browser");
    }

    @AfterMethod
    public void afterTest(ITestResult result){
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                System.out.println("======PASS=====");
                writeLog(Status.PASS,"TestCase PASSED");
                break;
            case ITestResult.FAILURE:
                System.out.println("======FAIL=====");
                String testResult = result.getThrowable().toString();
                if(!Utils.globalProperties.getProperty("executionType").equalsIgnoreCase("api")) {
                    writeLog(Status.FAIL, testResult, getScreenshot(getDriver()));
                }
                break;
            case ITestResult.SKIP:
                System.out.println("======SKIP BLOCKED=====");
                writeLog(Status.SKIP,"TestCase SKIPPED");
                break;
            default:
                throw new RuntimeException("Invalid status");
        }
        if(!Utils.globalProperties.getProperty("executionType").equalsIgnoreCase("api")) {
            closeBrowser();
        }
    }

    @AfterSuite
    public void afterSuite(){
        ExtentTestManager.extentReports.flush();
    }
}
