package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extent;

	// ThreadLocal so parallel scenarios each get their own ExtentTest node
	private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	public static ExtentReports getInstance() {
		if (extent == null) {
			synchronized (ExtentReportManager.class) {
				if (extent == null) {
					ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
					spark.config().setTheme(Theme.DARK);
					spark.config().setDocumentTitle("Udemy UI Automation Report");
					spark.config().setReportName("BDD Test Execution Report");
					spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

					extent = new ExtentReports();
					extent.attachReporter(spark);
					extent.setSystemInfo("Application", "Udemy");
					extent.setSystemInfo("Browser", "Edge");
					extent.setSystemInfo("Environment", "Production");
					extent.setSystemInfo("Author", "QA Team");
				}
			}
		}
		return extent;
	}

	public static void setTest(ExtentTest test) {
		testThread.set(test);
	}

	public static ExtentTest getTest() {
		return testThread.get();
	}

	public static void flushReports() {
		if (extent != null) {
			extent.flush();
		}
	}
}