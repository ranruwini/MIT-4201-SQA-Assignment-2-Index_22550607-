package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {
    WebDriver driver;

    @FindBy(xpath = "/html/body/div[5]/div/a[6]")
    WebElement fashionCategory;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[5]/a/div[2]/div[1]")
    WebElement specificItem;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[5]/a/div[2]/div[2]/div[1]/span")
    WebElement itemPrice;

    @FindBy(id = "addtocartbutton")
    WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='continue_shopping_popup']")
    WebElement continueShoppingButton;

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFashionCategory() {
        fashionCategory.click();
    }

    public void selectItem(String expectedPrice) {
        if (itemPrice.getText().equals(expectedPrice)) {
            specificItem.click();
        }
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }
}

