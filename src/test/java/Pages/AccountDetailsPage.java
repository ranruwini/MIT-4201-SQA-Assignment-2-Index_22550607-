package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDetailsPage {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Create Account']")
    WebElement createAccountButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[1]/td[2]/input")
    WebElement firstNameInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[2]/td[2]/input")
    WebElement lastNameInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[3]/td[2]/input")
    WebElement emailInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[4]/td[2]/input")
    WebElement passwordInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[5]/td[2]/input")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/table/tbody/tr[6]/td[2]/button")
    WebElement finalCreateAccountButton;

    @FindBy(xpath = "//h3[contains(text(),'Congratulations')]")
    WebElement successMessage;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/form/a/button")
    WebElement viewAccountButton;

    @FindBy(xpath = "//*[@id=\"exampleInputEmail1\"]")
    WebElement loginEmailInput;

    @FindBy(xpath = "//*[@id=\"exampleInputPassword1\"]")
    WebElement loginPasswordInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/form/input[3]")
    WebElement loginButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/table/tbody/tr/td[1]/a[4]")
    WebElement placeNewOrderButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div/div[2]/div/table/tbody/tr/td[1]/a[3]/div/div")
    WebElement myAddressBookButton;

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAccount() {
        createAccountButton.click();
    }

    public void fillAccountDetails(String firstName, String lastName, String email, String password, String confirmPassword) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void clickFinalCreateAccount() {
        finalCreateAccountButton.click();
    }

    public boolean verifyAccountCreationSuccess() {
        return successMessage.isDisplayed();
    }

    public void clickGoToMyAccount() {
        viewAccountButton.click();
    }

    public void enterEmail(String email) {
        loginEmailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        loginPasswordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickPlaceNewOrder() {
        placeNewOrderButton.click();
    }

    public void clickMyAddressBook() {
        myAddressBookButton.click();
    }
}
