package TestFeatures;

import org.testng.annotations.Test;

import WebsitePages.BuyMoreItems;
import testingComponents.BaseTest;

public class TestBuyMoreItems extends BaseTest {

	private String[] productsArray = {"Brocolli", "Cauliflower", "Beans"};
    private int[] quantities = {1, 3, 2};

	@Test
	public void checkAddingManyItemsToCart() throws InterruptedException {
		
		BuyMoreItems moreItemsObject = new BuyMoreItems(driver);
        moreItemsObject.buyMoreItemsQTYs(productsArray, quantities);
        
        // here we need to calculate the price multiplied by their QTY then see the actual price in the cart
        
	}

}
