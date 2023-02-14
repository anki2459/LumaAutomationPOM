package com.qa.luma.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void productInfoSetup()
	{
		ap =lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] productData()
	{
		return ExcelUtil.getTestData("accounts");
	}
	@Test(priority=1,dataProvider ="productData")
	public void ProductHeaderTest(String productName,String selectProductName)
	{
		sp = ap.doSearch(productName);
		pip = sp.selectProduct(selectProductName);
		Assert.assertEquals(pip.getProductHeader(), selectProductName);
		
	}
	@Test(priority=2,dataProvider ="productData")
	public void ImagesCountTest(String productName,String selectProductName)
	{
		sp = ap.doSearch(productName);
		pip = sp.selectProduct(selectProductName);
		Assert.assertEquals(pip.getImagesCount(),Constants.TEES_COUNT);
	}
	@DataProvider
	public Object[][] productInfoData()
	{
		return ExcelUtil.getTestData("productInfo");
	}
	@Test(priority=3,dataProvider ="productInfoData")
	public void ProductInfoTest(String productName,String selectProductName,
			                    String style,String material,String pattern)
	{
		sp = ap.doSearch(productName);
		pip = sp.selectProduct(selectProductName);
		Map<String,String> actProdInfoMap = pip.getProductInfo();
		actProdInfoMap.forEach((k,v) -> System.out.println(k + ":"+v));
		softAssert.assertEquals(actProdInfoMap.get("Style"),style.toString());
		softAssert.assertEquals(actProdInfoMap.get("Material"),material.toString());
		softAssert.assertEquals(actProdInfoMap.get("Pattern"),pattern.toString());
		softAssert.assertAll();
	}
	@Test(priority=4,dataProvider ="productData")
	public void addToCartTest(String productName,String selectProductName)
	{
		sp = ap.doSearch(productName);
		pip = sp.selectProduct(selectProductName);		
		Assert.assertTrue(pip.addToCart());
	}
}
