package utilites;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class checkoutclass {
	protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        // Initialize the ExtentReports instance
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReportscartpage.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void tearDownReport() {
        // Flush the report
        extent.flush();
    }
}
