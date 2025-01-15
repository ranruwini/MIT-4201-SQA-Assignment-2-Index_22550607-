package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPopupPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='popup__content']/div[2]/div/div[3]/a[2]")
    WebElement viewCartButton;

    public AddToCartPopupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void viewCart() {
        viewCartButton.click();
    }
}
