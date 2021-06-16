package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;

    @FindBy(xpath="//a[@data-name='account']//div[text()='My Account']/following-sibling::div")
    WebElement userBatteTag;

    @FindBy(xpath="//a[@data-name='shop']")
    WebElement shopNavBar;
    

    public AccountPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

    }   

    
    public String getUserBattleTag() {
    	return userBatteTag.getText();

    }
    
    public void goToShop() {
    	shopNavBar.click();
    }
}
