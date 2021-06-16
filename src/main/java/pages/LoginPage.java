package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

    @FindBy(id="accountName")
    WebElement usernameField;
    
    @FindBy(id="password")
    WebElement passwordField;
    
    @FindBy(id="submit")
    WebElement submitButton;
    

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

    }   

    public void setUsername(String username) {
    	usernameField.sendKeys(username);

    }
    
    public void setPassword(String password) {
    	passwordField.sendKeys(password);

    }
    
    public void clickSubmit() {
    	submitButton.click();

    }
    
    public void login(String username, String password) {
    	setUsername(username);
    	setPassword(password);
    	
    	clickSubmit();
    }
    
}
