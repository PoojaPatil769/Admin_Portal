package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class UsefulLinksPage extends BasePage {

	public UsefulLinksPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Useful Links')]")
	private WebElement heading;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//tr[td]")
	private List<WebElement> rows;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//a[span[contains(text(),'Go')]]")
	private List<WebElement> goLinks;

	public String getHeadingText() {
		return heading.getText().trim();
	}

	public int getLinksCount() {
		return rows.size();
	}

	public boolean areAllGoLinksValid() {
		for (WebElement link : goLinks) {
			String href = link.getAttribute("href");
			String target = link.getAttribute("target");
			if (href == null || href.trim().isEmpty()) {
				return false;
			}
			if (!(href.startsWith("http://") || href.startsWith("https://"))) {
				return false;
			}
			if (!"_blank".equals(target)) {
				return false;
			}
		}
		return true;
	}
}
