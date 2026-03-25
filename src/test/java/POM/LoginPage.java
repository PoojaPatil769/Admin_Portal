package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Login Fields

    @FindBy(id = "email")
    WebElement txtemail;

    @FindBy(id = "password")
    WebElement txtpassword;

    @FindBy(xpath = "//*[@id='form']/div[3]/div/button")
    WebElement signIN;

    // Error Messages

    @FindBy(id = "email_error")
    WebElement EEmsg;

    @FindBy(id = "password_error")
    WebElement PEmsg;

    // Actions

    public void email(String mail) {
        txtemail.clear();
        txtemail.sendKeys(mail);
    }

    public void password(String pass) {
        txtpassword.clear();
        txtpassword.sendKeys(pass);
    }

    public void signin() {
        signIN.click();
    }

    // Error getters

    public String getEmailError() {
        return EEmsg.getText();
    }

    public String getPasswordError() {
        return PEmsg.getText();
    }
}