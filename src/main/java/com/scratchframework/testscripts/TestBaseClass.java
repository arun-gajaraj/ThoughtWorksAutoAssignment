/**
 * 
 */
package com.scratchframework.testscripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.scratchframework.utils.PropertiesReader;
import com.scratchframework.utils.ScreenshotManager;
import com.scratchframework.utils.WebDriverFactory;

/**
 * @author arun.gajaraj
 *
 */
public class TestBaseClass {

	static String webSite;
//	WebDriver driver;
	public static WebDriver driver;
	List<String> testDataList = new ArrayList<>();
	public static int screenshotindex = 1;
	String browserName;

	@BeforeSuite
	public void initTest() throws FileNotFoundException {

		PropertiesReader.initializeEnvironmentProperties();
		webSite = PropertiesReader.getProperty("website");
		browserName = PropertiesReader.getProperty("browserName");
		
//				Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("website");
		File file = new File("Data.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			testDataList.add(sc.next());
		}
	}

	@BeforeMethod
	public void initDriver() {
		driver = WebDriverFactory.getBrowserInstance(browserName);
	}

	@AfterMethod
	public void closeDriver(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ScreenshotManager.takeScreenshot(driver, "screenshot_" + screenshotindex++ + ".png");
		}
		driver.quit();
	}

}
