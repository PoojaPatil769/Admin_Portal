package TestClass;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseClass;
import POM.DashboardPage;
import POM.UsefulLinksPage;
import Utility.JsonUtil;
import Utility.LoginUtil;

public class TC_006_UsefulLinksTest extends BaseClass {

	@BeforeClass
	public void loginApplication() {
		driver.get(appUrl);
		LoginUtil.login(driver);
	}

	@Test(dataProvider = "usefulLinksData")
	public void verifyUsefulLinksPage(String tcId, String expectedTitle, String expectedHeading, String expectedRows) {
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickUsefulLink();

		UsefulLinksPage usefulLinksPage = new UsefulLinksPage(driver);
		SoftAssert soft = new SoftAssert();

		soft.assertEquals(driver.getTitle(), expectedTitle);
		soft.assertEquals(usefulLinksPage.getHeadingText(), expectedHeading);
		soft.assertEquals(usefulLinksPage.getLinksCount(), Integer.parseInt(expectedRows));
		soft.assertTrue(usefulLinksPage.areAllGoLinksValid());
		soft.assertAll();
	}
	@AfterClass
    public void afterClass() {
        logger.info("*****usefullinks Validation Completed *****");
    }

	@DataProvider
	public Object[][] usefulLinksData() throws IOException {
		return JsonUtil.getData("usefulLinksData", "TC_ID", "expectedTitle", "expectedHeading", "expectedRows");
	}
}
