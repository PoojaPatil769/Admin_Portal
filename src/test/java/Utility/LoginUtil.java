package Utility;

import POM.HomePage;
import POM.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginUtil {

    public static void login(WebDriver driver)
    {
        HomePage hp = new HomePage(driver);
        hp.clicklogin();

        LoginPage lp = new LoginPage(driver);
        lp.email("kiran@kiranacademy.com");
        lp.password("123456");
        lp.signin();
    }
}
