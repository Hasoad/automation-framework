package automation.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    public static final ExtentReports extentReports = new ExtentReports();
    /**
     * This method creates extent report instance
     **/
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Reports.html");
        reporter.config().setReportName("Extent Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    /**
     *This method should be called before Test
     ***/
    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = createExtentReports().createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static void  setEnvironmentDetails(String browserName,String url){
        extentReports.setSystemInfo("Browser", browserName);
        extentReports.setSystemInfo("URL",url);

    }
}
