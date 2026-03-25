package TestClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BaseClass;
import POM.LoginPage;
import Utility.JsonUtil;

public class TC_002LoginTest extends BaseClass {

	@Test(dataProvider = "loginData")
	public void verifyLogin(String tcId,
			String email, 
			String password, 
			String exp) throws InterruptedException {
		
		driver.get(appUrl);
		Assert.assertEquals(driver.getTitle(),"Admin Portal | Log in"); // ........1
		
		logger.info("***** Starting LoginTest **");
		SoftAssert soft = new SoftAssert();
		logger.info("***** opened login page **");
		LoginPage lp = new LoginPage(driver);

		lp.email(email);
		lp.password(password);
		lp.signin();
		logger.info("login attempted for tcId {}", tcId);
		
		switch (exp) {
		case "success":
			wait.until(ExpectedConditions.titleIs("Admin Portal | Dashboard"));
			soft.assertEquals(driver.getTitle(), "Admin Portal | Dashboard");
			break;

		case "wrong_email":
			soft.assertEquals(lp.getEmailError(), "Please enter email as kiran@kiranacademy.com");
			break;

		case "wrong_password":
			soft.assertEquals(lp.getPasswordError(), "Please enter valid password");
			break;

		case "both_invalid":
			soft.assertEquals(lp.getEmailError(), "Please enter email as kiran@kiranacademy.com");
			soft.assertEquals(lp.getPasswordError(), "Please enter valid password");
			break;

		case "empty_email":
			soft.assertEquals(lp.getEmailError(), "Please enter email");
			break;

		case "empty_pass":
			soft.assertEquals(lp.getPasswordError(), "Please enter password.");
			break;

		default:
			Assert.fail("Unknown expected outcome in test data: " + exp + " for " + tcId);
		}
		
		soft.assertAll();
	}
	  @AfterClass
	    public void afterClass() {
	        logger.info("***** All Login Tests Completed *****");
			logger.info(" page navigate login  To dashboard.");
	    }

	@DataProvider
	public Object[][] loginData() throws IOException {
		return JsonUtil.getData("loginData", "TC_ID", "email", "password", "exp");
	}
}
