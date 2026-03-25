package POM;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Register Fields

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "mobile")
    WebElement txtMobile;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    WebElement btnRegister;

    // Error Messages

    @FindBy(id = "name_error")
    WebElement nameError;

    @FindBy(id = "mobile_error")
    WebElement mobileError;

    @FindBy(id = "email_error")
    WebElement emailError;

    @FindBy(id = "password_error")
    WebElement passError;

    // Actions

    public void name(String name) {
        txtName.clear();
        if(name != null && !name.trim().isEmpty())
            txtName.sendKeys(name);
    }

    public void mobile(String mobile) {
        txtMobile.clear();
        if(mobile != null && !mobile.trim().isEmpty())
            txtMobile.sendKeys(mobile);
    }

    public void email(String email) {
        txtEmail.clear();
        if(email != null && !email.trim().isEmpty())
            txtEmail.sendKeys(email);
    }

    public void password(String pass) {
        txtPassword.clear();
        if(pass != null && !pass.trim().isEmpty())
            txtPassword.sendKeys(pass);
    }

    public void register() {
        btnRegister.click();
    }

    // Error getters

    public String getNameError() {
        return nameError.getText();
    }

    public String getMobileError() {
        return mobileError.getText();
    }

    public String getEmailError() {
        return emailError.getText();
    }

    public String getPassError() {
        return passError.getText();
    }

    // Alert handling

    public String handleAlert() {
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();
        return msg;
    }
}