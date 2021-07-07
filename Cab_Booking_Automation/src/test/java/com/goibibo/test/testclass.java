package com.goibibo.test;

//import org.testng.annotations.Test;
//import org.testng.annotations.BeforeMethod;
//import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.goibibo.base.BaseUI;
import com.goibibo.pages.HomePage;

public class testclass extends BaseUI {
	
//	private WebDriver driver;
	
	By cab_button = getLocator("cab_xpath");

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}
    
	@Test
	public void verifyCabButtonText() {
		
		HomePage homePage = new HomePage(driver, logger);
		
		homePage.clickCabsButton();
		
		Assert.assertEquals("Cabs",getText(cab_button));
		
		
	}

}
