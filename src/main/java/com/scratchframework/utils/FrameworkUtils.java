package com.scratchframework.utils;

import java.util.List;

import org.openqa.selenium.WebElement;

public class FrameworkUtils {

	public static WebElement getMatchingWebElement(List<WebElement> listOfElements, String text) {

		for (WebElement element : listOfElements) {
			if (element.getText().contains(text)) {
				return element;
			}
		}
		return null;
	}

}
