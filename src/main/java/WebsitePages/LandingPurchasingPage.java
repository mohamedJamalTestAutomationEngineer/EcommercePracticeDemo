package WebsitePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPurchasingPage extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public LandingPurchasingPage(WebDriver driver) {
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

	@FindBy(className = "cart-info")
    private WebElement cartInfo;

	/**
     * Retrieves the cart details text.
     * 
     * @return The text from the cart information element.
     */
    public String getCartDetails() {
        return cartInfo.getText();
    }

    /**
     * Provides access to the cart information WebElement.
     * 
     * @return The cart information WebElement.
     */
    public WebElement getCartInfoElement() {
        return cartInfo;
    }

}
