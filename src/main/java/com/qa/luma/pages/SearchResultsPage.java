package com.qa.luma.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.luma.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver ;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private By productResults = By.cssSelector("a.product-item-link");
	
	
	public int getProductListCount()
	{
	  int resultsCount = eleUtil.waitForElementsToBeVisible(productResults,10).size();
	  System.out.println("The search productcount is: "+resultsCount);
	  return resultsCount;
	}
	public ProductInfoPage selectProduct(String requiredProductName)
	{
		System.out.println("The required product name is :"+requiredProductName);
		List<WebElement> prodResultList = eleUtil.getElements(productResults);
		for(WebElement e:prodResultList)
		{
			String text= e.getText();
			if(text.equalsIgnoreCase(requiredProductName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
