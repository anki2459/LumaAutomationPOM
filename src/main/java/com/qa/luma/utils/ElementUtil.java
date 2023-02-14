package com.qa.luma.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.luma.factory.DriverFactory;

public class ElementUtil 
{
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	//1 arg constructor : create to solve null pointer ref issue of driver
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
		
	}
	public By getBy(String locatorType,String locatorvalue)
	{
		By locator=null;
		switch(locatorType.toLowerCase())
		{
		case "id" :
					locator = By.id(locatorvalue);
					break;
		case "name" :
					locator = By.name(locatorvalue);
					break;
		case "classname" :
					locator = By.className(locatorvalue);
					break;
		case "xpath" :
					locator = By.xpath(locatorvalue);
					break;
		case "cssselector" :
					locator = By.cssSelector(locatorvalue);
					break;
		case "linktext" :
					locator = By.linkText(locatorvalue);
					break;
		case "partiallinktext" :
					locator = By.partialLinkText(locatorvalue);
					break;
		case "tagname" :
					locator = By.tagName(locatorvalue);
					break;
		default :
					System.out.println("Please enter valid locator...");
					break;
		}
		return locator;
	}
	public WebElement getElement(By locator)
	{
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
		return element;
	}
	public WebElement getElement(By locator,int timeOut)
	{
		return doPresenceOfElementLocated(locator, timeOut);
	}
	public WebElement getElement(String locatorType,String locatorvalue)
	{
		return driver.findElement(getBy(locatorType,locatorvalue));
	}
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	public void doClear(By locator)
	{
		driver.findElement(locator).clear();
	}
	public void doSendKeys(By locator,String value)
	{
		doClear(locator);
		getElement(locator).sendKeys(value);
	}
	public void doSendKeys(String locatorType,String locatorvalue,String value)
	{
		 getElement(getBy(locatorType,locatorvalue)).sendKeys(value);
	}
	public void doSendKeys(By locator,String value,int timeOut)
	{
		doPresenceOfElementLocated(locator,timeOut).sendKeys(value);
	}
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	public void doClick(String locatorType,String locatorvalue)
	{
		 getElement(getBy(locatorType,locatorvalue)).click();
	}
	public void doClick(By locator,int timeOut)
	{
		doPresenceOfElementLocated(locator,timeOut).click();
	}
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	public String getAttributeValue(By locator,String attrName)
	{
		 String attrVal =  getElement(locator).getAttribute(attrName);
		 System.out.println(attrVal);
		 return attrVal;
	}
	public boolean doIsDisplayed(By locator)
	{
		return driver.findElement(locator).isDisplayed();
	}
	public  boolean isElementExists(By locator)
	{
		int elementCount = getElementsCount(locator);
		System.out.println("total elements found " +elementCount);
			if(elementCount >= 1) {
				System.out.println("Element is found.."+ locator);
				return true;
			}
			else {
				System.out.println("Element is not found.."+ locator);
				return false;
			}
	}
	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	public List<String> getElementsTextList(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> textList = new ArrayList<String>();
		for(WebElement e:eleList)
		{
			if(!e.getText().isEmpty())
			{
				textList.add(e.getText());
			}
		}
		return textList;
	  
	}
	public List<String> getAttributeList(By locator,String attrName)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for(WebElement e:eleList)
		{
			String attrValue = e.getAttribute(attrName);
			attrList.add(attrValue);
		}
		return attrList;
	}
	public void printElementValue(List<String> eleList)
	{
		 for(String e:eleList)
		 {
			 System.out.println(e);
		 }
	}
	/************************* DropDown Utils **************************/
	public void doDropDownSelectByIndex(By locator,int index)
	{
		Select select = new Select(getElement(locator));
		
		select.selectByIndex(index);
	}
	public void doDropDownSelectByVisibleText(By locator,String text)
	{
		Select select = new Select(getElement(locator));
		
		select.selectByVisibleText(text);
	}
	public void doDropDownSelectByValue(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		
		select.selectByValue(value);
	}
	public void doSelectDropDownValue(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		
		for(WebElement e:optionsList )
		{
			String countryName = e.getText();
			System.out.println(countryName);
			if(countryName.equals(value))
			{
				e.click();
				break;
			}
		}
		
	}
	public void selectDropDownValueWithoutSelect(By locator,String value)
	{
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		for(WebElement e:optionsList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	/******************************** Link Utils *************************************/
	public List<String> getLinksTextList(By locator)
	{ 
		List<WebElement> linksList = getElements(locator);
		List<String> linksTextLink = new ArrayList<String>();
		System.out.println(linksTextLink.size());
		for(WebElement e:linksList)
			{
			String text = e.getText().trim();
			linksTextLink.add(text);
			}
		return linksTextLink;
		
	}
	public void clickOnElementFromSection(By locator,String value)
	{
		List<WebElement> languageList = getElements(locator);
		for(WebElement e:languageList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	/******************************** WebTable Util **********************************************/
	public void printTable(By rowLocator,By colLocator,String xpath_first,String xpath_last )
	{
		int rowcount = driver.findElements(rowLocator).size();
		int colcount = driver.findElements(colLocator).size();
		for(int row = 2;row<=rowcount;row++)
		{
			for(int col = 1;col<=colcount;col++){
			String xpath = xpath_first+row+xpath_last+col+"]";
			String text = doGetText(By.xpath(xpath));
			System.out.println(text);
			}
			System.out.println();
	    }
	}
	/*********************************** Actions Utils ***************************************/
	public void doMoveToElement(By locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}
	public void doClickOnChildMenu(By parentMenuLocator,By childMenuLocator) throws InterruptedException
	{
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);
	}
	public void doActionsSendKeys(By locator,String value)
	{
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator),value).build().perform();
	}
	public void doActionsSendKeysOnActiveElement(By locator,String value)
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}
	public void doActionsClick(By locator)
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	public void doActionsMoveToElementClick(By locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).click().build().perform();
	}
	/*********************************** Wait Utils ***************************************/
	
	public WebElement doPresenceOfElementLocated(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement doPresenceOfElementLocated(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementToBeVisible(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//Can be ignored
	public WebElement waitForElementToBeVisibleWithWebElement(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	public WebElement waitForElementToBeVisible(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<String> getElementsTextListWithWait(By locator,int timeOut)
	{
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e:eleList)
		{
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	/*********************************** Wait Utils for Non WebElements ***************************************/
	
	public boolean waitForURLToContain(String urlFraction,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	public boolean waitForURLToBe(String url,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	public String doGetTitle(String title,int timeOut)
	{
		if(waitForTitleToBe(title,timeOut))
		{
			return driver.getTitle();
		}
		else
			return null;
	}
	public String doGetTitleFraction(String titleFraction,int timeOut)
	{
		if(waitForTitleContains(titleFraction,timeOut))
		{
			return driver.getTitle();
		}
		else
			return null;
	}
	public boolean waitForTitleToBe(String title,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	}
	public boolean waitForTitleContains(String titleFraction,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	public Alert waitForAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(int timeOut)
	{
		return waitForAlert(timeOut).getText();
	}
	public void doAlertAccept(int timeOut)
	{
		 waitForAlert(timeOut).accept();
	}
	public void doAlertDismiss(int timeOut)
	{
		 waitForAlert(timeOut).dismiss();
	}
	public void enterAlertText(int timeOut,String value)
	{
		 waitForAlert(timeOut).sendKeys(value);
	}
	public void waitForFrameByNameorId(String frameNameorID,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorID));
	}
	public void waitForFrameByIndex(int index,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	public void waitForFrameByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	public void waitForFrameByElement(WebElement frameElement,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	public void clickElementWhenReady(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    wait.until(ExpectedConditions.elementToBeClickable(locator)).clear();
	    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public void clickElementWhenReady(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
	    wait.until(ExpectedConditions.elementToBeClickable(locator)).clear();
	    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
	}
	
	public WebElement waitForElementPresentUsingFluentWait(By locator,int timeOut,int pollingTime)
	{
		Wait<WebDriver>  wait= new FluentWait<WebDriver>(driver)
										.withTimeout(Duration.ofSeconds(timeOut))
										.pollingEvery(Duration.ofSeconds(pollingTime))
										.ignoring(NoSuchElementException.class)
										.ignoring(StaleElementReferenceException.class)
										.withMessage(Error.ELEMENT_NOT_FOUND_ERROR_MSG);

       return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementPresentUsingWebDriverWait(By locator,int timeOut,int pollingTime)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				      wait
										.pollingEvery(Duration.ofSeconds(pollingTime))
										.ignoring(NoSuchElementException.class)
										.ignoring(StaleElementReferenceException.class)
										.withMessage(Error.ELEMENT_NOT_FOUND_ERROR_MSG);

       return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	/************************************   Custom Waits   *******************************************/
	
	public WebElement retryGetElement(By locator,int timeOut)
	{
		int attempts =0;
		WebElement ele = null;
		while(attempts<timeOut)
		{
			try{
			ele = getElement(locator);
			break;
			}
			catch (NoSuchElementException e1){
				System.out.println("Element is not found with attempts :"+attempts);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			attempts++;
		}
		if(ele == null)
		{
			try {
			throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch(Exception e){
				System.out.println("The element is not found exception... Tried for: "+attempts+ " seconds "+"every :"+500+"ms");
			}
		}
		return ele;
	}
	public  WebElement retryGetElement(By locator,int timeOut,int pollingTime)
	{
		int attempts =0;
		WebElement ele = null;
		while(attempts<timeOut)
		{
			try{
			ele = getElement(locator);
			break;
			}
			catch (NoSuchElementException e1){
				System.out.println("Element is not found with attempts :"+attempts);
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			attempts++;
		}
		if(ele == null)
		{
			try {
			throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch(Exception e){
				System.out.println("The element is not found exception... Tried for: "+attempts+ " seconds "+"every :"+pollingTime+"ms");
			}
		}
		return ele;
	}
	

}
