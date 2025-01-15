package TestCases;

import Base.TestInitializer;
import Pages.*;
import Utils.ScreenCapture;
import Utils.ExcelDataHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchItemAddToCart extends TestInitializer {
    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void searchAndAddCakeToCart() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ItemDetailsPage itemDetailsPage = new ItemDetailsPage(driver);
        AddToCartPopupPage addToCartPopupPage = new AddToCartPopupPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

        // Read data
        String itemType = excel.getCellData(1, 2); // "cakes"
        String expectedPrice = excel.getCellData(3, 2);

        try {
            // Step 1: Search for 'cakes'
            homePage.searchFor(itemType);
            test = extent.createTest("Search for Cakes", "Searched 'cakes' and pressed Enter");
            test.pass("Successfully searched for 'cakes' with Enter.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Search_Cakes"));


            // Step 2: Verify Price and Select Product
            searchResultsPage.verifyAndSelectProduct(expectedPrice);
            test = extent.createTest("Verify the product", "Checked whether the 'Bubble Pop Birthday Cake Tower' is selected");
            test.pass("Verified product price and selected the product.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Select_Product"));

            // Step 3: Verify Price and Add to Cart
            itemDetailsPage.verifyPriceAndAddToCart(expectedPrice);
            test = extent.createTest("Add to cart", "Added ' Bubble Pop Birthday Cake Tower' to cart");
            test.pass("Verified item price and added to cart.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Add_To_Cart"));

            // Step 4: View Cart
            addToCartPopupPage.viewCart();
            test = extent.createTest("View cart", "Verified 'Bubble Pop Birthday Cake Tower' is in cart");
            test.pass("Navigated to cart successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "View_Cart"));

            // Write back to Excel
            excel.setCellData(1, 3, "Successfully searched HBubble Pop Birthday Cake Tower Set and added to cart !!!", excelFilePath);
        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
