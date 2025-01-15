package TestCases;

import Base.TestInitializer;
import Pages.AccountDetailsPage;
import Pages.HomePage;
import Utils.ScreenCapture;
import Utils.ExcelDataHandler;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class CreateAccountAndVerify extends TestInitializer {

    private ExtentTest test;

    @BeforeClass
    public void setup() {
        setUpBrowser();
        assert driver != null : "WebDriver initialization failed!";
    }


    @Test
    public void createAccountTest() {
        HomePage homePage = new HomePage(driver);
        AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

        // Read data from Excel
        String firstName = excel.getCellData(15, 1);
        String lastName = excel.getCellData(15, 2);
        String email = excel.getCellData(15, 3);
        String password = excel.getCellData(15, 4);
        String confirmPassword = excel.getCellData(15, 4);

        try {
            // Step 1: Click User Icon
            homePage.clickOnUserIcon();
            test = extent.createTest("Click User Icon", "Clicking user icon on homepage");
            test.pass("User icon clicked successfully.").addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "User_Icon_Click"));

            // Step 2: Navigate to Create Account
            test = extent.createTest("Navigate to Create Account", "Click Create Account button");
            accountDetailsPage.clickCreateAccount();
            test.pass("Create account button clicked successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Create_Account_Click"));

            // Step 3: Fill Account Details
            test = extent.createTest("Fill Account Details", "Filling account details from Excel");
            accountDetailsPage.fillAccountDetails(firstName, lastName, email, password, confirmPassword);
            test.pass("Account details filled successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Fill_Account_Details"));

            // Step 4: Finalize Account Creation
            test = extent.createTest("Finalize Account Creation", "Click final create account button");
            accountDetailsPage.clickFinalCreateAccount();
            test.pass("Final create account button clicked successfully.")
                    .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Finalize_Account_Creation"));

            // Step 5: Verify Account Creation
            test = extent.createTest("Verify Account Creation", "Verify account creation success message");
            if (accountDetailsPage.verifyAccountCreationSuccess()) {
                accountDetailsPage.clickGoToMyAccount();
                test.pass("Account creation success message displayed and navigated successfully.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Account_Creation_Success"));
            } else {
                test.fail("Account creation failed.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Account_Creation_Failure"));
            }

            // Write back to Excel
            excel.setCellData(15, 5, "Account created successfully", excelFilePath);
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
