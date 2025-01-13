package WebsitePages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;

public class BuyMoreItems extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public BuyMoreItems(WebDriver driver) {
		// The super(driver) call is used to invoke the constructor of the parent
		// AbstractComponent class (this might handle
		// some base functionality for all page objects).
		// It passes the driver argument from the categories constructor to the
		// constructor of AbstractComponent.
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}

	@FindBy(xpath = "//h4")
    private List<WebElement> nameOfAllItems;

    @FindBy(className = "cart-icon")
    private WebElement cartButton;

    @FindBy(className = "increment")
    private WebElement increment;

    @FindBy(css = "p[class='amount']")
    private List<WebElement> cartItemPrices;

    /**
     * Adds multiple items to the cart with specified quantities.
     * @param productsArray Array of product names.
     * @param quantities Array of quantities corresponding to product names.
     */
	@Test
	public void buyMoreItemsQTYs(String[] productsArray, int[] quantities) {
		// Iterate over the list of products and quantities
		for (int i = 0; i < productsArray.length; i++) {
			String productIndexValue = productsArray[i];
			int qunatity = quantities[i];
			// Iterate over all available items on the page
			for (WebElement item : nameOfAllItems) {
				if (item.getText().contains(productIndexValue)) {
					WebElement AddtoCart = item.findElement(By.xpath("./following-sibling::div/button"));
					WebElement QTYBox = item.findElement(By.xpath("./following-sibling::div/input"));
					// Clear the quantity box and enter the required quantity
					QTYBox.clear();
					// System.out.println("Cleared QTY Box for: " + productIndexValue);
					QTYBox.sendKeys(Integer.toString(qunatity));
					AddtoCart.click();
					break;
				}
			}
		}

		cartButton.click();
		calculateTotalPrice();

		/*
		double count = 0.0;
		for (WebElement item : CartItemPrice) {
			String priceText = item.getText().trim(); // Trim whitespace

			System.out.println("Price Text: '" + priceText + "'");  // Debugging line
			
			// Skip if the price text is empty or contains invalid characters
			if (priceText.isEmpty()) {
				System.out.println("Empty price text, skipping this item.");
				// skip that item entirely, without affecting the final calculation
				continue; // Skip this iteration and proceed to the next code
			}
			String cleanedPriceText = priceText.replaceAll("[^0-9\\.]", ""); // Remove any non-numeric characters (currency symbols,// etc.)
			
			// Only process the text if it's not empty and contains a valid numeric value
		    if (cleanedPriceText.isEmpty()) {
		        System.out.println("Invalid price format: " + priceText);
		        continue;
		    }
			
			try {
				// Try parsing the cleaned price
				double price = Double.parseDouble(cleanedPriceText);
				// Add to total
				count += price;
			} catch (NumberFormatException e) {
				System.out.println("Invalid price format: " + cleanedPriceText);
			}
		}
		System.out.println("count prices : " + count);
		*/
		
		// another refactored code for above price part 
		
		
	}
	
	  /**
     * Calculates the total price of items in the cart.
     */
	
	
	private void calculateTotalPrice() {
        double totalPrices = 0.0;

        for (WebElement item : cartItemPrices) {
            String priceText = item.getText().trim().replaceAll("[^0-9\\.]", "");

            if (priceText.isEmpty()) {
                System.out.println("Invalid or empty price, skipping item.");
                continue;
            }

            try {
                totalPrices += Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + priceText);
            }
        }

        System.out.println("Total price: " + totalPrices);
    }
}