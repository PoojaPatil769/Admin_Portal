package TestClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseClass;
import POM.HomePage;
import POM.RegisterPage;
import Utility.ExcelUtil;

public class TC_001RegisterTest extends BaseClass {

	@Test(dataProvider = "registerData")
	public void verifyRegister(String tcId,
			String name, 
			String mobile,
			String email,
			String password, String exp){


		logger.info("***** Starting Register Test **");
		SoftAssert soft = new SoftAssert();

		HomePage hp = new HomePage(driver);
		hp.clickRegisterlink();

		Assert.assertEquals(driver.getTitle(),"Admin Portal | Registration Page"); // ........1

		logger.info("***** clicked register link **");

		RegisterPage rp = new RegisterPage(driver);

		rp.name(name);
		rp.mobile(mobile);
		rp.email(email);
		rp.password(password);
		rp.register();

		switch (exp) {
		case "success":

			String alertMsg = rp.handleAlert();
			logger.info("Alert Message : {}", alertMsg);
			soft.assertTrue(alertMsg.contains("User registered successfully"));//......2
			
			driver.navigate().back();
			break;

		case "name_error":
			logger.info("Name Error : {}", rp.getNameError());
			soft.assertEquals(rp.getNameError(), "Please enter Name.");//........3
			
			driver.navigate().back();
			break;

		case "mobile_error":
			logger.info("Mobile Error : {}", rp.getMobileError());
			soft.assertEquals(rp.getMobileError(), "Please enter Mobile.");//..........4
			
			driver.navigate().back();
			break;

		case "email_error":
			logger.info("Email Error : {}", rp.getEmailError());
			soft.assertEquals(rp.getEmailError(), "Please enter Email.");//..................5
			
			driver.navigate().back();
			break;

		case "password_error":
			logger.info("Password Error : {}", rp.getPassError());
			soft.assertEquals(rp.getPassError(), "Please enter Password.");//............6
			
			driver.navigate().back();
			break;
		}
		soft.assertAll();
	}
	  @AfterClass
	    public void afterClass() {
	        logger.info("***** All register Tests Completed *****");
			logger.info(" page navigate  register To login.");
	    }

	@DataProvider
	public Object[][] registerData() throws IOException {
		return ExcelUtil.getData("reg");
	}

}
