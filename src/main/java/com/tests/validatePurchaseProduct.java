package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ShopPage;

public class validatePurchaseProduct extends baseTest {
	
	static final String PAYPAL = "PayPal";
	static final String CREDIT_OR_DEBIT_CARD = "Credit or Debit card";
	static final String BATTLENET_BALANCE = "Battle.net Balance";
	
	@BeforeMethod
	@Parameters({"username", "password" , "battleTag"})
	public void setUp(String username, String password, String battleTag) {
		HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
		
		homePage.clickLogin();
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.login(username, password);
		
		AccountPage accountPage = new AccountPage(driver);
		
		accountPage.goToShop();
	}
	
	@Test
	public void validateTitleSelectProdut() throws InterruptedException {
		
		ShopPage shopPage = new ShopPage(driver);
		
		shopPage.search("Diablo III");
		
		Assert.assertTrue(shopPage.getProductTitle().contains("Diablo® III"));
		
	}
	
	@Test (dataProvider="PaymentMethod")
	public void validatePaymentMethodOptions(String paymentMethod) throws InterruptedException {
		
		ShopPage shopPage = new ShopPage(driver);
		
		shopPage.search("Diablo III");
		
		shopPage.clickBuyNowButton();
		
		shopPage.clickContinueButton();
		
		Assert.assertTrue(shopPage.verifyPaymentMethod(paymentMethod));
		
	}
	
	@Test (dataProvider="PaymentMethod")
	public void validateFieldsForPaymentMethod(String paymentMethod) throws InterruptedException {
		
		ShopPage shopPage = new ShopPage(driver);
		
		shopPage.search("Diablo III");
		
		shopPage.clickBuyNowButton();
		
		shopPage.clickContinueButton();
		
		shopPage.selectPaymentMethod(paymentMethod);
		
		Assert.assertTrue(shopPage.isFormDisplayed(paymentMethod));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name="PaymentMethod")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { PAYPAL},
            { CREDIT_OR_DEBIT_CARD},
            { BATTLENET_BALANCE }
        };

    }
}
