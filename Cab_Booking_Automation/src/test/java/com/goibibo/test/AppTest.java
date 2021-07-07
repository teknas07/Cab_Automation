package com.goibibo.test;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.goibibo.base.BaseUI;
import com.goibibo.pages.HomePage;
//import com.tripadvisor.base.BaseUI;

public class AppTest extends BaseUI
{
//	private WebDriver driver;
	
	By cab_button = getLocator("cab_xpath");

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}
    
	@Test
	public void verifyCabButtonText() {
		
		HomePage hp = new HomePage();
		
		hp.clickCabsButton();
		
		Assert.assertEquals("Cabs",getText(cab_button));
		
		
	}
}
