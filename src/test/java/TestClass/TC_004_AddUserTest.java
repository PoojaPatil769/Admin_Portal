package TestClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseClass;
import POM.AddUserPage;
import POM.DashboardPage;
import POM.UsersPage;
import Utility.JsonUtil;
import Utility.LoginUtil;

public class TC_004_AddUserTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_004_AddUserTest.class);

    @BeforeClass
    public void loginApplication() {
        logger.info("Opening application URL: " + appUrl);
        driver.get(appUrl);

        logger.info("Logging into application");
        LoginUtil.login(driver);

        logger.info("Login completed successfully");
    }

    @Test(priority = 1)
    public void verifyUsersPageAndDelete() {

        logger.info("Starting verifyUsersPageAndDelete test");

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickUsers();
        logger.info("Clicked on Users menu");

        UsersPage usersPage = new UsersPage(driver);
        SoftAssert soft = new SoftAssert();

        logger.info("Verifying page title and heading");
        soft.assertEquals(driver.getTitle(), "Admin Portal | User");
        soft.assertEquals(usersPage.getHeadingText(), "Users");

        logger.info("Verifying user count");
        soft.assertTrue(usersPage.getUsersCount() >= 4);

        logger.info("Trying to delete default user");
        String defaultAlert = usersPage.deleteDefaultUserAndGetAlertText();
        soft.assertTrue(defaultAlert.contains("You can not delete Default User"));

        logger.info("Deleting row tr_2 user");
        boolean beforeDelete = usersPage.isRowDisplayed("tr_2");

        usersPage.deleteRowTwo();
        usersPage.acceptConfirmPopup();

        String deleteAlert = usersPage.acceptAlertAndGetText();
        boolean afterDelete = usersPage.isRowDisplayed("tr_2");

        logger.info("Delete alert message: " + deleteAlert);

        soft.assertTrue(beforeDelete, "Expected row tr_2 before delete.");
        soft.assertTrue(deleteAlert.isEmpty() || deleteAlert.contains("User deleted successfully."));
        soft.assertFalse(afterDelete, "Expected row tr_2 to be hidden after delete.");

        soft.assertAll();

        logger.info("verifyUsersPageAndDelete test completed");
    }

    @Test(dataProvider = "addUserData", priority = 2)
    public void verifyAddUser(String tcId, String name, String mobile, String email,
                              String course, String state, String password, String exp) {

        logger.info("Starting Add User Test Case: " + tcId);

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickUsers();

        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickAddUser();

        AddUserPage addUserPage = new AddUserPage(driver);
        SoftAssert soft = new SoftAssert();

        logger.info("Verifying Add User page title and heading");

        soft.assertEquals(driver.getTitle(), "Admin Portal | Add User");
        soft.assertEquals(addUserPage.getHeadingText(), "Add User");

        logger.info("Entering user details");

        addUserPage.enterUserName(name);
        addUserPage.enterMobile(mobile);
        addUserPage.enterEmail(email);
        addUserPage.enterCourse(course);
        addUserPage.selectGender("Male");
        addUserPage.selectState(state);
        addUserPage.enterPassword(password);

        logger.info("Submitting form for TC: " + tcId);

        switch (exp) {

        case "success":
            String alertText = addUserPage.clickSubmitAndGetAlertText();
            logger.info("Alert received: " + alertText);
            soft.assertTrue(alertText.contains("User Added Successfully"));
            break;

        case "name_error":
            logger.warn("Expecting name validation error");
            addUserPage.clickSubmit();
            soft.assertEquals(addUserPage.getActiveElementId(), "username");
            break;

        case "email_error":
            logger.warn("Expecting email validation error");
            addUserPage.clickSubmit();
            soft.assertEquals(addUserPage.getActiveElementId(), "email");
            break;

        case "password_error":
            logger.warn("Expecting password validation error");
            addUserPage.clickSubmit();
            soft.assertEquals(addUserPage.getActiveElementId(), "password");
            break;

        default:
            logger.error("Unknown expected outcome for TC: " + tcId);
            Assert.fail("Unknown expected outcome for " + tcId + " : " + exp);
        }

        soft.assertAll();

        logger.info("Add User Test Case Completed: " + tcId);
    }

    @Test(priority = 3)
    public void verifyCancelButton() {

        logger.info("Starting verifyCancelButton test");

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickUsers();

        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickAddUser();

        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.clickCancel();

        logger.info("Clicked Cancel button");

        UsersPage usersPageAfterCancel = new UsersPage(driver);

        Assert.assertEquals(driver.getTitle(), "Admin Portal | User");
        Assert.assertEquals(usersPageAfterCancel.getHeadingText(), "Users");

        logger.info("verifyCancelButton test completed");
    }

    @DataProvider
    public Object[][] addUserData() throws IOException {

        logger.info("Loading Add User test data from JSON");

        return JsonUtil.getData("addUserData", "TC_ID", "name", "mobile", "email",
                "course", "state", "password", "exp");
    }
}
