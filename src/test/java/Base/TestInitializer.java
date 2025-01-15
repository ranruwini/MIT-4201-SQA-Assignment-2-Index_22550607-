package Base;

import Utils.ScreenCapture;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestInitializer {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private String reportFileName;

    @BeforeSuite
    public void setUp() {
        // Create a unique file name for the Extent Report
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = "ExtentReport_" + timestamp + ".html"; // Default report name

        // Set up ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Set a custom name for the Extent Report.
     * @param customName The custom name for the report.
     */
    public void setReportName(String customName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = customName + "_" + timestamp + ".html";

        // Reinitialize ExtentSparkReporter with the new name
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Start a test in Extent Report with a given name.
     *
     * @param testName Name of the test case.
     */
    public void startTest(String testName) {
        test = extent.createTest(testName);
    }

    @BeforeClass
    public void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        driver.get("https://www.kapruka.com/");
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenCapture.takeScreenshot(driver, result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();

    }

}