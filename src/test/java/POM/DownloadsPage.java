package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class DownloadsPage extends BasePage {

	public DownloadsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Downloads')]")
	private WebElement heading;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//tr[td]")
	private List<WebElement> rows;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//a[span[contains(text(),'Official Website')]]")
	private List<WebElement> officialLinks;

	@FindBy(xpath = "//table[contains(@class,'table-hover')]//a[@download]")
	private List<WebElement> downloadableLinks;

	public String getHeadingText() {
		return heading.getText().trim();
	}

	public int getDownloadItemsCount() {
		return rows.size();
	}

	public boolean areOfficialLinksValid() {
		for (WebElement link : officialLinks) {
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

	public boolean areDownloadLinksValid() {
		for (WebElement link : downloadableLinks) {
			String href = link.getAttribute("href");
			String download = link.getAttribute("download");
			if (href == null || href.trim().isEmpty()) {
				return false;
			}
			if (download == null) {
				return false;
			}
		}
		return true;
	}
}
