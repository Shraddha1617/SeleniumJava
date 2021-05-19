package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Dell 
 * @return this method return webdriver
 */
public class DriverFactory {

	WebDriver driver;	
	Properties prop;
	private OptionsManager optionsManager;
	
	public static String highlight = null;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_driver(Properties prop) {
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		String browserName= prop.getProperty("browser").trim();
		System.out.println("browser name is: "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//			driver= new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//			driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
		}
		if(browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
//			driver= new SafariDriver(); 
		}
		else {
			System.out.println("please pass the correct browser: "+ browserName);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
//		driver.get("https://demo.opencart.com/index.php?route=account/login");
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method interact with config.properties
	 * it will read all properties from config file
	 * and in config file all properties will behave like a environment variable
	 * @return this method will return Properties Object
	 */
	public Properties init_prop() {
		//how you will read the properties
		//to read the properties in java
		// we use class called Properties
		FileInputStream ip = null;
		prop = new Properties();
		
		String env = System.getProperty("env");
		System.out.println("Running on environment: "+ env);
		
		if(env == null) {
			try {
				ip= new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream(".//src//test//resources//config//qa.config.properties");
					break;
					
				case "stage":
					ip = new FileInputStream(".//src//test//resources//config//stage.config.properties");
					break;
					
				case "dev":
					ip = new FileInputStream(".//src//test//resources//config//dev .config.properties");
					break;

				default:
					break;
				}
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//(./ before src means go to the current project directory and then path
			//what is the purpose of FileInputStream---> it will make the connection with config.properties
			// or whatever the file you provided in double quotes
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/screenshots"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
