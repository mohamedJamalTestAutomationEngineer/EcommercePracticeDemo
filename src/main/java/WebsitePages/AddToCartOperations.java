package WebsitePages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class AddToCartOperations extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public AddToCartOperations(WebDriver driver) {
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
    private List<WebElement> itemNames;

    @FindBy(className = "cart-icon")
    private WebElement cartButton;

    @FindBy(css = "li div p[class='product-name']")
    private List<WebElement> cartItems;

    @FindBy(css = "[class='amount']")
    private WebElement price;

    /**
     * Adds multiple items to the cart based on an array of product names.
     * @param productArray Array of product names to add to the cart.
     */ 
	public void AddManyItemToCart(String[] prodcutArrays) {
		for (String product : prodcutArrays) {
			for (WebElement item : itemNames) {
				if (item.getText().contains(product)) {
					// to find element in three steps step (assuming you are not using POM ) , HYG
					// String prodcutName = "Brocolli";
					// WebElement item = driver.findElement(By.xpath("//h4[contains(text(),'" +
					// prodcutName + "')]/following-sibling::div/button"));
					WebElement add = item.findElement(By.xpath("./following-sibling::div/button"));
					add.click();
					break;
				}
			}
		}
	}
	
	
	 /**
     * Retrieves items currently in the cart.
     * @return List of WebElement representing items in the cart.
     */
	public List<WebElement> getCartItems() {

		cartButton.click();

		if (cartItems.isEmpty()) {
			// there are no products in the cart
			System.out.println("No items found in the cart.");
		}
		return cartItems;
	}

	/**
     * Refreshes the current page.
     */
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	
	/**
     * Checks the text of the add button for a specific product.
     * @param product Name of the product to check.
     * @return The text of the add button for the product.
     */
	public String checkAddedText(String product){

		WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]/following-sibling::div/button"));
		return itemWebElement.getText();
	}

}
