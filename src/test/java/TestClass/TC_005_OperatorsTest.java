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
import POM.OperatorsPage;
import Utility.JsonUtil;
import Utility.LoginUtil;

public class TC_005_OperatorsTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_005_OperatorsTest.class);

    @BeforeClass
    public void loginApplication() {

        logger.info("Opening application URL: " + appUrl);
        driver.get(appUrl);

        logger.info("Logging into application");
        LoginUtil.login(driver);

        logger.info("Login successful");
    }

    @Test(dataProvider = "operatorsData")
    public void verifyOperatorsPage(String tcId, String expectedTitle, String expectedHeading,
                                    String expectedRows, String keyPerson, String keyContact) {

        logger.info("Starting Operators Test Case: " + tcId);

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickOperator();
        logger.info("Clicked on Operators menu");

        OperatorsPage operatorsPage = new OperatorsPage(driver);
        SoftAssert soft = new SoftAssert();

        logger.info("Validating page title");
        soft.assertEquals(driver.getTitle(), expectedTitle);

        logger.info("Validating page heading");
        soft.assertEquals(operatorsPage.getHeadingText(), expectedHeading);

        logger.info("Validating number of operators");
        int actualRows = operatorsPage.getOperatorCount();
        logger.info("Expected rows: " + expectedRows + " | Actual rows: " + actualRows);

        soft.assertEquals(actualRows, Integer.parseInt(expectedRows));

        logger.info("Checking if operator exists: " + keyPerson + " | Contact: " + keyContact);
        soft.assertTrue(operatorsPage.isOperatorPresent(keyPerson, keyContact));

        soft.assertAll();

        logger.info("Operators Test Case Completed: " + tcId);
    }

    @DataProvider
    public Object[][] operatorsData() throws IOException {

        logger.info("Loading Operators test data from JSON file");

        return JsonUtil.getData("operatorsData",
                "TC_ID",
                "expectedTitle",
                "expectedHeading",
                "expectedRows",
                "keyPerson",
                "keyContact");
    }
}
