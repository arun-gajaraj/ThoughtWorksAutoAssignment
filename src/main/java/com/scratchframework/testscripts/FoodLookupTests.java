package com.scratchframework.testscripts;

import static org.testng.Assert.*;

import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.scratchframework.pages.FoodLookupPage;

public class FoodLookupTests extends TestBaseClass {

	private final static HashMap<String, String> testData = new HashMap<>();
	static {
		testData.put("food1", "Cheese, blue");
		testData.put("food2", "Fish oil, cod liver");
	}

	@Test(description = "Verify the Page contains Selected Foods and Search Table")
	public void verifyThePageTables() {

		driver.get(webSite);
		FoodLookupPage foodLookupPage = new FoodLookupPage(driver);
		assertTrue(foodLookupPage.verifyPageContainsExpectedTables(),
				"The Page Contains Selected Foods and Food Search Table");
	}

	@Test(description = "Verify the user can Search and add a Food Item")
	public void verifyFoodItemCanBeAdded() throws InterruptedException {

		String foodNameToAdd = testData.get("food1");
		driver.get(webSite);
		FoodLookupPage foodLookupPage = new FoodLookupPage(driver);
		foodLookupPage.searchForFood(foodNameToAdd);
		foodLookupPage.selectFoodFromSearch(foodNameToAdd);

		assertEquals(foodLookupPage.getLastSelectedItemName(), foodNameToAdd);

	}

	@Test(description = "Verify the user can Delete a Food Item")
	public void verifyFoodItemCanBeDeleted() throws InterruptedException {

		String food = testData.get("food1");
		String anotherFood = testData.get("food2");
		driver.get(webSite);
		FoodLookupPage foodLookupPage = new FoodLookupPage(driver);
		foodLookupPage.searchForFood(food);
		foodLookupPage.selectFoodFromSearch(food);

		foodLookupPage.searchAndSelectFood(anotherFood);

		foodLookupPage.deleteFood(food);

		assertFalse(foodLookupPage.verifySelectedFoodExists(food));
		Reporter.log("Item is Deleted Successfully");

	}

	@Test(description = "Verify the user can Delete a Food Item")
	public void verifyKcalCountOfItemsFromTextFile() throws InterruptedException {

	
		driver.get(webSite);
		FoodLookupPage foodLookupPage = new FoodLookupPage(driver);

		foodLookupPage.searchAndSelectFood(testDataList.get(0));
		foodLookupPage.searchAndSelectFood(testDataList.get(1));
		foodLookupPage.searchAndSelectFood(testDataList.get(2));
		foodLookupPage.searchAndSelectFood(testDataList.get(3));
		
		assertEquals(foodLookupPage.getKcalCountSum(), foodLookupPage.getTotalKCalValue());
		

	}

}
