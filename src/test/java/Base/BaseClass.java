package Base;

import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BaseClass {

    protected WebDriver driver;

    public Logger logger;
    public WebDriverWait wait;
    protected String appUrl;

    public WebDriver getDriver() {
        return driver;
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {

        logger = LogManager.getLogger(this.getClass());
        driver = createDriverWithRetry(browser, 2);

        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        URL appUrl = Thread.currentThread()
                .getContextClassLoader()
                .getResource("static Website/index.html");

        if (appUrl == null) {
            throw new RuntimeException("Application resource not found: static Website/index.html");
        }

        this.appUrl = appUrl.toExternalForm();

        getDriver().get(this.appUrl);
    }

    private WebDriver createDriverWithRetry(String browser, int attempts) {
        RuntimeException lastError = null;
        for (int i = 1; i <= attempts; i++) {
            try {
                if (browser.equalsIgnoreCase("chrome")) {
                    return new ChromeDriver();
                } else if (browser.equalsIgnoreCase("firefox")) {
                    return new FirefoxDriver();
                } else if (browser.equalsIgnoreCase("edge")) {
                    return new EdgeDriver();
                } else {
                    throw new RuntimeException("Invalid browser name");
                }
            } catch (RuntimeException e) {
                lastError = e;
                if (i < attempts) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Driver setup interrupted", ie);
                    }
                }
            }
        }
        throw lastError;
    }

    @AfterClass
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver = null;
        }
    }
}
