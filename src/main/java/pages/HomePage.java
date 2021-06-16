package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

    @FindBy(xpath="//table//tr[@class='heading3']")
    WebElement homePageUserName;   
    
    @FindBy(xpath="//a[@data-name='account']//div[text()='My Account']")
    WebElement myAccount;
    
    @FindBy(xpath="//a[@data-analytics-placement='Nav - Account - Log In']")
    WebElement login;

    public HomePage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

    }   

    public String getHomePageTile() {

         return  driver.getTitle();

    }
    
    public void clickMyAccount() {
    	myAccount.click();

   }
    
    public void clickLogin() {
    	login.click();

   }
    
    
    
}
