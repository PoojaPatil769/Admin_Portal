package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;
	private static final String REPORT_DIR = "test-output/extent-reports";

	private ExtentManager() {
	}

	public static synchronized ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}
		return extent;
	}

	private static ExtentReports createInstance() {
		try {
			Files.createDirectories(Paths.get(REPORT_DIR));
		} catch (IOException e) {
			throw new RuntimeException("Unable to create extent report directory", e);
		}

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
		Path reportPath = Paths.get(REPORT_DIR, "ExtentReport-" + timestamp + ".html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("Hybrid Selenium Framework");

		ExtentReports reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Framework", "Selenium + TestNG");
		reports.setSystemInfo("Environment", "Local");
		return reports;
	}
}
