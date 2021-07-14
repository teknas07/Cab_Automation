package com.goibibo.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//import org.apache.poi.hpsf.Date;
//import org.testng.annotations.Test;
//import org.testng.annotations.BeforeMethod;
//import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.goibibo.base.BaseUI;
import com.goibibo.pages.CarPage;
import com.goibibo.pages.CarsDashboardPage;
//import com.goibibo.pages.CarsDashboardPage;
import com.goibibo.pages.HomePage;
//import com.goibibo.pages.HotelPage;
//import com.goibibo.pages.LowestCarPricePage;
import com.goibibo.utils.FileIO;

public class CabPageTest extends BaseUI {

	private WebDriver driver;
	String Time = prop.getProperty("time_data");
	String cabModel = prop.getProperty("cab_data");

	By cab_button = getLocator("cab_xpath");

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
	public void verifyCabButtonText() {

		HomePage homePage = new HomePage(driver);

		homePage.clickCabsButton();

		Assert.assertEquals("Cabs", getText(cab_button));

	}

	@Test(dependsOnMethods = "verifyCabButtonText")
	public void verifyOneWaySelected() {
		CarPage cp = new CarPage(driver);

		Assert.assertTrue(cp.isOneWayActivated());

	}

//	@Test(dependsOnMethods = "verifyOneWaySelected", dataProvider = "travelData")
//	public void travelDetailsTest(String From, String To, String Month, String Day, String hr, String min, String am) {
//		CarPage cp = new CarPage(driver);
//
//		cp.searchSourceLocation(From);
////		waitForDocumentReady(30);
//
//		cp.searchDestinationLocation(To);
////		waitForDocumentReady(30);
//
//		cp.searchPickUpDate(Month, Day);
////		waitForDocumentReady(30);
//
//		Time = "0" + hr + ":" + min + " " + am;
//		cp.searchPickUpTime(Time);
////		waitForDocumentReady(30);
//
//	}

	@Test(dependsOnMethods = "verifyOneWaySelected")
	public void verifyFromLocation() {

		CarPage cp = new CarPage(driver);
		
		cp.searchSourceLocation(prop.getProperty("from_data"));
		
		waitForDocumentReady(30);
//		cp.searchSourceLocation("Delhi, India");

//		System.out.println(cp.isSourceLocationActivated());
		Assert.assertEquals("Delhi, India",cp.getSourceText());

//		cp.isLineSelected();
	}

	@Test(dependsOnMethods = "verifyFromLocation")
	public void verifyDropDownNotActivatedTest() {
		CarPage cPage = new CarPage(driver);

		cPage.isOneWayActivated();
		Assert.assertFalse(cPage.isDropdownActivated());
	}

	@Test(dependsOnMethods = "verifyDropDownNotActivatedTest")
	public void verifyDestinationLocation() {
		
		CarPage cp  = new CarPage(driver); 
		cp.searchDestinationLocation(prop.getProperty("to_data"));
//		cp.searchDestinationLocation("Manali, Himachal");
		waitForDocumentReady(30);
//		System.out.println(getText(from_search_location));
		Assert.assertEquals("Manali, Himachal Pradesh, India",cp.getDestinationText());
	}

	@Test(dependsOnMethods = "verifyDestinationLocation")
	public void verifyPickUpDate() {

		CarPage cp = new CarPage(driver);

//		cp.searchPickUpDate("August", "20");
		cp.searchPickUpDate(prop.getProperty("month_data"), prop.getProperty("day_data"));
		waitForDocumentReady(30);
//		System.out.println(getText(date_picker_month_text));
		Assert.assertTrue(cp.checkGivenDate("20"));
	}

	@Test(dependsOnMethods = "verifyPickUpDate")
	public void verifyPickUpMonth() {

		CarPage cp = new CarPage(driver);

//		cp.searchPickUpDate("August", "20");

//		waitForDocumentReady(30);
//		System.out.println(getText(date_picker_month_text));
		Assert.assertTrue(cp.checkGivenMonth("August"));
	}

