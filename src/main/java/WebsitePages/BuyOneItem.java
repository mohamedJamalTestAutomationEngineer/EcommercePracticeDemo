package WebsitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;

public class BuyOneItem extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public BuyOneItem(WebDriver driver) {
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
    private WebElement cartButton;

    @FindBy(css = "p[class='amount']")
    private WebElement totalItemPrice;

    /**
     * Buy one item with specified quantity.
     *
     * @param product Name of the product to buy.
     * @param qty Quantity to purchase.
     * @return Total price calculated based on the quantity.
     */
	public int buyoneItemWithQTYs(String product, int qty) {

		WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));
		
		// QTY in the search Box
		WebElement QTYBox = itemWebElement.findElement(By.xpath("./following-sibling::div/input"));

		WebElement priceWebElement = itemWebElement.findElement(By.xpath("./following-sibling::p"));

		QTYBox.clear();

		QTYBox.sendKeys(Integer.toString(qty));

		clickOnAddToCart(product);

		int totalPrice = Integer.parseInt(priceWebElement.getText());
		totalPrice *= qty;

		cartButton.click();

		return totalPrice;

	}

	/**
     * Get the price of a specific product.
     *
     * @param product Name of the product.
     * @return Price of the product for a single unit.
     */
	public int getItemPrice(String product) {
		WebElement priceWebElement = driver
				.findElement(By.xpath("//h4[contains(text(),'" + product + "')]/following-sibling::p"));
		// we multiplied by 1 as the QYT will be one by default
		int expectedPrice = Integer.parseInt(priceWebElement.getText()) * 1;
		return expectedPrice;
	}

	/**
     * Get the price of the item currently added in the cart.
     *
     * @return Total price of items in the cart.
     */
	public int getItemPriceInCart() {

		return Integer.parseInt(totalItemPrice.getText());
	}

	/**
     * Add a specific product to the cart.
     *
     * @param product Name of the product to add.
     */
	public void clickOnAddToCart(String product) {
		WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));
		WebElement AddtoCart = itemWebElement.findElement(By.xpath("./following-sibling::div/button"));
		AddtoCart.click();
	}

}
