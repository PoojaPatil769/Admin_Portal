package POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class OperatorsPage extends BasePage {

	public OperatorsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Operators')]")
	private WebElement heading;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//tr[td]")
	private List<WebElement> rows;

	public String getHeadingText() {
		return heading.getText().trim();
	}

	public int getOperatorCount() {
		return rows.size();
	}

	public boolean isOperatorPresent(String name, String contact) {
		String xpath = "//table[contains(@class,'table-hover')]//tr[td[2][normalize-space()='" + name
				+ "'] and td[5][normalize-space()='" + contact + "']]";
		return !driver.findElements(By.xpath(xpath)).isEmpty();
	}
}
