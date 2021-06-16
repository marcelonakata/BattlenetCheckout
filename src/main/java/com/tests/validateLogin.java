package com.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

public class validateLogin extends baseTest {
	
	@Test
	@Parameters({"username", "password" , "battleTag"})
	public void validateBattleTagTest(String username, String password, String battleTag) {
		
		HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
		
		homePage.clickLogin();
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.login(username, password);
		
		AccountPage accountPage = new AccountPage(driver);
		
		String userBattleTagDisplayed = accountPage.getUserBattleTag();
		
		Assert.assertTrue(userBattleTagDisplayed.contains(battleTag), "BattleTag of the account " + username + " has the battle tag: " + userBattleTagDisplayed + " .Expected value: " + battleTag );

	}
}
