package TestClass;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseClass;
import POM.DashboardPage;
import Utility.ExcelUtil;
import Utility.LoginUtil;

public class TC_003_DashboardTest extends BaseClass {

    @BeforeClass
    public void loginApplication()
    {
        LoginUtil.login(driver);
        logger.info("Login successful");
    }

    @Test(dataProvider = "dashboardData")
    public void verifyDashboard(String tcId,
                                String expectedTitle,
                                String MenuCount,
                                String courseCount,
                                String MenuName,
                                String CourseName) {

        logger.info("***** Starting Dashboard Test : " + tcId + " *****");

        DashboardPage d = new DashboardPage(driver);
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(d.getDashboardTitle(), expectedTitle);

        soft.assertEquals(
                d.getCoursesCount(),
                Integer.parseInt(courseCount));

        soft.assertEquals(
                d.getLeftMenuCount(),
                Integer.parseInt(MenuCount));

        if (MenuName != null && !MenuName.trim().isEmpty()) {
            soft.assertTrue(d.isMenuPresent(MenuName), "Menu not found: " + MenuName);
        }

        if (CourseName != null && !CourseName.trim().isEmpty()) {
            soft.assertTrue(d.isCoursePresent(CourseName), "Course not found: " + CourseName);
        }

        soft.assertAll();
    }

    @AfterClass
    public void afterClass() {
        logger.info("***** Dashboard Validation Completed *****");
    }

    @DataProvider
    public Object[][] dashboardData() throws IOException {
        return ExcelUtil.getData("dash");
    }
}
