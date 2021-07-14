package com.goibibo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.goibibo.base.BaseUI;

public class HotelPage extends BaseUI {

//	public ExtentTest logger;
	public WebDriver driver;

	By hotel_button = getLocator("hotel_select_xpath");
	By guest_room_location = getLocator("guest_rooms_xpath");
	By guest_section = getLocator("guest_section_xpath");
	By guest_number_text = getLocator("adult_number_xpath");
	By click_next = getLocator("click_next_xpath");
	By click_prev_xpath = getLocator("click_prev_xpath");

	public HotelPage() {
	}

	public HotelPage(WebDriver driver) {
		this.driver = driver;
	}

//	public HotelPage(WebDriver driver, ExtentTest logger) {
//		this.driver = driver;
//		this.logger = logger;
//		log=LogManager.getLogger(HomePage.class);
//	}

	/************* Clicking on hotel section **************/
	public void clickHotelButton() {
//		log.debug("Clicking on hotel button");
		clickOn(hotel_button, 20);
//		log.info("Clicked on hotel button");
//		log.debug("Searching for location: "+location);
//		sendText(search_textbox, location);
//		clickOn(search_location, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}

	/************* Extract all the numbers **************/
	public List<String> extractNumbers() {
//		log.debug("Clicking on cabs button");
//		clickOn(from_search_location, 30);
//		log.info("Clicked on cabs button");

//		clickOn(guest_room_location, 30);
//
//		waitForDocumentReady(30);
//
//		clickOn(click_prev_xpath, 30);
//
//		waitForDocumentReady(30);

		List<String> number = new ArrayList<>();

		while (true) {
			number.add(getText(guest_number_text));
			clickOn(click_next, 30);

			try {
//				WebDriverWait wait = new WebDriverWait(driver, 2);
//				wait.until(ExpectedConditions.alertIsPresent());
//				Alert alert = driver.switchTo().alert();
//			    System.out.println(alert.getText());
				if (isAlertActivated()) {
					break;
				}
			} catch (Exception e) {
				
			}
//			driver.switchTo().alert().accept();
		}

		return number;
//		log.debug("Searching for location: "+location);
//		sendText(from_search_location, from_location);
//		clickOn(from_search_select, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	/************* Clicking on guest room section **************/
	public void clickGuestButton() {
//		log.debug("Clicking on hotel button");
		clickOn(guest_room_location, 30);
//		log.info("Clicked on hotel button");
//		log.debug("Searching for location: "+location);
//		sendText(search_textbox, location);
//		clickOn(search_location, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	/************* Check if dropdown is activated *************/
	public boolean isDropdownActivated() {
		boolean result;
//		log.debug("Checking if ship dropdown is activated");
//		clickOn(guest_room_location, 10);
		if (isElementPresent(guest_section, 1))
			result = true;
		else
			result = false;
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}
	
	/************* Clicking on previous button **************/
	public void clickPreviousButton() {
//		log.debug("Clicking on hotel button");
		clickOn(click_prev_xpath, 30);
//		log.info("Clicked on hotel button");
//		log.debug("Searching for location: "+location);
//		sendText(search_textbox, location);
//		clickOn(search_location, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	/************* Check if alert is activated *************/
	public boolean isAlertActivated() {
		boolean result;
//		log.debug("Checking if ship dropdown is activated");
//		clickOn(guest_room_location, 10);
		if (isAlertPresent(1))
			result = true;
		else
			result = false;
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}
	
	/************* Close alert *************/
	public void closeAlert() {
		
//		log.debug("Checking if ship dropdown is activated");
//		clickOn(guest_room_location, 10);
		Alert alert = driver.switchTo().alert();
		alert.accept();
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		
	}
	
	/************* Get Alert text *************/
	public String getAlertText() {
		
//		log.debug("Checking if ship dropdown is activated");
//		clickOn(guest_room_location, 10);
		Alert alert = driver.switchTo().alert();
	    String text = alert.getText();
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return text;
	}
	
	

	/************* Get Page title **************/
	public String getTitle() {
		return driver.getTitle();
	}
	
	/************* Get adults number text **************/
	public String getNumber() {
		return getText(guest_number_text);
	}
}
