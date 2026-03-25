package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	//button[contains(text(),'Register')]"
	 @FindBy(linkText ="Register a new membership") WebElement registerLink;
	 @FindBy(xpath = "//button[text()='Sign In']") WebElement signIN;  

	public void clickRegisterlink()
	{
		registerLink.click();
	}
	public void clicklogin()
	{
		signIN.click();
	}
}
