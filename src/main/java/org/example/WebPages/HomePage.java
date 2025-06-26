package org.example.WebPages;

import org.example.Utility.BaseUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.MessageFormat;

public class HomePage extends BaseUtility {

    // Constructor
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // 1. Search city input box
    @FindBy(css = "input[aria-label=\"Enter a destination or property\"]")
    WebElement cityInput;

    // 2. Select city from Search city result box
    @FindBy(xpath = "//div[@class=\"Popup__content Popup__content_Occupancy\"]/ul/li[1]")
    WebElement selectCity;

    // 3. Search button
    @FindBy(xpath = "//button[@data-selenium=\"searchButton\"]")
    WebElement searchButton;

    // 4. Select hotel from results
    @FindBy(xpath = "(//li[@data-selenium=\"hotel-item\"])[1]/div/a/div/div/div/div[@data-element-name=\"property-card-info\"]")
    WebElement firstHotelBookButton;

    // 5. Get first hotel name
    @FindBy(xpath = "(//li[@data-selenium=\"hotel-item\"])[1]/div/a/div/div/div[2]/div/header/div/h3")
    WebElement getFirstHotelName;

    // 6. get Final Price For First Hotel before coupon
    @FindBy(xpath = "(//span[@data-selenium=\"display-price\"])[1]")
    WebElement getFinalPriceForFirstHotelBeforeCoupon;

    // 7. Room type selection dropdown
    @FindBy(xpath = "(//button[@data-element-name=\"book-btn\"])[1]")
    WebElement clickOnBookBtn;

    // 8. Activate Coupon
    @FindBy(xpath = "//span[text()='Activate coupon']")
    WebElement activateCoupon;

    // 9. see all Coupons
    @FindBy(xpath = "//a[@href=\"https://www.agoda.com/deals?cid=1897344\"]")
    WebElement seeAllCoupons;

    // 10. After Selecting coupon code
    @FindBy(xpath = "//h2[text()='Coupon successfully collected! ']")
    WebElement afterSelectingCouponCodeFlagLine;

    // 11. After Selecting coupon code
    @FindBy(xpath = "//h2[contains(@class,'DusrU')]")
    WebElement couponCollectedSuccessfullyMsg;

    // 12. After Selecting coupon code close popup
    @FindBy(css = "button[aria-label=\"close\"]")
    WebElement couponCollectedSuccessfullyClosePopUp;

    // 13. Proceed to payment
    @FindBy(css = "button[data-element-name=\"bf-user-drop-off-alert-continue-button\"]")
    WebElement continueBookingBtn;

    @FindBy(css = "input[id='contact.contactFirstName']")
    WebElement firstNameInput;

    @FindBy(css = "input[id=\"contact.contactLastName\"]")
    WebElement lastNameInput;

    @FindBy(css = "input[id=\"contact.contactEmail\"]")
    WebElement emailInput;

    @FindBy(css = "input[id=\"contact.contactPhoneNumber\"]")
    WebElement mobileNumberInput;

    @FindBy(css = "button[data-action=\"nextPage\"]")
    WebElement nextOrFinalStepButton;

    @FindBy(css = "label[data-testid=\"NonSmoking\"]")
    WebElement noSmokingRadioBtn;

    @FindBy(css = "button[data-action=\"book\"]")
    WebElement bookBtn;

    @FindBy(css = "label[data-testid=\"LargeBed\"]")
    WebElement largeBedRadioBtn;

    public void clickOnContinueBooking() {
        waitForElementToBeDisplay(continueBookingBtn);
        continueBookingBtn.click();
    }

    public void enterFirstName(String firstName) {
        waitFor(2);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        waitForElementToBeDisplay(lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        waitForElementToBeDisplay(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterMobileNumber(String mobileNumber) {
        waitForElementToBeDisplay(mobileNumberInput);
        mobileNumberInput.clear();
        mobileNumberInput.sendKeys(mobileNumber);
    }

    public void clickFinalStep() {
        waitForElementToBeDisplay(nextOrFinalStepButton);
        nextOrFinalStepButton.click();
    }

    // select the Non-Smoking radio option
    public void selectNonSmokingRoom() {
        if (!noSmokingRadioBtn.isSelected()) {
            noSmokingRadioBtn.click();
        }
    }

    // select the large btns radio option
    public void selectLargeBed() {
        if (!largeBedRadioBtn.isSelected()) {
            largeBedRadioBtn.click();
        }
    }

    public void enterCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);

        // Selecting city from suggestion dropdown
        waitForElementToBeDisplay(selectCity);
        selectCity.click();
    }


    public String getFirstHotelName() {
        waitForElementToBeDisplay(getFirstHotelName);
        return getFirstHotelName.getText();
    }


    public void selectCheckinDate(String startDate) {
        String path = MessageFormat.format("//div[text()=''July 2025'']/parent::div[@role=\"grid\"]/descendant::span[text()=''{0}'']", startDate);
        WebElement ele = driver.findElement(By.xpath(path));
        ele.click();
    }

    public void selectCheckoutDate(String endDate) {
        String path = MessageFormat.format("//div[text()=''July 2025'']/parent::div[@role=\"grid\"]/descendant::span[text()=''{0}'']", endDate);
        WebElement ele = driver.findElement(By.xpath(path));
        ele.click();
    }

    public void selectCoupon(String couponText) {
        String path = MessageFormat.format("//div[@title=''{0}'']/ancestor::div[contains(@class,''bdjxkd'')]/div/button[@title=''CLAIM COUPON'']", couponText);
        WebElement ele = driver.findElement(By.xpath(path));
        ele.click();
    }


    public void clickOnSearch() {
        searchButton.click();
    }

    public void bookFirstHotel() {
        waitForElementToBeDisplay(firstHotelBookButton);
        firstHotelBookButton.click();
    }

    public void setClickOnBookBtn() {
        clickOnBookBtn.click();
    }

    public void clickOnSeeAllCoupons() {
        waitFor(5);
        waitForElementToBeDisplay(seeAllCoupons);
        seeAllCoupons.click();
    }

    public boolean verifyCouponIsApplied() {
        waitForElementToBeDisplay(afterSelectingCouponCodeFlagLine);
        return afterSelectingCouponCodeFlagLine.isDisplayed();
    }

    public boolean verifiedCouponCollectedSuccessfully() {
        waitForElementToBeDisplay(couponCollectedSuccessfullyMsg);
        return couponCollectedSuccessfullyMsg.isDisplayed();
    }

    public void closeCouponCollectedPopup() {
        waitForElementToBeDisplay(couponCollectedSuccessfullyClosePopUp);
        couponCollectedSuccessfullyClosePopUp.click();
    }

    public boolean isPaymentFillUpFormDisplay() {
        waitFor(3);
        scrollToElement(bookBtn);
        return bookBtn.isDisplayed();
    }


}
