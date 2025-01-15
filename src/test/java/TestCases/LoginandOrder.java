package TestCases;

import Base.TestInitializer;
import Pages.AccountDetailsPage;
import Pages.HomePage;
import Pages.OrderDetailsPage;
import Utils.ScreenCapture;
import Utils.ExcelDataHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class LoginandOrder  extends TestInitializer {

    private ExtentTest test;

    @BeforeTest
    public void setup() {
        setUpBrowser();
        assert driver != null : "WebDriver initialization failed!";
    }

    @Test
    public void loginAndPlaceOrderTest() {
        HomePage homePage = new HomePage(driver);
        AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

        // Read data from Excel
        String email = excel.getCellData(7, 3);
        String password = excel.getCellData(7, 4);
        String expectedPrice = excel.getCellData(9, 1);

        try {
            // Step 1: Click User Icon
            homePage.clickOnUserIcon();
            test = extent.createTest("Click User Icon", "Clicking user icon on homepage");
            test.pass("User icon clicked successfully.").addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "User_Icon_Click"));

            // Step 2: Login
            test = extent.createTest("Login Test", "Enter login credentials and click login");
            accountDetailsPage.enterEmail(email);
            accountDetailsPage.enterPassword(password);
            accountDetailsPage.clickLogin();
            test.pass("Login successful.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Login_Success"));

            // Step 3: Place a New Order
            test = extent.createTest("Place New Order", "Click on Place New Order button");
            accountDetailsPage.clickPlaceNewOrder();
            test.pass("Navigated to Place New Order successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Place_Order"));

            // Step 4: Select Fashion Category
            test = extent.createTest("Select Fashion Category", "Click on Fashion category");
            orderDetailsPage.selectFashionCategory();
            test.pass("Fashion category selected.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Select_Fashion"));

            // Step 5: Select Item Based on Price
            test = extent.createTest("Select Item", "Select an item with price " + expectedPrice);
            orderDetailsPage.selectItem(expectedPrice);
            test.pass("Item selected successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Select_Item"));

            // Step 6: Add to Cart
            test = extent.createTest("Add to Cart", "Click Add to Cart button");
            orderDetailsPage.clickAddToCart();
            test.pass("Item added to cart.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Add_To_Cart"));

            // Step 7: Continue Shopping
            test = extent.createTest("Continue Shopping", "Click Continue Shopping button");
            orderDetailsPage.clickContinueShopping();
            test.pass("Continued shopping successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Continue_Shopping"));

            // Write back to Excel
            excel.setCellData(9, 4, "Order placed successfully", excelFilePath);

        } catch (Exception e) {
            if (test != null) {
                test.fail("Test failed due to: " + e.getMessage())
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Test_Failure"));
            }
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
