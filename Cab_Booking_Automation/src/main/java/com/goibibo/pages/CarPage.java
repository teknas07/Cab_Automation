package com.goibibo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
import com.goibibo.base.BaseUI;

public class CarPage extends BaseUI {

//	public ExtentTest logger;
	public WebDriver driver;
	public String text;

	By one_way = getLocator("one_way_xpath");

	By from_search_location = getLocator("from_xpath");
	By from_search_select = getLocator("from_select_xpath");
	By from_text = getLocator("from_text_xpath");
	By to_search_location = getLocator("to_xpath");
	By to_search_select = getLocator("to_select_xpath");

	By date_picker_location = getLocator("date_picker_xpath");
	By date_picker_month_text = getLocator("date_picker_text_xpath");
	By date_picker_next_button = getLocator("date_click_next_xpath");
	By date_picker_day_text = getLocator("dates_xpath");

	By date_text = getLocator("date_text_xpath");

	By time_picker_location = getLocator("time_button_xpath");
	By time_picker_text = getLocator("list_times_xpath");

	By time_text = getLocator("time_text_xpath");

	By search_cabs = getLocator("search_cabs_xpath");

	public CarPage() {
	}

	public CarPage(WebDriver driver) {
		this.driver = driver;
	}

//	public CarPage(WebDriver driver, ExtentTest logger) {
//		this.driver = driver;
//		this.logger = logger;
//		log=LogManager.getLogger(HomePage.class);
//	}

	/************* Search for source location **************/
	public void searchSourceLocation(String from_location) {
//		log.debug("Clicking on cabs button");
		clickOn(from_search_location, 30);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
		sendText(from_search_location, from_location);

		clickOn(from_search_select, 30);

//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Search for destination location **************/
	public void searchDestinationLocation(String to_location) {
//		log.debug("Clicking on cabs button");
		clickOn(to_search_location, 30);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
		sendText(to_search_location, to_location);
		clickOn(to_search_select, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Search for Pick up Date **************/
	public void searchPickUpDate(String month, String day) {
//		log.debug("Clicking on cabs button");
		clickOn(date_picker_location, 30);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
		while (!getText(date_picker_month_text).contains(month)) {
			clickOn(date_picker_next_button, 5);
		}
		List<WebElement> dates = getListOfElements(date_picker_day_text);
		for (int i = 0; i < dates.size(); i++) {
			if (dates.get(i).getText().equalsIgnoreCase(day)) {
				dates.get(i).click();
				break;
			}

		}

//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Search for Pick up Time **************/
	public void searchPickUpTime(String time) {
//		log.debug("Clicking on cabs button");
		clickOn(time_picker_location, 30);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
		List<WebElement> times = getListOfElements(time_picker_text);
		for (int i = 0; i < times.size(); i++) {
//			times.get(i).getText();
			if (times.get(i).getText().equalsIgnoreCase(time)) {
				times.get(i).click();
				break;
			}
		}

//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Search cabs **************/
	public void searchCabs() {
//		log.debug("Clicking on cabs button");
//		System.out.println(driver.findElement(getLocator("search_cabs")));
		clickOn(search_cabs, 20);
//		log.info("Clicked on cabs button");
//		log.debug("Searching for location: "+location);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Check if dropdown is activated *************/
	public boolean isDropdownActivated() {
		boolean result;
//		log.debug("Checking if ship dropdown is activated");
		clickOn(from_search_location, 10);
		if (isElementPresent(from_search_select, 1))
			result = true;
		else
			result = false;
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}

	/************* Check if one-way is activated *************/
	public boolean isOneWayActivated() {
		boolean result;
//		log.debug("Checking if ship dropdown is activated");
//		System.out.println(driver.findElement(one_way).isSelected());
//		driver.findElement(one_way).isSelected();
		if (driver.findElement(one_way).isSelected())
			result = true;
		else
			result = false;
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}

	/************* Check if given date is correct *************/
	public boolean checkGivenDate(String day) {
		boolean result;

		String dt = getText(date_text);
//		System.out.println(dt.substring(0,2));

		if (dt.substring(0, 2).equalsIgnoreCase(day))
			result = true;
		else
			result = false;

		return result;
	}

	/************* Check if given month is correct *************/
	public boolean checkGivenMonth(String month) {
		boolean result;

		String dt = getText(date_text);
//		System.out.println(dt.substring(3,6));

		if (month.contains(dt.substring(3, 6))) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/************* Check if given time is correct *************/
	public boolean checkGivenTime(String time) {
		boolean result;

		if (time.equalsIgnoreCase(getText(time_text))) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/************* Get Source Text *************/
	public String getSourceText() {
		return driver.findElement(from_search_location).getAttribute("value");
	}
	
	/************* Get Destination Text *************/
	public String getDestinationText() {
		return driver.findElement(to_search_location).getAttribute("value");
	}

}
