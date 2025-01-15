package TestCases;

import Pages.HomePage;
import Pages.OrderTrackingPage;
import Utils.ScreenCapture;
import Utils.ExcelDataHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import Base.TestInitializer;

public class OrderTracking extends TestInitializer{

    @BeforeClass
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void testInvalidTrackingNumber() throws IOException {
        HomePage homePage = new HomePage(driver);
        OrderTrackingPage orderTrackingPage = new OrderTrackingPage(driver);


        // Excel Initialization
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

        // Read data
        String invalidTrackingNumber = excel.getCellData(5, 2);

        try {
        // Step 1: Click on tracking icon
        homePage.clickOnTrackingIcon();
        test = extent.createTest("Click to track orders", "Clicked tracking icon");
        test.pass("Successfully clicked the tracking icon.")
                .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "click_tracking_icon"));

        // Enter invalid tracking number and check status
        orderTrackingPage.enterTrackingNumber(invalidTrackingNumber);
        orderTrackingPage.clickShowOrderStatus();
        test = extent.createTest("Enter a random tracking number", "Entered a random tracking number");
        test.pass("Successfully entered a random tracking number.")
                .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "enter_tracking_number"));

        // Step 3: Verify the error message
        Assert.assertTrue(orderTrackingPage.isInvalidReferenceMessageDisplayed(),
                "Invalid reference message was not displayed!");
        test = extent.createTest("Check the error message", "Checked for the expected error message");
        test.pass("Successfully received the expected error message")
                .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "expected_error_message"));


            // Write back to Excel
            excel.setCellData(5, 4, "Order tracking search Passed", excelFilePath);

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }


}
