package com.qa.opencart.Tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void setUpRegister() {
		registrationpage =  loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	//return type of data provider is 2-D Object Array
	public Object[][] getRegisterData() {
		Object regDate[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regDate;
		
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "testautomation" + randomGenerator.nextInt(1000) + "@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone,
										String password, String subscribe) {
		Assert.assertTrue(registrationpage.accRegistration(firstName, lastName, getRandomEmail(),
							telephone, password, subscribe));
	}
	
	
	
	
	
	
	
	
	
	

}
