package com.qa.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.luma.utils.Constants;
import com.qa.luma.utils.ElementUtil;

public class LoginPage
{ 
	private WebDriver driver;
	private ElementUtil eleUtil;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	//private By signInButton = By.linkText("Sign In");
	private By emailId = By.id("email");
	private By password = By.id("pass");
	private By loginButton = By.xpath("(//button[@id ='send2'])[1]");
	private By header = By.xpath("//h1");
	private By loginErrorMessage = By.xpath("//div[@role='alert']");
	private By createAccountButton = By.linkText("Create an Account");

	public boolean getLoginPageTitle() {
		return eleUtil.waitForTitleContains(Constants.LOGIN_PAGE_TITLE, 5);
	}
	public String getPageHeaderText() {
		return eleUtil.doGetText(header);
	} 
	
	public AccountsPage doLogin(String un,String pwd)
	{
		//driver.findElement(signInButton).click();
		System.out.println("Login with username as : "+un +" and password : "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doActionsClick(loginButton);
		return new AccountsPage(driver);
	}
	public boolean doLoginWithIncorrectCreds(String un,String pwd)
	{
		//The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.
		System.out.println("Login with incorrect username: "+un +" and password : "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doActionsClick(loginButton);
		String errorMsg = eleUtil.doGetText(loginErrorMessage);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE))
		{
			System.out.println("Unable to login");
			return false;
		}
		return true;

	}
	public CreateAccountPage goToCreateAccountPage()
	{
		eleUtil.doClick(createAccountButton);
		return new CreateAccountPage(driver);
	}

}




