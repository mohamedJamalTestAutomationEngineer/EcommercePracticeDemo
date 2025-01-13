package TestFeatures;

import org.testng.Assert;
import org.testng.annotations.Test;

import WebsitePages.BuyOneItem;
import testingComponents.BaseTest;

public class TestBuyOneItem extends BaseTest {

	
	@Test
	public void checkAddingOneItemToCart() throws InterruptedException {

		String productName = "Brocolli";
		int quantity = 5;
		BuyOneItem buyMoreObject = new BuyOneItem(driver);
		
		// Add the product with the specified quantity to the cart and retrieve the calculated total price.
		int expectedTotalPrice = buyMoreObject.buyoneItemWithQTYs(productName, quantity);
	
		// Retrieve the total price of the product in the cart and assert it matches the expected total price.
        int actualTotalPriceInCart = buyMoreObject.getItemPriceInCart();

		Assert.assertEquals(actualTotalPriceInCart, expectedTotalPrice);


	}

}
