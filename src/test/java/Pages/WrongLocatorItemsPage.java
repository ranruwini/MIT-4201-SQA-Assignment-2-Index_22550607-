package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WrongLocatorItemsPage {
    WebDriver driver;

    // Locators using @FindBy annotation
    @FindBy(xpath = "/html/body/div[5]/div/a[6]")
    WebElement clothingLink;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[6]/a/div[2]/div[1]")
    WebElement specificItem;

    @FindBy(id = "new")
    WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div[1]")
    WebElement viewCartButton;

    @FindBy(id = "wrong")
    WebElement checkoutButton;

    // Constructor
    public WrongLocatorItemsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on Clothing
    public void clickOnClothing() {
        clothingLink.click();
    }

    // Method to click on a Specific Item
    public void clickOnSpecificItem() {
        specificItem.click();
    }

    // Method to click on Add to Cart Button
    public void clickOnAddToCart() {
        addToCartButton.click();
    }

    // Method to click on View Cart
    public void clickOnViewCart() {
        viewCartButton.click();
    }

    // Method to click on Checkout
    public void clickOnCheckout() {
        checkoutButton.click();
    }

    // Verification Method (Optional)
    public boolean isViewCartButtonVisible() {
        return viewCartButton.isDisplayed();
    }


    // Verification Method (Optional)
    public boolean isCheckoutButtonVisible() {
        return checkoutButton.isDisplayed();
    }
}
