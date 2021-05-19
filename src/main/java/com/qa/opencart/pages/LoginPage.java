	package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	//reference of webdriver
	private WebDriver driver; 
	private ElementUtil elementUtil;
	
	// this will maintain it's By locator
	// this is the pattern of the POM that every page class will have a sepearte java class
	// and every java class will have its own locators
	// you can make it as public but acc to the POM you should achieve it in the form of encapsulation  
	// that it is only for that page and you can access via public methods
	
	//1. private By locator
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton= By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	
	//2. constructors of a class
	public LoginPage(WebDriver driver) {
		//says when you perform any action like you want to press username
		//what thing you need--> you need a driver
		// who give driver to the login page
		// when we create an object of loginpage class, a constutr will called and via 
		// constructor we will pass the driver
		
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. public page actions--> means page methods we have to define
	public String getLoginPageTitle() {
		return elementUtil.waitForTitle(5, Constants.LOGIN_PAGE_TITLE);
	}
	
	public String getLoginPageUrl() {
		return elementUtil.getPageUrl();
	}
	
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	

}
