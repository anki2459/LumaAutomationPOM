package com.qa.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ElementUtil;

public class CreateAccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CreateAccountPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By firstName = By.id("firstname");
	private By lastName = By.id("lastname");
	private By email = By.id("email_address");
	private By password = By.id("password");
	private By confirmPassword = By.id("password-confirmation");
	
	private By createButton = By.xpath("//button[@title = 'Create an Account']");
	private By subscribeCheckBox = By.xpath("//input[@id='is_subscribed']");
	private By successMsg = By.xpath("//div[@role ='alert']");
	private By accountHeading = By.tagName("h1");
	
	private By dropdownButton = By.xpath("(//button[@type='button'])[position ()=1]");
	private By signOutLink = By.partialLinkText("Sign Out");
	private By createAccountLink = By.linkText("Create an Account");

public boolean docreateAccount(String fName,String lName,String email,String pass,String subscribe)
{
	eleUtil.doSendKeys(firstName,fName);
	eleUtil.doSendKeys(lastName,lName);
	eleUtil.doSendKeys(this.email, email);
	eleUtil.doSendKeys(password, pass);
	eleUtil.doSendKeys(confirmPassword, pass);
if(subscribe.equals("yes"))
{
	eleUtil.doClick(subscribeCheckBox);
}
eleUtil.doClick(createButton);

String successMessage = eleUtil.waitForElementToBeVisible(accountHeading,20).getText();
//String successMessage = eleUtil.waitForElementToBeVisible(successMsg,10).getText();
System.out.println(successMessage);
if(successMessage.contains(Constants.ACCOUNTS_PAGE_SUCCESS))
{
eleUtil.doActionsMoveToElementClick(dropdownButton);
eleUtil.doClick(signOutLink, 10);
eleUtil.doClick(createAccountLink,10);
return true;
}
return false;
}

	

}
