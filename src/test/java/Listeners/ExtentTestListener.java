package Listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Base.BaseClass;
import Utility.ExtentManager;

public class ExtentTestListener implements ITestListener {

	private static final String SCREENSHOT_DIR = "test-output/extent-reports/screenshots";
	private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();
	private ExtentReports extent;

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getInstance();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getRealClass().getSimpleName();
		String methodName = result.getMethod().getMethodName();
		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
		String params = Arrays.stream(result.getParameters())
				.map(String::valueOf)
				.collect(Collectors.joining(", "));
		String prefix = browser == null ? "" : "[" + browser + "] ";
		String testName = params.isEmpty() ? prefix + className + "." + methodName
				: prefix + className + "." + methodName + " [" + params + "]";
		ExtentTest test = extent.createTest(testName);
		CURRENT_TEST.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		CURRENT_TEST.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest test = CURRENT_TEST.get();
		test.fail(result.getThrowable());
		WebDriver driver = extractDriver(result);
		if (driver != null) {
			try {
				String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
				if (screenshotPath != null) {
					test.fail("Screenshot on failure",
							MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				}
			} catch (Exception e) {
				test.warning("Could not capture screenshot: " + e.getMessage());
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		CURRENT_TEST.get().skip("Test skipped: " + result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
		CURRENT_TEST.remove();
	}

	private WebDriver extractDriver(ITestResult result) {
		Object instance = result.getInstance();
		if (instance instanceof BaseClass) {
			return ((BaseClass) instance).getDriver();
		}
		return null;
	}

	private String captureScreenshot(WebDriver driver, String methodName) {
		if (!(driver instanceof TakesScreenshot)) {
			return null;
		}
		try {
			Files.createDirectories(Paths.get(SCREENSHOT_DIR));
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
			String fileName = methodName + "-" + timestamp + ".png";
			Path destination = Paths.get(SCREENSHOT_DIR, fileName);
			File source;
			try {
				source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			}
			FileUtils.copyFile(source, destination.toFile());
			return "screenshots/" + fileName;
		} catch (IOException e) {
			return null;
		}
	}
}