	@Test(dependsOnMethods = "verifyPickUpMonth")
	public void verifyPickUpTime() {

		CarPage cp = new CarPage(driver);

		cp.searchPickUpTime(Time);
//		cp.searchCabs();
		waitForDocumentReady(30);
//		System.out.println(getText(date_picker_month_text));
		Assert.assertTrue(cp.checkGivenTime(Time));
	}

	@Test(dependsOnMethods = "verifyPickUpTime")
	public void verifySearchCabs() {
//		
		CarPage cp = new CarPage(driver);
//		waitForDocumentReady(20);
//		System.out.println("check");
		cp.searchCabs();

		waitForDocumentReady(30);

	}

	@Test(dependsOnMethods = "verifySearchCabs")
	public void verifyCarModelIsSelected() {
		CarsDashboardPage carDash = new CarsDashboardPage(driver);

		Assert.assertFalse(carDash.isCarModelActivated(cabModel));

	}

//	
	@Test(dependsOnMethods = "verifyCarModelIsSelected")
	public void cabsModelTest() {
//		
		CarsDashboardPage carDash = new CarsDashboardPage(driver);
//		waitForDocumentReady(20);
//		System.out.println("check");
		carDash.searchCarModel(cabModel);
		waitForDocumentReady(30);
//		System.out.println(carDash.isCarModelActivated());
		Assert.assertTrue(carDash.isCarModelActivated(cabModel));
	}

	@Test(dependsOnMethods = "cabsModelTest")
	public void verifyLowPrice() {
//		
		CarsDashboardPage carDash = new CarsDashboardPage(driver);
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();

		waitForDocumentReady(30);

		Assert.assertTrue(carDash.isCarPriceSorted());
	}

	@Test(dependsOnMethods = "verifyLowPrice")
	public void verifyCabsTitle() {
//		
		CarsDashboardPage  carDash  = new CarsDashboardPage(driver); 
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();
		
		waitForDocumentReady(30);
//		System.out.println(carDash.getTitle());
		Assert.assertEquals("Cab Booking - Outstation cabs, Car Rental, Taxi &amp; Cars Booking", carDash.getTitle());
//		Assert.assertTrue(carDash.isCarPriceSorted());
	}

	@Test(dependsOnMethods = "verifyCabsTitle")
	public void LowestPriceTest() {
//		
		CarsDashboardPage carDash = new CarsDashboardPage(driver);
//		waitForDocumentReady(20);
//		System.out.println("check");
//		carDash.isCarPriceSorted();

		waitForDocumentReady(30);
		carDash.selectLowestPrice();
//		Assert.assertTrue(carDash.isCarPriceSorted());
	}

	@Test(dependsOnMethods = "LowestPriceTest")
	public void displayModelAndPrice() {
		CarsDashboardPage carDash = new CarsDashboardPage(driver);

		System.out.println("Cab Model : " + cabModel + ", Price : Rs." + carDash.getLowCabPrice());
	}

//	/**********************************************
//	 ******* Data Provider for travel data ********
//	 **********************************************/
//	@DataProvider
//	public Object[][] travelData() throws IOException {
//		HashMap<String, ArrayList<String>> dataMap = FileIO.readExcelData(0);
//		int noRow = dataMap.size();
//		int noCol = dataMap.get("1").size();
//		Object[][] data = new Object[noRow][noCol];
//		for (int i = 0; i < noRow; ++i) {
//			ArrayList<String> rowData = dataMap.get("" + (i + 1));
//			for (int j = 0; j < noCol; ++j) {
//				data[i][j] = rowData.get(j);
//			}
//		}
//		System.out.println(data);
//		return data;
//	}

//	/**********************************************
//	 ******* Data Provider for car data ********
//	 **********************************************/
//	@DataProvider
//	public Object[][] carData() throws IOException {
////		System.out.println( FileIO.readExcelData("CarDetails"));
//		HashMap<String, ArrayList<String>> dataMap = FileIO.readExcelData(1);
//		int noRow = dataMap.size();
//		int noCol = dataMap.get("1").size();
//		Object[][] data = new Object[noRow][noCol];
//		for (int i = 0; i < noRow; ++i) {
//			ArrayList<String> rowData = dataMap.get("" + (i + 1));
//			for (int j = 0; j < noCol; ++j) {
//				data[i][j] = rowData.get(j);
//			}
//		}
//		return data;
//	}

}
