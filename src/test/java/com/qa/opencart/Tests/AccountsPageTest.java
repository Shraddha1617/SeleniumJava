package com.qa.opencart.Tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest{
	
	@BeforeTest
	public void accPageSetup() {
		accPage= loginPage.
				doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	 @Test
	 public void accPageTitleTest() {
		 String title = accPage.getAccPageTitle();
		 System.out.println("Account Page title is "+ title);
		 Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, Errors.ACC_PAGE_TITLE_ERROR);
	 }
	 
	 @Test
	 public void accPageHeaderTest() {
		 String header = accPage.getAccPageHeader();
		 System.out.println("Account Page Header is: "+ header);
		 Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_ERROR);
	 }
	 
	 @Test
	 public void accSectionsListTest() {
		List<String> secList = accPage.getAccountSectionList();
		secList.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.EXP_ACC_SEC_LIST); 
		Assert.assertEquals(secList, Constants.EXP_ACC_SEC_LIST);
	 }
	
	 @Test
	 public void logoutLinkTest() {
		 Assert.assertTrue(accPage.isLogoutExist(), Errors.LOGOUT_LINK_NOT_PRESENT);
	 }

}
