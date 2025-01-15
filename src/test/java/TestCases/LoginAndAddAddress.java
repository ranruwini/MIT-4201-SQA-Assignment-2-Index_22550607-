package TestCases;

import Base.TestInitializer;
import Pages.AccountDetailsPage;
import Pages.AddressBookPage;
import Pages.HomePage;
import Utils.ScreenCapture;
import Utils.ExcelDataHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class LoginAndAddAddress extends TestInitializer {

        private ExtentTest test;

        @BeforeTest
        public void setup() {
            setUpBrowser();
            assert driver != null : "WebDriver initialization failed!";
        }

        @Test
        public void createAccountAndAddAddressTest() {
            HomePage homePage = new HomePage(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            AddressBookPage addressBookPage = new AddressBookPage(driver);

            String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
            String sheetName = "Data";
            ExcelDataHandler excel = new ExcelDataHandler(excelFilePath, sheetName);

            String email = excel.getCellData(15, 3);
            String password = excel.getCellData(15, 4);

            // Address Data
            String addressName = excel.getCellData(11, 1);
            String addressDetails = excel.getCellData(11, 2);
            String city = excel.getCellData(11, 3);
            String country = excel.getCellData(11, 4);
            String phone = excel.getCellData(11, 5);

            try {
                // Step 1: Click User Icon
                homePage.clickOnUserIcon();
                test = extent.createTest("Click User Icon", "Clicking user icon on homepage");
                test.pass("User icon clicked successfully.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "User_Icon_Click"));


                // Step 2: Login
                test = extent.createTest("Login Test", "Enter login credentials and click login");
                accountDetailsPage.enterEmail(email);
                accountDetailsPage.enterPassword(password);
                accountDetailsPage.clickLogin();
                test.pass("Login successful.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Login_Success"));

                // Step 3: Navigate to Address Book
                accountDetailsPage.clickMyAddressBook();
                test = extent.createTest("Navigate to Address Book", "Navigating to address book section");
                test.pass("Navigated to address book successfully.")
                        .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Address_Book_Navigate"));

                // Step 4: Add New Address
                addressBookPage.fillAddressDetails(addressName, addressDetails, city, country, phone);
                addressBookPage.clickAddAddress();
                test = extent.createTest("Add New Address", "Adding new address from Excel data");

                if (addressBookPage.verifyAddressAdded()) {
                    test.pass("Address added successfully.")
                            .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Address_Added"));
                } else {
                    test.fail("Failed to add address.")
                            .addScreenCaptureFromPath(ScreenCapture.takeScreenshot(driver, "Address_Failed"));
                }

                // Write Back to Excel
                excel.setCellData(11, 6, "Address added successfully", excelFilePath);

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

