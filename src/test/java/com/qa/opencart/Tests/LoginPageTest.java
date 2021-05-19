package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{
	
	 
	
	@Test(priority =1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	//suppose I don't want to execute this method we have to exclude this fr that we use enabled=false
	@Test(priority=2, enabled=false)//if enabled = false then that perticular test will not participate in execution
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_URL_VALUE));
	}
	
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	

}
