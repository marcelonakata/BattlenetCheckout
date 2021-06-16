package com.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class baseTest {
	
	WebDriver driver;
	String resourcePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
	
	@BeforeMethod
	@Parameters({"url"})
	public void setup(String url) {
		String OS = System.getProperty("os.name").toLowerCase();
		
		if (OS.contains("win"))
			System.setProperty("webdriver.chrome.driver", resourcePath + "chromedriver.exe");
			
		if (OS.contains("mac"))
			System.setProperty("webdriver.chrome.driver", resourcePath + "chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}
}
