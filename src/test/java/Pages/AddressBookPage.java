package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class AddressBookPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[1]/td[2]/input")
    WebElement addressNameInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[2]/td[2]/textarea")
    WebElement addressDetailsInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[3]/td[2]/span/span[1]/span/span[2]")
    WebElement cityDropdownArrow;

    @FindBy(xpath = "/html/body/span/span/span[1]/input")
    WebElement citySearchBox;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[4]/td[2]/input")
    WebElement postalCodeInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[5]/td[2]/input")
    WebElement phoneNumberInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/form/table/tbody/tr[8]/td[2]/input")
    WebElement addAddressButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div/div/b")
    WebElement successMessage;

    public AddressBookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
        PageFactory.initElements(driver, this);
    }

    public void fillAddressDetails(String name, String details, String city, String postalCode, String phone) {
        addressNameInput.sendKeys(name);
        addressDetailsInput.sendKeys(details);

        // Step 1: Click the dropdown arrow
        cityDropdownArrow.click();

        // Step 2: Wait for the search box to appear and type city
        wait.until(ExpectedConditions.visibilityOf(citySearchBox));
        citySearchBox.sendKeys(city);

        // Step 3: Select the city from the results
        WebElement cityOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + city + "')]")
        ));
        cityOption.click();

        postalCodeInput.sendKeys(postalCode);
        phoneNumberInput.sendKeys(phone);
    }
    public void clickAddAddress() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addAddressButton);
        js.executeScript("arguments[0].click();", addAddressButton);
    }



    public boolean verifyAddressAdded() {
        return successMessage.isDisplayed();
    }
}
