package com.scratchframework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scratchframework.utils.FrameworkUtils;
import com.thoughtworks.selenium.SeleniumException;

public class FoodLookupPage {

	protected WebDriver driver;
	private WebDriverWait wait;

	private static final int TIMEOUT = 5;
	private static final int POLLING = 100;

	public FoodLookupPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}

	@FindBy(css = ".large.table")
	List<WebElement> lstTables;

	@FindBy(css = ".text.container > table")
	List<WebElement> tableSelectedFoods;

	@FindBy(css = "#food-search > table")
	List<WebElement> tableSearchFoods;

	@FindBy(css = ".App > div > table > tbody > tr")
	List<WebElement> lstSelectedRows;

	@FindBy(css = "#food-search tbody tr")
	List<WebElement> lstSearchResults;

	@FindBy(css = ".prompt")
	WebElement inputSearchFoodText;

	@FindBy(css = ".trash")
	WebElement iconDeleteTrash;

	@FindBy(css = "#total-kcal")
	WebElement totalKCal;

	@FindBy(css = ".remove.icon")
	WebElement clearSearch;

	public final boolean verifyPageContainsExpectedTables() {
		boolean twoTables = false;

		if (lstTables.size() == 2) {
			twoTables = true;
		}

		return twoTables;

	}

	/**
	 * Gets the nth item description from selected table
	 * 
	 * @param index
	 * @return
	 */
	public String getSelectedItemName(int index) {
		return lstSelectedRows.get(index).findElement(By.cssSelector("td")).getText();
	}

	public String getLastSelectedItemName() {
		return lstSelectedRows.get(lstSelectedRows.size() - 1).findElement(By.cssSelector("td")).getText();

	}

	public void searchForFood(String foodName) {
//		inputSearchFoodText.clear(); 
		this.clearSearch();
		inputSearchFoodText.sendKeys(foodName);

	}

	public void clearSearch() {
		try {
			clearSearch.click();
		} catch (ElementNotVisibleException e) {
			// No Clear Icon
		}

	}

	public void selectFoodFromSearch(String foodName) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> listElement = driver.findElements(By.cssSelector("#food-search tbody tr"));
		Thread.sleep(1000);
		WebElement element = FrameworkUtils
				.getMatchingWebElement(listElement, foodName);
		element.click();
	}

	public void searchAndSelectFood(String food) throws InterruptedException {
		this.searchForFood(food);
		this.selectFoodFromSearch(food);
	}

	public void deleteFood(String food) {
		WebElement foodElement = FrameworkUtils.getMatchingWebElement(lstSelectedRows, food);
		foodElement.findElement(By.cssSelector(".trash"));

	}

	public boolean verifySelectedFoodExists(String food) {

		WebElement element = FrameworkUtils.getMatchingWebElement(lstSelectedRows, food);
		if (element == null)
			return false;
		else
			return true;

	}

	public int getNthItemKCalCount(int index) {
		return 0;
	}

	public int getKcalCountSum() {
		int tempTotal = 0;

		for (WebElement element : lstSelectedRows) {
			tempTotal = tempTotal + Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(2)")).getText());
		}

		return tempTotal;
	}

	public int getTotalKCalValue() {

		double totalValue = Double.parseDouble(totalKCal.getText());

		return ((int) totalValue);
	}

}
