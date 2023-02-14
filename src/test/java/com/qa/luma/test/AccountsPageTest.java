package com.qa.luma.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ExcelUtil;


public class AccountsPageTest extends BaseTest
{
	@BeforeClass
	public void accountsPageSetup()
	{
		ap =lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test(priority =1)
	public void accPageTitleTest() {
		Assert.assertTrue(ap.getAccountsPageTitle());
	}
	@Test(priority =2)
	public void accPageHeaderTextTest() {
		Assert.assertEquals(ap.getAccountsPageHeaderText(),Constants.ACCOUNTS_PAGE_HEADER);

	} 
	@Test(priority =3)
	public void accountsPageSectionsTest() {
      ArrayList<String> actualAccSecList = ap.getAccountsSections();
      Assert.assertEquals(actualAccSecList, Constants.getExpectedAccountsSectionList());
	} 
	@DataProvider
	public Object[][] productData()
	{
		return ExcelUtil.getTestData("accounts");
	}
//		return new Object[][] {
//			{"jackets"},
//			{"tees"},
//			{"tanks"}
//		};
	
	@Test(priority =4,dataProvider ="productData")
	public void accPageSearchTest(String productName,String selectProductName) {
		sp = ap.doSearch(productName);
		Assert.assertTrue(sp.getProductListCount()>0);
	} 
}
