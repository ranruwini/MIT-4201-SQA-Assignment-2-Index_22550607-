package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ItemDetailsPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='pricelbl']")
    WebElement priceLabel;

    @FindBy(id = "addtocartbutton")
    WebElement addToCartButton;

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPriceAndAddToCart(String expectedPrice) {
        String actualPrice = priceLabel.getText().trim();

        // Normalize case and spaces for comparison
        String normalizedActualPrice = actualPrice.replace(" ", "").toLowerCase();
        String normalizedExpectedPrice = expectedPrice.replace(" ", "").toLowerCase();

        Assert.assertEquals(normalizedActualPrice, normalizedExpectedPrice, "Item price mismatch!");
        addToCartButton.click();
    }
}
