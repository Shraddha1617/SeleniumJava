package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accSections = By.cssSelector("div#content h2");
	private By header= By.xpath("//div[@id='logo']//h1//a[text()='Your Store']");
	private By logoutlink= By.linkText("Logout");
	private By searchfield = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public void getAccPageUrl() {
		elementUtil.getPageUrl();
	}
	
	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public List<String> getAccountSectionList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSectionList=
				elementUtil.waitForVisibilityOfElements(accSections, 5);
		for(WebElement e: accSectionList) {
			accSecValList.add(e.getText());
		}
		Collections.sort(accSecValList );
		return accSecValList;
	}
	
	public boolean isLogoutExist() {
		return elementUtil.doIsDisplayed(logoutlink);
	}
	
	//search methos
	public SearchResultPage doSearch(String productName) {
		System.out.println("Searching the product: "+ productName);
		elementUtil.doSendKeys(searchfield, productName);
		elementUtil.doClick(searchButton);
		//next landing page class
		return new SearchResultPage(driver);
	}
	
	

}
