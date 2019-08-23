package com.scratchframework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenshotManager {

	private static String screenShotFolderPath;
	@SuppressWarnings("unused")
	private static File screens;

	static {
		File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
		screenShotFolderPath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
		screenShotFolder = new File(screenShotFolderPath);

		if (!screenShotFolder.exists()) {
			screenShotFolder.mkdir();
		}

		File[] screenShots = screenShotFolder.listFiles();
		screens = screenShotFolder;
		// delete files if the folder has any
		if (screenShots != null && screenShots.length > 0) {
			for (File screenShot : screenShots) {
				screenShot.delete();
			}
		}

	}

	public static void takeScreenshot(WebDriver driver, String saveAsFileName) throws IOException {

		TakesScreenshot screenShot = ((TakesScreenshot) driver);
		File file = screenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(screenShotFolderPath + saveAsFileName);
//		destFile.getParentFile().mkdirs();
		FileUtils.copyFile(file, destFile);
		file.delete();

	}
	
	public static void takeScreenshot(WebDriver driver) {
		TakesScreenshot screenShot = ((TakesScreenshot) driver);
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		
	}

}
