package WebsitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;

public class ProceedToCheckout extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public ProceedToCheckout(WebDriver driver) {
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

    @FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;

    @FindBy(tagName = "select")
    private WebElement countryDropdown;

    @FindBy(className = "chkAgree")
    private WebElement agreeCheckbox;

    @FindBy(tagName = "button")
    private WebElement proceedBtn;

    @FindBy(className = "wrapperTwo")
    private WebElement confirmationMessage;
    
    
    /**
     * Clears an item from the cart.
     * @param product The name of the product to be cleared.
     * @return A message confirming the cart is empty.
     */
	public String ClearItemFromCart(String product) {

		//WebElement itemWebElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));
		// no need to check this WebElement as there is a method "clickOnAddToCart"
		//WebElement AddtoCart = itemWebElement.findElement(By.xpath("./following-sibling::div/button"));
		//AddtoCart.click();
		
		addProductToCart(product);
        cartBtn.click();
		cartBtn.click();
		clearButton.click();
		return cartEmptyMessage.getText();
	}

	/**
     * Verifies if the "Proceed to Checkout" button is enabled.
     * @return True if the button is enabled, false otherwise.
     */
	public boolean verifyCheckoutbutton() {
		cartBtn.click();
        boolean isEnabled = proceedToCheckoutBtn.isEnabled();
        System.out.println("Checkout button enabled: " + isEnabled);
        return isEnabled;	
	}
	
	/**
     * Completes the order creation process.
     * @param product The product to be purchased.
     * @return The confirmation message displayed after placing the order.
     * @throws InterruptedException If there is a delay during the process.
     */
	public String createOrder(String product) throws InterruptedException {
		
		BuyOneItem buyObj = new BuyOneItem(driver);
        buyObj.buyoneItemWithQTYs(product, 3);

        proceedToCheckoutBtn.click();
        waitForElementToAppear(placeOrderBtn);
        placeOrderBtn.click();

        waitForElementToAppear(countryDropdown);
        selectCountry("Egypt");

        agreeCheckbox.click();
        proceedBtn.click();

        return confirmationMessage.getText();
	}
	
	/**
     * Selects a country from the dropdown during the checkout process.
     * @param country The country to select.
     */
    private void selectCountry(String country) {
        Select dropdown = new Select(countryDropdown);
        dropdown.selectByValue(country);
    }

    /**
     * Adds a product to the cart.
     * @param product The name of the product to add.
     */
    private void addProductToCart(String product) {
        WebElement itemElement = driver.findElement(By.xpath("//h4[contains(text(),'" + product + "')]"));
        WebElement addToCartButton = itemElement.findElement(By.xpath("./following-sibling::div/button"));
        addToCartButton.click();
    }

}
