package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPage {
	
	static final String PAYPAL = "PayPal";
	static final String CREDIT_OR_DEBIT_CARD = "Credit or Debit card";
	static final String BATTLENET_BALANCE = "Battle.net Balance";

	WebDriver driver;
	WebDriverWait wait;

    @FindBy(id="search-desktop-input")
    WebElement searchBox;
    
    @FindBy(xpath=" //storefront-product-selection//h1/strong")
    WebElement productTitle;
    
    @FindBy(xpath="//div[@class='product-button']//a")
    WebElement buyNowButton;
    
    @FindBy(xpath="//a[text()='Cancel']")
    WebElement cancelUpgrade;
    
    @FindBy(xpath="//input[@value='Continue']")
    WebElement continueUpgrade;
    
    @FindBy(xpath="//select[@name='paymentOption']")
    WebElement payment;
    
    @FindBy(xpath="//form[@id='payment-form']//button[text()='Continue to PayPal']")
    WebElement paypalPayment;
    
    @FindBy(xpath="//div[@class='card']//h1[text()=' Billing Information ']")
    WebElement cardPayment;
    
    @FindBy(xpath="//form[@data-payment-method-id='PAYPAL']")
    WebElement payPalForm;
    
    @FindBy(xpath="//form[@data-payment-method-id='TOKEN_CREDIT_CARD']")
    WebElement cardForm;
    
    @FindBy(xpath="//form[@data-payment-method-id='BATTLENET_BALANCE']")
    WebElement battleNetForm;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }   

    public void search(String product) throws InterruptedException {
    	 searchBox.sendKeys(product);
    	 Thread.sleep(5000);
    	 searchBox.sendKeys(Keys.DOWN);
    	 searchBox.sendKeys(Keys.ENTER);
    	 
    }
    
    public String getProductTitle() {
    	return productTitle.getText();
    }
    
    public void clickBuyNowButton() {
    	buyNowButton.click();
    }
    
    public void clickCancelButton() {
    	cancelUpgrade.click();
    }
    
    public void clickContinueButton() throws InterruptedException {
    	try {
	    	if (driver.getCurrentUrl().contains("wait"))
	    		wait.until(ExpectedConditions.visibilityOf(continueUpgrade));
	    	
	    	continueUpgrade.click();
    	} catch (Exception e) {
    		System.out.println("Upgrade not available");
    	}
    }
    
    public boolean verifyPaymentMethod(String option) {
    	boolean found = false;
    	
    	Select paymentField = new Select(payment);
    	List<WebElement> list = paymentField.getOptions();
    	
    	for (int i = 0; i < list.size(); i ++) {
    		
    		if (list.get(i).getText().contains(option)) {
    			found = true;
    		}
    	}
    	
    	return found;
    }
    
    public void selectPaymentMethod(String paymentMethod) {
    	Select paymentField = new Select(payment);
    	paymentField.selectByVisibleText(paymentMethod);
    }   
    
    public boolean isFormDisplayed(String paymentMethod) throws InterruptedException {
    	boolean displayed = false;
    	
    	switch (paymentMethod) {
		case PAYPAL: 
			if (payPalForm.isDisplayed())
				displayed = true;
			break;
					 
		case CREDIT_OR_DEBIT_CARD: 

			wait.until(ExpectedConditions.visibilityOf(cardForm));
			
			if (cardForm.isDisplayed())
				displayed = true;
		 	break;
		 	
		case BATTLENET_BALANCE: 
			if (battleNetForm.isDisplayed())
				displayed = true;
			break;	   
    	}
    	return displayed;
    }
}
