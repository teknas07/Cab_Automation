package com.goibibo.pages;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

import com.goibibo.base.BaseUI;

public class LowestCarPricePage extends BaseUI {

//	public ExtentTest logger;
	public WebDriver driver;

	By hotel_location = getLocator("hotel_select_xpath");
	

	public LowestCarPricePage() {
	}

	public LowestCarPricePage(WebDriver driver) {
		this.driver = driver;
	}

//	public LowestCarPricePage(WebDriver driver, ExtentTest logger) {
//		this.driver = driver;
//		this.logger = logger;
//		log=LogManager.getLogger(HomePage.class);
//	}

	/************* Select hotels page **************/
	public void selectHotelPage() {
//		log.debug("Clicking on cabs button");
//		clickOn(from_search_location, 30);
//		log.info("Clicked on cabs button");

		clickOn(hotel_location, 30);

//		log.debug("Searching for location: "+location);
//		sendText(from_search_location, from_location);
//		clickOn(from_search_select, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
}
