package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;

public class HomePage {
    WebDriver driver;

    @FindBy(id = "search_bar_id")
    WebElement searchBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div[2]/a") // Tracking icon XPath
    WebElement trackingIcon;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div[3]") // User icon
    WebElement userIcon;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to search for cakes
    public void searchFor(String keyword) {
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER); // Press Enter after typing
    }

    // Method to click on the tracking icon
    public void clickOnTrackingIcon() {
        trackingIcon.click();
    }

    // Method to click on the user icon
    public void clickOnUserIcon() {
        userIcon.click();
    }
}
