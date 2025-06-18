package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/test-output/extent-report.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Ayberk");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = getExtentReports().createTest(testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
