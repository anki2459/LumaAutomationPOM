package com.qa.luma.utils;

import java.util.ArrayList;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Customer Login Magento";
	public static final String LOGIN_PAGE_HEADER = "Customer Login";
	
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "My Account";
	public static final int TEES_COUNT = 3;
	public static final int JACKETS_COUNT = 3;
	public static final int TANKS_COUNT = 3;
	public static final String LOGIN_ERROR_MESSAGE = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
	//public static final String ACCOUNTS_PAGE_SUCCESS = "Thank you for registering with Fake Online Clothing Store.";
	public static final String ACCOUNTS_PAGE_SUCCESS = "My Account";
	public static final String CREATE_ACCOUNT_SHEET_NAME = "registration";

	
	
	public static ArrayList<String> getExpectedAccountsSectionList()
	{
		ArrayList<String> expectedAccSecList = new ArrayList<String>();
		expectedAccSecList.add("Contact Information");
		expectedAccSecList.add("Newsletters");
		expectedAccSecList.add("Default Billing Address");
		expectedAccSecList.add("Default Shipping Address");
		return expectedAccSecList;

	}

}
