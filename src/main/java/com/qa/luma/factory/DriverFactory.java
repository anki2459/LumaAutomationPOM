package com.qa.luma.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver ;
	public OptionsManager optionsManager; 
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method initilizes Webdriver
	 * @param prop
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop)
	{
		String browserName= prop.getProperty("browser");
		System.out.println("The browser is: "+browserName);
		highlight = prop.getProperty("highlight");
		//initialize to prevent null pointer
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please enter correct browser name");
			break;
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	/**
	 * This will return thread local copy of WebDriver 
	 */
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	/**
	 * This method initilizes properties
	 * @return this will return properties prop reference
	 */
	public Properties init_prop()
	{
		Properties prop = new Properties();
		FileInputStream ip =null;
		String envName = System.getProperty("env");
		
		if(envName == null)
		{
			System.out.println("Running on PROD env: ");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();}
		}
		else{
			System.out.println("Running on env: "+envName);
			try {
		switch (envName) {
		case "qa":
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
		case "dev":
			ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
		case "stage":
			ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;
		case "uat":
			ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
			break;
		default:
			System.out.println("Please enter Valid Environment variable");
			break;
		}
			}catch (FileNotFoundException e) {
			e.printStackTrace();}
	}
try {
	prop.load(ip);
} catch (IOException e) {
	e.printStackTrace();
}
		return prop;    
	}
}
