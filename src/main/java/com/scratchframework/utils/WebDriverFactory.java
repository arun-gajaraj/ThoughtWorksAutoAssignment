package com.scratchframework.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class WebDriverFactory {

//	public static WebDriver driver = null;
	static WebDriver driver = null;
	private static Logger log = Logger.getLogger(WebDriverFactory.class);
	static {
		String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "win"
				+ File.separator + "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		log.debug("Chrome Driver Path Set");
	}

	public static WebDriver getBrowserInstance(String browserName) {

//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\arun.gajaraj\\Downloads\\Work\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\arun.gajaraj\\Downloads\\Work\\geckodriver.exe");

//		browserName = (browserName != null) ? browserName.toLowerCase() : "chrome"; // default Browser

		switch (browserName) {
		case "chrome":
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("start-maximized", "--test-type");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);

			driver = new ChromeDriver(capabilities);
			break;

		case "edge":

			System.setProperty("webdriver.edge.driver",
					"C:\\Windows\\WinSxS\\wow64_microsoft-webdriver-server-components_31bf3856ad364e35_10.0.17763.1_none_edeaf67448991e72\\MicrosoftWebDriver.exe");

			DesiredCapabilities capabilitiesEdge = DesiredCapabilities.edge();
			driver = new EdgeDriver(capabilitiesEdge);

			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			driver = new FirefoxDriver();
			break;

		}
		if (browserName.contains("edge"))
			return driver;

		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver getBrowserInstance() {

		String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("browserName");
		return getBrowserInstance(browserName);
	}

}
