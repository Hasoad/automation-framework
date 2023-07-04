package automation.framework.reports;

import automation.framework.logs.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static automation.framework.reports.ExtentTestManager.getTest;

public class ExtentListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        ExtentTestManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        getTest().log(Status.PASS, "Test passed");
    }

    public synchronized static String  getScreenshot(WebDriver driver) {
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    public synchronized static void writeLog(String message){
        getTest().log(Status.INFO,message);
    }

    public synchronized static void writeLog(Status status, String message){
        getTest().log(status,message);
    }

    public synchronized static void writeLog(Status status, String message, String screenshotPath){
        getTest().log(status, message,getTest().addScreenCaptureFromBase64String(screenshotPath).getModel().getMedia().get(0));
    }
}
