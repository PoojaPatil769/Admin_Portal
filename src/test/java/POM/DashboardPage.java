package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
	WebElement dashboardHeading;

	// Course cards (for course count)
	@FindBy(xpath = "//div[@class='col-lg-3 col-xs-6']")
	private List<WebElement> courses;

	// Course names
	@FindBy(xpath = "//h3")
	private List<WebElement> courseNames;

	// LEFT MENU LIST
	@FindBy(xpath = "//ul[@class='sidebar-menu']/li/a/span")
	private List<WebElement> leftMenuItems;

	@FindBy(linkText = "Users")
	WebElement usersMenu;

	@FindBy(linkText = "Operators")
	WebElement operatorsLink;

	@FindBy(linkText = "Useful Links")
	WebElement usefulLink;

	@FindBy(linkText = "Downloads") //// span[text()="Downloads"]
	WebElement download;

	// dashboard Actions
	public String getDashboardTitle() {
		return dashboardHeading.getText();
	}

	public int getCoursesCount() {
		return courses.size();
	}

	public int getLeftMenuCount() {
		return leftMenuItems.size();
	}

	public boolean isMenuPresent(String expectedMenu) {
		for (WebElement menu : leftMenuItems) {
			if (menu.getText().trim().equalsIgnoreCase(expectedMenu.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean isCoursePresent(String expectedCourse) {
		for (WebElement course : courseNames) {
			if (course.getText().trim().equalsIgnoreCase(expectedCourse.trim())) {
				return true;
			}
		}
		return false;
	}

	public void clickOperator() {
		operatorsLink.click();
	}

	public void clickUsers() {
		usersMenu.click();
	}

	public void clickUsefulLink() {
		usefulLink.click();
	}

	public void goTodownloadPage() {
		download.click();
	}
}
