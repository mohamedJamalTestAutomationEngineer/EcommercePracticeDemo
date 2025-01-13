package TestFeatures;

import org.testng.Assert;
import org.testng.annotations.Test;

import WebsitePages.ProceedToCheckout;
import testingComponents.BaseTest;

/**
 * Test class for validating checkout functionality.
 */
public class TestProceedToCheckout extends BaseTest {


	@Test
	public void shouldDisableCheckoutButtonWhenNoProducts() {

		ProceedToCheckout proceedObject = new ProceedToCheckout(driver);
		boolean isCheckoutEnabled = proceedObject.verifyCheckoutbutton();
        Assert.assertFalse(isCheckoutEnabled, "Checkout button should be disabled when no products are in the cart.");
   
	}
	
	@Test
	public void shouldPlaceOrderSuccessfully() throws InterruptedException {
        String expectedSuccessMessage = "your order has been placed successfully";
        ProceedToCheckout proceedObject = new ProceedToCheckout(driver);

        // Create an order for "Brocolli" and verify the success message
        String actualMessage = proceedObject.createOrder("Brocolli");
        Assert.assertTrue(actualMessage.contains(expectedSuccessMessage),
            "Order placement failed. Expected message: '" + expectedSuccessMessage + "', but got: '" + actualMessage + "'.");
    }

}
