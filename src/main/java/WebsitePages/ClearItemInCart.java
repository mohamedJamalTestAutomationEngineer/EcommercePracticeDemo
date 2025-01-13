package WebsitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;

public class ClearItemInCart extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public ClearItemInCart(WebDriver driver) {
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

	@FindBy(className = "cart-icon")
    private WebElement cartBtn;

    @FindBy(css = "[class='product-remove']")
    private WebElement clearButton;

    @FindBy(css = "div[class='empty-cart']")
    private WebElement cartEmptyMessage;

	
	public String ClearItemFromCart(String product) {

		WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));

		// no need to check this WebElement as there is a method "clickOnAddToCart"
		WebElement addToCartButton = itemWebElement.findElement(By.xpath("./following-sibling::div/button"));

		// Add the product to the cart
        addToCartButton.click();

        // Open the cart
        cartBtn.click();

        // Clear the cart
        clearButton.click();

        // Return the empty cart message
        return cartEmptyMessage.getText();
		
	}

	// get price of each product
	public int getItemPrice(String product) {
		WebElement priceWebElement = driver
				.findElement(By.xpath("//h4[contains(text(),'" + product + "')]/following-sibling::p"));
		// we multiplied by 1 as the QYT will be one by default
		int expectedPrice = Integer.parseInt(priceWebElement.getText()) * 1;
		// Extract and return the price
		return expectedPrice;
	}

	// add item to cart
	public void clickOnAddToCart(String product) {
		WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));

		WebElement addToCartButton = itemWebElement.findElement(By.xpath("./following-sibling::div/button"));

		// Click the "Add to Cart" button
        addToCartButton.click();

	}

}
