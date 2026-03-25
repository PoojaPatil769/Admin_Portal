package POM;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Base.BasePage;

public class AddUserPage extends BasePage {

	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	private WebElement txtUserName;

	@FindBy(id = "mobile")
	private WebElement txtMobile;

	@FindBy(id = "email")
	private WebElement txtEmail;

	@FindBy(id = "course")
	private WebElement txtCourse;

	@FindBy(id = "Male")
	private WebElement maleRadio;

	@FindBy(id = "Female")
	private WebElement femaleRadio;

	@FindBy(xpath = "//select[@class='form-control']")
	private WebElement stateSelect;

	@FindBy(id = "password")
	private WebElement txtPassword;

	@FindBy(id = "submit")
	private WebElement submitBtn;

	@FindBy(xpath = "//a[contains(@href,'users.html')]/span[contains(text(),'Cancel')]")
	private WebElement cancelBtn;

	@FindBy(xpath = "//h1[contains(text(),'Add User')]")
	private WebElement heading;

	public String getHeadingText() {
		return heading.getText().trim();
	}

	public void enterUserName(String value) {
		txtUserName.clear();
		if (value != null && !value.trim().isEmpty()) {
			txtUserName.sendKeys(value);
		}
	}

	public void enterMobile(String value) {
		txtMobile.clear();
		if (value != null && !value.trim().isEmpty()) {
			txtMobile.sendKeys(value);
		}
	}

	public void enterEmail(String value) {
		txtEmail.clear();
		if (value != null && !value.trim().isEmpty()) {
			txtEmail.sendKeys(value);
		}
	}

	public void enterCourse(String value) {
		txtCourse.clear();
		if (value != null && !value.trim().isEmpty()) {
			txtCourse.sendKeys(value);
		}
	}

	public void selectGender(String gender) {
		if ("female".equalsIgnoreCase(gender)) {
			femaleRadio.click();
		} else {
			maleRadio.click();
		}
	}

	public void selectState(String state) {
		Select select = new Select(stateSelect);
		select.selectByVisibleText(state);
	}

	public void enterPassword(String value) {
		txtPassword.clear();
		if (value != null && !value.trim().isEmpty()) {
			txtPassword.sendKeys(value);
		}
	}

	public void clickSubmit() {
		submitBtn.click();
	}

	public String clickSubmitAndGetAlertText() {
		submitBtn.click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public String getActiveElementId() {
		return driver.switchTo().activeElement().getAttribute("id");
	}

	public void clickCancel() {
		cancelBtn.click();
	}
}
