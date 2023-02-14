package com.qa.luma.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.luma.utils.Constants;

public class LoginPageTest extends BaseTest {
	@Test(priority = 1)
	public void getLoginPageTitleTest()
	{
		Assert.assertTrue(lp.getLoginPageTitle());
	}
	@Test(priority = 2)
	public void getPageHeaderTextTest()
	{
		String actHeader = lp.getPageHeaderText();
		Assert.assertEquals(Constants.LOGIN_PAGE_HEADER,actHeader);
		
	}
	@Test(priority =3)
	public void doLoginPageTest()
	{
		ap=lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(ap.getAccountsPageHeaderText(),Constants.ACCOUNTS_PAGE_HEADER);
	}

}
