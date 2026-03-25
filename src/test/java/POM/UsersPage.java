package POM;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class UsersPage extends BasePage {

	public UsersPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Users')]")
	private WebElement usersHeading;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//tr[td]")
	private List<WebElement> userRows;

	@FindBy(xpath = "//a[contains(@href,'add_user.html')]/button")
	private WebElement addUserBtn;

	@FindBy(xpath = "//span[contains(@class,'label-danger') and contains(@onclick,'Default User')]")
	private WebElement defaultUserDeleteBtn;

	@FindBy(xpath = "//tr[@id='tr_2']//span[contains(@class,'label-danger')]")
	private WebElement deletableUserDeleteBtn;

	public String getHeadingText() {
		return usersHeading.getText().trim();
	}

	public int getUsersCount() {
		return userRows.size();
	}

	public void clickAddUser() {
		addUserBtn.click();
	}

	public String deleteDefaultUserAndGetAlertText() {
		defaultUserDeleteBtn.click();
		return acceptAlertAndGetText();
	}

	public void deleteRowTwo() {
		deletableUserDeleteBtn.click();
	}

	public void acceptConfirmPopup() {
		Alert confirm = driver.switchTo().alert();
		confirm.accept();
	}

	public String acceptAlertAndGetText() {
		try {
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			alert.accept();
			return msg;
		} catch (NoAlertPresentException e) {
			return "";
		}
	}

	public boolean isRowDisplayed(String rowId) {
		try {
			List<WebElement> rows = driver.findElements(By.id(rowId));
			return !rows.isEmpty() && rows.get(0).isDisplayed();
		} catch (UnhandledAlertException e) {
			try {
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException ignored) {
				// no-op
			}
			List<WebElement> rows = driver.findElements(By.id(rowId));
			return !rows.isEmpty() && rows.get(0).isDisplayed();
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}
