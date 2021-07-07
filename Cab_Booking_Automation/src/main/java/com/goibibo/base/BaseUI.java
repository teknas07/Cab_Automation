package com.goibibo.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.goibibo.utils.DateUtils;
import com.goibibo.utils.FileIO;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.tripadvisor.utils.DateUtils;
//import com.tripadvisor.utils.FileIO;

public class BaseUI {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static Properties prop;
	public static String browser_choice;
	public static String timestamp = DateUtils.getTimeStamp();
	public static Logger log;
	private static final Logger logBase = LogManager.getLogger(BaseUI.class);

	public BaseUI() {
		prop = FileIO.initProperties();
	}

	/************** Invoke Browser ****************/
	public static WebDriver invokeBrowser() {
		logBase.debug("Opening browser");
		browser_choice = prop.getProperty("browserName");
		try {
			if (browser_choice.equalsIgnoreCase("chrome")){
				driver = DriverSetup.getChromeDriver();
			} else{
				throw new Exception("Invalid browser name provided in property file");
			}
			logBase.info("Opened browser");
		} catch (Exception e) {
			logBase.error("Failed to open browser: "+e.getMessage());
			reportFail(e.getMessage());
		}

		return driver;
	}


	/************** Open website URL ****************/
	public static void openBrowser(String websiteUrlKey) {
		try {
			logBase.debug("Opening URL");
			driver.get(prop.getProperty(websiteUrlKey));
			logBase.info("Opened URL");
		} catch (Exception e) {
			e.printStackTrace();
			logBase.error("Failed to open URL");
			reportFail(e.getMessage());
		}

	}


	

	/************** Get text of element ****************/
	public static String getText(By locator) {
		String text = null;
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(locator));
			text = driver.findElement(locator).getText();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to get text from element");
			reportFail(e.getMessage());
		}
		return text;
	}

	/************** Click on element with WebElement ****************/
	public static void clickOn(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions
					.elementToBeClickable(locator));
			driver.findElement(locator).click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to click on element");
			reportFail(e.getMessage());
		}
	}

	

	/**************** Get By locator using locator key ****************/
	public static By getLocator(String locatorKey) {
		if (locatorKey.endsWith("_id")) {
			return By.id(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_name")) {
			return (By.name(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_className")) {
			return (By.className(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_xpath")) {
			return (By.xpath(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_css")) {
			return (By.cssSelector(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_linkText")) {
			return (By.linkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_partialLinkText")) {
			return (By.partialLinkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_tagName")) {
			return (By.tagName(prop.getProperty(locatorKey)));
		}
		log.error("Invalid locator key");
		reportFail("Failing test case, Invalid locator key: " + locatorKey);
		return null;
	}

	
	/************** Report fail test ****************/
	public static void reportFail(String reportMessage) {
		logger.log(Status.FAIL, reportMessage);
		Assert.fail("Test case failed: " + reportMessage);
	}

}
