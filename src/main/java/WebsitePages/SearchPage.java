package WebsitePages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class SearchPage extends AbstractComponent {

	private WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public SearchPage(WebDriver driver) {
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

	@FindBy(className = "search-keyword")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@class='product']/h4")
    private List<WebElement> resultProducts;

    @FindBy(className = "no-results")
    private WebElement noResults;

    /**
     * Performs a search and returns the list of product names found.
     * 
     * @param searchValue The keyword to search for.
     * @return List of product names matching the search.
     * @throws InterruptedException
     */
	public List<String> searchProducts(String searchValue) throws InterruptedException {
		clearAndEnterSearchValue(searchValue);

        List<String> productNames = new ArrayList<>();
        if (!resultProducts.isEmpty()) {
            for (WebElement product : resultProducts) {
                productNames.add(product.getText());
            }
        } else {
            System.out.println("No products found for the keyword: " + searchValue);
        }
        return productNames;
	}

	/**
     * Verifies a negative search result and returns the 'no results' message.
     * 
     * @param searchValue The keyword to search for.
     * @return The 'no results' message displayed on the page.
     * @throws InterruptedException
     */
    public String verifyNegativeSearch(String searchValue) throws InterruptedException {
        clearAndEnterSearchValue(searchValue);

        if (resultProducts.isEmpty() && noResults.isDisplayed()) {
            return noResults.getText();
        }
        return null; // Return null if results are unexpectedly found.
    }

    /**
     * Helper method to clear the search box and enter a search value.
     * 
     * @param searchValue The keyword to enter into the search box.
     */
    private void clearAndEnterSearchValue(String searchValue) {
        searchBox.clear();
        searchBox.sendKeys(searchValue);
    }

}
