package TestClass;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseClass;
import POM.DashboardPage;
import POM.DownloadsPage;
import Utility.JsonUtil;
import Utility.LoginUtil;

public class TC_007_DownloadsTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_007_DownloadsTest.class);

    @BeforeClass
    public void loginApplication() {

        logger.info("Opening application URL: " + appUrl);
        driver.get(appUrl);

        logger.info("Logging into application");
        LoginUtil.login(driver);

        logger.info("Login successful");
    }

    @Test(dataProvider = "downloadsData")
    public void verifyDownloadsPage(String tcId, String expectedTitle, String expectedHeading, String expectedRows) {

        logger.info("Starting Downloads Test Case: " + tcId);

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goTodownloadPage();
        logger.info("Navigated to Downloads Page");

        DownloadsPage downloadsPage = new DownloadsPage(driver);
        SoftAssert soft = new SoftAssert();

        logger.info("Validating page title");
        String actualTitle = driver.getTitle();
        logger.info("Expected Title: " + expectedTitle + " | Actual Title: " + actualTitle);
        soft.assertEquals(actualTitle, expectedTitle);

        logger.info("Validating page heading");
        String actualHeading = downloadsPage.getHeadingText();
        logger.info("Expected Heading: " + expectedHeading + " | Actual Heading: " + actualHeading);
        soft.assertEquals(actualHeading, expectedHeading);

        logger.info("Validating number of download items");
        int actualRows = downloadsPage.getDownloadItemsCount();
        logger.info("Expected Rows: " + expectedRows + " | Actual Rows: " + actualRows);
        soft.assertEquals(actualRows, Integer.parseInt(expectedRows));

        logger.info("Validating official links");
        soft.assertTrue(downloadsPage.areOfficialLinksValid(), "Official links validation failed");

        logger.info("Validating download links");
        soft.assertTrue(downloadsPage.areDownloadLinksValid(), "Download links validation failed");

        soft.assertAll();

        logger.info("Downloads Test Case Completed: " + tcId);
    }

    @DataProvider
    public Object[][] downloadsData() throws IOException {

        logger.info("Loading Downloads test data from JSON");

        return JsonUtil.getData("downloadsData",
                "TC_ID",
                "expectedTitle",
                "expectedHeading",
                "expectedRows");
    }
}
