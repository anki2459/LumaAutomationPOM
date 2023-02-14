package com.qa.luma.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.luma.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver ;
	private ElementUtil eleUtil;
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);

	}
private By productHeader = By.xpath("//div[@class='product-info-main']//h1");
private By imagesLink    = By.cssSelector("div.fotorama__nav__frame");
private By moreInfoLink  = By.xpath("//div[@id='tab-label-additional']");
private By productMetaData = By.xpath("//table[@id='product-attribute-specs-table']/tbody/tr");
private By quantity        = By.id("qty");
private By size = By.xpath("//div[text()='M']");
private By color = By.xpath("//div[@option-label='Green']");
private By addToCartButton = By.id("product-addtocart-button");
private By addsuccess = By.xpath("//div[@class='page messages']");

private Map<String,String> productInfoMap;

public String getProductHeader()
{
	String header = eleUtil.doGetText(productHeader);
	System.out.println("Product header is : "+header);
	return header;
}

public int getImagesCount()
{
	return eleUtil.waitForElementsToBeVisible(imagesLink,10).size();
}
public Map<String, String> getProductInfo()
{
	eleUtil.doClick(moreInfoLink,10);
	productInfoMap = new HashMap<String,String>();
	productInfoMap.put("Name",getProductHeader());
	getProductMetaData();
	return productInfoMap;
}
private void getProductMetaData()
{
	List<WebElement> metaList = eleUtil.getElements(productMetaData);
	for(WebElement e:metaList)
	{
		String text=e.getText();
		String []meta=text.split(" ");
		String metaKey = meta[0].trim();
		String metaValue =meta[1].trim();
		productInfoMap.put(metaKey, metaValue);
		
	}
	
}
public boolean addToCart()
{
	eleUtil.doClick(size, 10);
	eleUtil.doClick(color);
	eleUtil.doClick(quantity);
	eleUtil.doActionsMoveToElementClick(addToCartButton);
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	String successmsg = eleUtil.doGetText(addsuccess);
	if(successmsg.contains("You added"))
	{
	 return true;
	}
	return false;
}
}
