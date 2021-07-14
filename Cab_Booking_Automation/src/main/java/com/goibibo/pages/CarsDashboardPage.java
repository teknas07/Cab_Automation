package com.goibibo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.goibibo.base.BaseUI;

public class CarsDashboardPage extends BaseUI {
	
//	public ExtentTest logger;
	public WebDriver driver;
	
	By car_model = getLocator("car_model_xpath");
	By car_text = getLocator("car_model_text_xpath");
	By car_price = getLocator("price_text_xpath");
	By low_price = getLocator("lowest_price_text_xpath");
	By select_lowest = getLocator("select_low_price_xpath");
	
	public CarsDashboardPage() {
	}

	public CarsDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

//	public CarsDashboardPage(WebDriver driver, ExtentTest logger) {
//		this.driver = driver;
//		this.logger = logger;
//		log=LogManager.getLogger(HomePage.class);
//	}

	/************* Search for car model  **************/
	public void searchCarModel(String model) {
//		log.debug("Clicking on cabs button");
		
//		waitForDocumentReady(30);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,2500)");
//		log.info("Clicked on cabs button");
//		waitForDocumentReady(20);
//		log.debug("Searching for location: "+location);
		List<WebElement> models  = getListOfElements(car_model) ;
//		sendText(from_search_location, from_location);
		for(WebElement el : models) {
//			System.out.println(el.getText());
			if(el.getText().contains(model)) {
//				System.out.println(el.getText());
				el.click();
			}
		}
		
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-2300)");
//		clickOn(from_search_select, 30);
		waitForDocumentReady(20);
//		log.info("Searched for location: "+location);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(+300,+300)");
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	/************* Select lowest car price **************/
	public void selectLowestPrice() {
//		log.debug("Clicking on cabs button");
//		clickOn(from_search_location, 30);
//		log.info("Clicked on cabs button");
		waitForDocumentReady(20);
		
//		List<WebElement> low_price = getListOfElements(select_lowest);
		driver.findElement(select_lowest).click();
//		low_price.get(0).click();
//		log.debug("Searching for location: "+location);
//		sendText(from_search_location, from_location);
//		clickOn(from_search_select, 30);
//		log.info("Searched for location: "+location);
//		logger.log(Status.INFO, "Searched location for holiday homes");
	}
	
	/************* Check if car model is activated *************/
	public boolean isCarModelActivated(String model) {
		boolean result=true;
//		log.debug("Checking if ship dropdown is activated");
		List<WebElement> models  = getListOfElements(car_text) ;
//		models.get(2).isSelected();
//		System.out.println(models.get(3).isSelected());
//		driver.findElement(one_way).isSelected();
		for(WebElement m : models) {
			if(!m.getText().equalsIgnoreCase(model)) {
				result = false;
			}
		}
//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}
	
	/************* Check if car price is sorted *************/
	public boolean isCarPriceSorted() {
		boolean result=true;
//		log.debug("Checking if ship dropdown is activated");
		List<WebElement> models  = getListOfElements(car_price) ;
//		models.get(2).isSelected();
//		System.out.println(models.get(3).isSelected());
//		driver.findElement(one_way).isSelected();
		List<Integer> priceList = getSubString(models);
		
		int small = priceList.get(0);
		
		for(int i=1;i<priceList.size();i++) {
			if(small>priceList.get(i)) {
				result = false;
			}
		}


//		logger.log(Status.INFO, "Ship drop down is activated: " + result);
//		log.info("Ship drop down is activated: " + result);
		return result;
	}
	
	/************* Get substring for cab's price *************/
	public List<Integer> getSubString(List<WebElement> models){
	
		List<Integer> list = new ArrayList<>();
		for(WebElement m : models) {
			list.add(Integer.parseInt(m.getText().substring(1)));
		}
		
		return list;
		
	}
	
	/************* Get Page title *************/
	public String getTitle() {
		return driver.getTitle();
	}
	
	
	/************* Get lowest cab price *************/
	public String getLowCabPrice() {
		return getText(low_price).substring(1);
	}
	

}
