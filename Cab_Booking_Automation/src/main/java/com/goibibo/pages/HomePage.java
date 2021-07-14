package com.goibibo.pages;

//import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.ExtentTest;
//import com.goibibo.base.BaseUI;
//import com.aventstack.extentreports.Status;
import com.goibibo.base.BaseUI;

public class HomePage extends BaseUI {

//	public ExtentTest logger;
	public WebDriver driver;
	
	By cab_button = getLocator("cab_xpath");
	By default_section = getLocator("flight_xpath");
//	By search_location = getLocator("searchLocation_xpath");
	
	public HomePage() {
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

//	public HomePage(WebDriver driver, ExtentTest logger) {
//		this.driver = driver;
//		this.logger = logger;
//		log=LogManager.getLogger(HomePage.class);
//	}

	/************* Clicking on cabs section  **************/
	public void clickCabsButton() {
//		log.debug("Clicking on cabs button");
		clickOn(cab_button, 20);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
//		sendText(search_textbox, location);
//		clickOn(search_location, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	
	/************* Check the default section *************/
	public boolean checkDefaultSection(String defaultText) {
		boolean result;
//		log.debug("Checking if line is selected");
		if (getText(default_section).equals(defaultText))
			result = true;
		else
			result = false;
//		logger.log(Status.INFO, "Ship '" + cruiseLine + "' is selected: "
//				+ result);
//		log.info("Ship '" + cruiseLine + "' is selected: "
//				+ result);
		return result;

	}
	
	
}
