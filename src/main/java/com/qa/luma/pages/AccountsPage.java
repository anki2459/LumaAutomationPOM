package com.qa.luma.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ElementUtil;

public class AccountsPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private By pageTitle = By.xpath("//h1");
	private By searchBar = By.id("search");
	private By searchButton = By.xpath("//button[@title='Search']");

	private By accSection = By.xpath("//strong[@class='box-title']");
	
	public boolean getAccountsPageTitle() {
		return eleUtil.waitForTitleContains(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}
	public String getAccountsPageHeaderText() {
		return eleUtil.doGetText(pageTitle);
	} 
	public ArrayList<String> getAccountsSections()
	{
		List<WebElement> accSectList = eleUtil.getElements(accSection);
		ArrayList<String> accSections = new ArrayList<String>();
		for(WebElement e:accSectList)
		{
			String text = e.getText();
			accSections.add(text);
		}
		return accSections;
	}
	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("Searching for product :"+productName);
		eleUtil.doSendKeys(searchBar,productName);
		eleUtil.doActionsMoveToElementClick(searchButton);
	  //  eleUtil.doClick(searchButton,10);
	 return new SearchResultsPage(driver);
	}

}
