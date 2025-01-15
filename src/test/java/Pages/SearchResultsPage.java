package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchResultsPage {
    WebDriver driver;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[20]/a/div/div[2]/div[1]/span")
    WebElement productPrice;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[20]/a")
    WebElement productLink;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyAndSelectProduct(String expectedPrice) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        String actualPrice = productPrice.getText().trim();
        Assert.assertEquals(actualPrice.trim().toLowerCase(), expectedPrice.trim().toLowerCase(), "Product price mismatch!");
        productLink.click();
    }
}
