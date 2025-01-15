package TestCases;

import Base.TestInitializer;
import Pages.WrongLocatorItemsPage;
import Utils.ScreenCapture;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WrongLocatorScenario2 extends TestInitializer {

    private ExtentTest test;

    @BeforeTest
    public void setup() {
        setUpBrowser();
        assert driver != null : "WebDriver initialization failed!";
    }

    @Test
    public void testViewCartAndCheckout() {
        WrongLocatorItemsPage wrongLocatorItemsPage = new WrongLocatorItemsPage(driver);

        try {
            // Step 1: Click on View Cart
            test = extent.createTest("Click on View Cart", "Navigating to the cart view page");
            wrongLocatorItemsPage.clickOnViewCart();
            test.pass("Successfully clicked on View Cart.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "ViewCart_Click"));

            // Step 2: Click on Checkout
            test = extent.createTest("Click on Checkout", "Attempting to click the Checkout button");
            try {
                wrongLocatorItemsPage.clickOnCheckout();
                test.pass("Successfully clicked on Checkout button.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Checkout_Click"));
            } catch (Exception e) {
                test.fail("Failed to click on Checkout button: " + e.getMessage())
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Checkout_Click_Failure"));
            }

            // Verification: Check if Checkout Button is visible
            if (!wrongLocatorItemsPage.isCheckoutButtonVisible()) {
                String screenshotPath = ScreenCapture.takeScreenshot(driver, "Checkout_Not_Visible");
                test.fail("Checkout button is not visible.")
                        .addScreenCaptureFromPath(screenshotPath);
            } else {
                test.pass("Checkout button is visible.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Checkout_Visible"));
            }

        } catch (Exception e) {
            if (test != null) {
                test.fail("Test failed due to: " + e.getMessage())
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Test_Failure"));
            }
            throw e;
        }
    }
}
