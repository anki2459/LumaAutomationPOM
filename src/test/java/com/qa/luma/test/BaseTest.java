package com.qa.luma.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.luma.factory.DriverFactory;
import com.qa.luma.factory.OptionsManager;
import com.qa.luma.pages.AccountsPage;
import com.qa.luma.pages.CreateAccountPage;
import com.qa.luma.pages.LoginPage;
import com.qa.luma.pages.ProductInfoPage;
import com.qa.luma.pages.SearchResultsPage;

public class BaseTest {
	DriverFactory df ;
	Properties prop ;
	WebDriver driver;
	LoginPage lp;
	CreateAccountPage cp;
	AccountsPage ap;
	SearchResultsPage sp;
	ProductInfoPage pip;
	SoftAssert softAssert;	
	@BeforeTest
	public  void setup()
	{
       df = new DriverFactory();
       prop = df.init_prop();
       driver = df.init_driver(prop);
       lp = new LoginPage(driver);
       softAssert = new SoftAssert();
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
