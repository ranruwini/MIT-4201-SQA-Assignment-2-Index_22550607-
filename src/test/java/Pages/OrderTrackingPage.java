package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderTrackingPage {
    WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div[3]/div/form/table/tbody/tr/td/div/div/div[2]/input")
    WebElement trackingNumberInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div[3]/div/form/table/tbody/tr/td/div/div/input")
    WebElement showOrderStatusButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div[3]/div/form/table/tbody/tr/td/div/div/div[4]/font")
    WebElement invalidReferenceMessage;

    public OrderTrackingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to enter tracking number
    public void enterTrackingNumber(String trackingNumber) {
        trackingNumberInput.clear();
        trackingNumberInput.sendKeys(trackingNumber);
    }

    // Method to click 'Show My Order Status'
    public void clickShowOrderStatus() {
        showOrderStatusButton.click();
    }

    // Method to verify invalid reference message
    public boolean isInvalidReferenceMessageDisplayed() {
        return invalidReferenceMessage.isDisplayed() &&
                invalidReferenceMessage.getText().contains("You have entered an invalid reference number");
    }
}
