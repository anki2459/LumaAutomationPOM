package com.qa.luma.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider
	public Object[][] loginWrongTestData()
	{
		return new Object[][] {
			{"test11@gmail.com","Test@123"},
			{"test11@gmail.com","Test@123"},
			{" ","Test@123"},
			{"@@@#@gmail.com","Test@123"},	
			{" "," "}
		};
	}
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String un,String pwd)
	{
		Assert.assertFalse(lp.doLoginWithIncorrectCreds(un, pwd));
	}

}
