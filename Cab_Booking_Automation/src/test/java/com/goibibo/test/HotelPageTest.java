package com.goibibo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.goibibo.base.BaseUI;
import com.goibibo.pages.CarPage;
import com.goibibo.pages.HomePage;
import com.goibibo.pages.HotelPage;

public class HotelPageTest extends BaseUI {

	private WebDriver driver;
	String Time;

	By hotel_button = getLocator("hotel_select_xpath");
	

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}

	@Test
	public void verifyDefaultSection() {
		HomePage homePage = new HomePage(driver);

		Assert.assertTrue(homePage.checkDefaultSection("Flights"));
	}

	@Test(dependsOnMethods = "verifyDefaultSection")
	public void verifyHotelButtonText() {

		HotelPage hotelPage = new HotelPage(driver);

		hotelPage.clickHotelButton();

		Assert.assertEquals("Hotels", getText(hotel_button));

	}

	@Test(dependsOnMethods = "verifyHotelButtonText")
	public void verifyHotelsTitle() {
//		
		HotelPage hotelPage  = new HotelPage(driver); 
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();
		
		waitForDocumentReady(30);
		Assert.assertEquals("Online Hotel Booking | Book Cheap, Budget and Luxury Hotels -Goibibo", hotelPage.getTitle());

	}
	
	@Test(dependsOnMethods = "verifyHotelsTitle")
	public void verifyDropDownNotActivatedTest() {
		HotelPage hotelPage = new HotelPage(driver);

		Assert.assertFalse(hotelPage.isDropdownActivated());
	}
	
	
	@Test(dependsOnMethods = "verifyDropDownNotActivatedTest")
	public void verifyDefaultNumber() {
//		
		HotelPage hotelPage  = new HotelPage(driver); 
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();
		hotelPage.clickGuestButton();
		
		waitForDocumentReady(30);
		
		Assert.assertEquals("2", hotelPage.getNumber());

	}
	
	@Test(dependsOnMethods = "verifyDefaultNumber")
	public void verifyStartingNumber() {
//		
		HotelPage hotelPage  = new HotelPage(driver); 
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();
		hotelPage.clickPreviousButton();
		
		waitForDocumentReady(30);
		
		Assert.assertEquals("1", hotelPage.getNumber());

	}
	
	
	@Test(dependsOnMethods = "verifyStartingNumber")
	public void verifyAlertIsActivatedTest() {
		HotelPage hotelPage = new HotelPage(driver);
		
		hotelPage.clickPreviousButton();
		
//		waitForDocumentReady(30);

		Assert.assertTrue(hotelPage.isAlertActivated());
	}
	
	@Test(dependsOnMethods = "verifyAlertIsActivatedTest")
	public void verifyAlertMessageTest() {
		HotelPage hotelPage = new HotelPage(driver);
		
//		waitForDocumentReady(10);
		
		Assert.assertEquals("Minimum 1 adult is required",hotelPage.getAlertText());
		
	}
	
	
	@Test(dependsOnMethods = "verifyAlertMessageTest")
	public void verifyAlertIsClosedTest() {
		HotelPage hotelPage = new HotelPage(driver);
		
		hotelPage.closeAlert();
		waitForDocumentReady(10);

		Assert.assertFalse(hotelPage.isAlertActivated());
	}
	
	
	
	

	@Test(dependsOnMethods = "verifyAlertIsClosedTest")
	public void verifyAlertWhenNumberIsMax() {
//		
		HotelPage hotelPage = new HotelPage(driver);
//		waitForDocumentReady(200);
//		System.out.println("check");
//		hpage.selectHotelPage();
		System.out.println(hotelPage.extractNumbers());
//		hotelPage.extractNumbers();
//		waitForDocumentReady(30);
//		System.out.println(getText(date_picker_month_text));
		Assert.assertTrue(hotelPage.isAlertActivated());
	}
	
	@Test(dependsOnMethods = "verifyAlertWhenNumberIsMax")
	public void verifyAlertMaxMessageTest() {
		HotelPage hotelPage = new HotelPage(driver);
		
//		waitForDocumentReady(10);
		
		Assert.assertEquals("Max allowed upto 16",hotelPage.getAlertText());
	}
	
	@Test(dependsOnMethods = "verifyAlertMaxMessageTest")
	public void verifyMaxAlertIsClosedTest() {
		HotelPage hotelPage = new HotelPage(driver);
		
		hotelPage.closeAlert();
		waitForDocumentReady(10);

		Assert.assertFalse(hotelPage.isAlertActivated());
	}

}
