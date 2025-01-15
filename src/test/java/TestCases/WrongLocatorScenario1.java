package TestCases;

import Base.TestInitializer;
import Pages.WrongLocatorItemsPage;
import Utils.ExcelDataHandler;
import Utils.ScreenCapture;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WrongLocatorScenario1 extends TestInitializer {

    private ExtentTest test;

    @BeforeTest
    public void setup() {
        setUpBrowser();
        assert driver != null : "WebDriver initialization failed!";
    }

    @Test
    public void testWrongLocatorItems() {
        WrongLocatorItemsPage wrongLocatorItemsPage = new WrongLocatorItemsPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

        try {
            // Step 1: Click on Clothing
            test = extent.createTest("Click on Clothing", "Navigating to Clothing section");
            wrongLocatorItemsPage.clickOnClothing();
            test.pass("Successfully clicked on Clothing section.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Clothing_Click"));

            // Step 2: Click on a Specific Item
            test = extent.createTest("Click on Specific Item", "Selecting a specific item");
            wrongLocatorItemsPage.clickOnSpecificItem();
            test.pass("Successfully clicked on the specific item.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Item_Click"));

            // Step 3: Click Add to Cart
            test = extent.createTest("Click Add to Cart", "Attempting to add item to cart");
            wrongLocatorItemsPage.clickOnAddToCart();
            test.pass("Clicked on Add to Cart button.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "AddToCart_Click"));

            // Verification: Check if Add to Cart button is visible
            if (wrongLocatorItemsPage.isViewCartButtonVisible()) {
                test.pass("Add to Cart button is visible after clicking.");
            } else {
                String screenshotPath = ScreenCapture.takeScreenshot(driver, "AddToCart_NotVisible");
                test.fail("Add to Cart button is NOT visible after clicking.")
                        .addScreenCaptureFromPath(screenshotPath);
                // Write back to Excel
                excel.setCellData(13, 5, "Account created successfully", excelFilePath);
                throw new AssertionError("Add to Cart button is not visible.");
            }

        } catch (Exception e) {
            if (test != null) {
                String screenshotPath = ScreenCapture.takeScreenshot(driver, "Test_Failure");
                test.fail("Test failed due to: " + e.getMessage())
                        .addScreenCaptureFromPath(screenshotPath);
            }
            throw e;
        }
    }
}
