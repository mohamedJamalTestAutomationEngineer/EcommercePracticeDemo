package TestFeatures;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebsitePages.AddToCartOperations;
import WebsitePages.BuyOneItem;
import testingComponents.BaseTest;

public class TestAddToCartOperations extends BaseTest {

	private String[] productsArray = { "Carrot", "Brocolli", "Tomato", "Brinjal" };
    private AddToCartOperations cartOperations;

	@Test
	public void refreshPageAfterAddingItemsToCart() {

		cartOperations = new AddToCartOperations(driver);
		cartOperations.AddManyItemToCart(productsArray);
		List<WebElement> addedProduct = cartOperations.getCartItems();

		// Verify cart contents before refreshing the page
		verifyCartContents(addedProduct);
		
		// page refresh
		cartOperations.refreshPage();
		
		// after refresh page , assertion here will fail because the items are removed ( here is the defect)
		verifyCartContents(addedProduct);
	}

	public void verifyCartContents(List<WebElement> addedProduct) {
		// Verify that all expected products are present in the cart
		for (String product : productsArray) {
			boolean productFound = false;
			for (WebElement item : addedProduct) {
				// retrieved item is "Brocolli - 1 Kg" but the in the array it`s "Brocolli" so
				// we will assert that retrieved item contains array element
				if (item.getText().contains(product)) {
					productFound = true;
					break;
				}
			}
			Assert.assertTrue(productFound, "Product '" + product + "' not found in the cart!");
		}
	}
	
	@Test
	public void testAddingTextAfterAddingItems(){
		cartOperations = new AddToCartOperations(driver);
		BuyOneItem buyObj = new BuyOneItem(driver);
		
		buyObj.clickOnAddToCart("Brocolli");
		String AfterClicking = cartOperations.checkAddedText("Brocolli");
		
		// returned text is "? ADDED" so we need to split it and take the needed part from our side. split function will do that by space separator
		// so we will use the 1st index which contain "ADDED" only 
		Assert.assertEquals("ADDED", AfterClicking.split(" ")[1]);
		
	}

}
