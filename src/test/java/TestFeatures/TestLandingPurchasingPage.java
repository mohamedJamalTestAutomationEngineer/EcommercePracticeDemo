package TestFeatures;

import org.testng.annotations.Test;

import WebsitePages.LandingPurchasingPage;
import testingComponents.BaseTest;


public class TestLandingPurchasingPage extends BaseTest {


	@Test
	public void getCartInfo() throws InterruptedException {
		LandingPurchasingPage landObject = new LandingPurchasingPage(driver);
		landObject.getCartInfoElement();  
	}
	
	
}
