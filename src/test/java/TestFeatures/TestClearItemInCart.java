package TestFeatures;

import org.testng.Assert;
import org.testng.annotations.Test;

import WebsitePages.ClearItemInCart;
import testingComponents.BaseTest;

public class TestClearItemInCart extends BaseTest {
	
	@Test
	public void ClearProductFromCart() {

		String productName = "Brocolli";
		String expectedEmptyCartMessage = "cart is empty!";
		
		ClearItemInCart clearObject = new ClearItemInCart(driver);
		String actualEmptyCartMessage = clearObject.ClearItemFromCart(productName);
        Assert.assertTrue(actualEmptyCartMessage.contains(expectedEmptyCartMessage));
	}

}
