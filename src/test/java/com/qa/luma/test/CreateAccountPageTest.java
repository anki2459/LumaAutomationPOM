package com.qa.luma.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ExcelUtil;

public class CreateAccountPageTest extends BaseTest{
	@BeforeClass
	public void createaccountPageSetup()
	{
		cp=lp.goToCreateAccountPage();
	}
	public String getRandomEmail()
	{
		Random randomGenerator = new Random();
		String email = "septautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getAccountTestData()
	{
		return ExcelUtil.getTestData(Constants.CREATE_ACCOUNT_SHEET_NAME);
	}
	@Test(dataProvider = "getAccountTestData")
	public void createAccountTest(String fName,String lName,String pass,String subscribe)
	{
		//Assert.assertTrue(cp.docreateAccount("Ankush", "Gupta", "test_!1@gmail.com", "Test@123","yes"));
		Assert.assertTrue(cp.docreateAccount(fName,lName,getRandomEmail(),pass,subscribe));
	}
}
