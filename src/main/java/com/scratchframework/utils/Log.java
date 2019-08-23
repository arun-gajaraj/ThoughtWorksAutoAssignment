package com.scratchframework.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Log {

	@SuppressWarnings("unused")
	private static String screenShotFolderPath = File.separator + "test-output"+ File.separator + "ScreenShot" + File.separator;
	private static AtomicInteger screenShotIndex = new AtomicInteger(0);

	public static void log(String message) {
		Reporter.log(message);

	}

	/**
	 * Takes screenshot
	 * 
	 * @param message
	 * @param driver
	 * @throws IOException
	 */
	public static void log(String message, WebDriver driver) throws IOException {

		String file = Log.takeScreenshot(driver);
		Reporter.log(message + getScreenshotHyperlink(file));
	}

	public static String getScreenshotHyperlink(String file) {
		String screenShotHyperLink = "";
		screenShotHyperLink = "<a href = "+System.getProperty("user.dir")+ File.separator +"test-output"+ File.separator + "Screenshot" + File.separator + file + "> [Screenshot] </a>";
		return screenShotHyperLink;
	}

	public static String takeScreenshot(WebDriver driver) throws IOException {
		String inputFile = "";
		inputFile = Reporter.getCurrentTestResult().getName() + "_" + screenShotIndex.getAndIncrement() + ".png";
		ScreenshotManager.takeScreenshot(driver, inputFile);
		return inputFile;

	}
}
