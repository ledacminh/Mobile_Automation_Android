package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        reporter.config().setReportName("Demo app");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Appium Java Framework");
        extentReports.setSystemInfo("Author", "Minh Le");
        return extentReports;
    }
}
