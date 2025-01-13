package TestFeatures;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import WebsitePages.SearchPage;
import testingComponents.BaseTest;


public class TestSearchPage extends BaseTest {

    private static final String EXPECTED_NO_RESULTS_MESSAGE = "Sorry, no products matched";

    /**
     * Test for positive search scenario.
     * 
     * @param searchKeyword The keyword to search for.
     */
    @Test(dataProvider = "positiveSearchData")
    public void testPositiveSearch(String searchKeyword) throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);
        var searchResults = searchPage.searchProducts(searchKeyword);

        Assert.assertFalse(searchResults.isEmpty(), "Expected search results, but none were found.");
        System.out.println("Positive Search Results: " + searchResults);
    }

    /**
     * Test for negative search scenario.
     * 
     * @param searchKeyword The keyword to search for.
     */
    @Test(dataProvider = "negativeSearchData")
    public void testNegativeSearch(String searchKeyword) throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);
        String noResultsMessage = searchPage.verifyNegativeSearch(searchKeyword);

        Assert.assertTrue(noResultsMessage.contains(EXPECTED_NO_RESULTS_MESSAGE),
                "Expected 'no results' message not found. Actual message: " + noResultsMessage);
        System.out.println("Negative Search Message: " + noResultsMessage);
    }

    /**
     * DataProvider for positive search tests.
     */
    @DataProvider(name = "positiveSearchData")
    public Object[][] positiveSearchData() {
        return new Object[][] { { "rr" }, { "Tomato" } };
    }

    /**
     * DataProvider for negative search tests.
     */
    @DataProvider(name = "negativeSearchData")
    public Object[][] negativeSearchData() {
        return new Object[][] { { "InvalidProduct" }, { "rr," } };
    }

	
	
}
