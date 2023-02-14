package com.qa.luma.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.luma.utils.ExcelUtil;

public class SearchResultsPageTest extends BaseTest{
	@BeforeClass
	public void accountsPageSetup()
	{
		ap =lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] productData()
	{
		return ExcelUtil.getTestData("accounts");
	}
	@Test(priority = 1,dataProvider ="productData")
	public void selectProductTest(String productName,String selectProductName)
	{
		sp = ap.doSearch(productName);
		pip = sp.selectProduct(selectProductName);
		Assert.assertEquals(pip.getProductHeader(), selectProductName);}

}
