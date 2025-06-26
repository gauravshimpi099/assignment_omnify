package org.example.TestCases;

import org.example.Utility.BaseUtility;
import org.example.WebPages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomepageTest extends BaseUtility {

    HomePage getHomePage;

    @BeforeClass
    public void preCondition() {
        getHomePage = new HomePage(driver);
    }

    @Test(priority = 1, description = "Verify booking")
    public void verifyBooking() {

        // Updating city and date
        getHomePage.enterCity("NewYork");
        getHomePage.selectCheckinDate("10");
        getHomePage.selectCheckoutDate("15");
        getHomePage.clickOnSearch();

        // closing this window and navigating to another one.
        closeWindow();
        switchToWindowByTitle("Agoda | Hotels in New York (NY) | Best Price Guarantee!");

        // To get coupon clicking on See all coupons & switching to that window
        getHomePage.clickOnSeeAllCoupons();
        closeWindow();
        switchToWindowByTitle("Agoda Promo Codes: Discounts + Coupons on Hotels Updated Daily");

        // Select Coupon text for 5% OFF
        getHomePage.selectCoupon("Up to ₹2,500 Off Hotels");
        switchToWindowByTitle("Agoda Official Site | Free Cancellation & Booking Deals | Over 2 Million Hotels");
        waitFor(2);

        // Verification of coupon is collected
        Assert.assertTrue(getHomePage.verifiedCouponCollectedSuccessfully());
        getHomePage.closeCouponCollectedPopup();

        // Switching to window
        switchToWindowByTitle("Agoda Official Site | Free Cancellation & Booking Deals | Over 2 Million Hotels");

        /** After updating coupon again user lands on search hotel
         *  booking primary page with pre-filled values. so clicking on search */
        getHomePage.clickOnSearch();
        waitFor(2);
        switchToWindowByTitle("Agoda | Hotels in New York (NY) | Best Price Guarantee!");

        // Clicking on first hotel, new window opens, switched to that.
        String getFirstHotelName = getHomePage.getFirstHotelName();
        getHomePage.bookFirstHotel();
        waitFor(3);
        switchToWindowByTitle(getFirstHotelName);
        getHomePage.setClickOnBookBtn();

        // Switching to booking form where updating personal info
        waitFor(3);
        switchToWindowByTitle("Booking Form");
        getHomePage.clickOnContinueBooking();
        getHomePage.enterFirstName("Gaurav");
        getHomePage.enterLastName("shimpi");
        getHomePage.enterEmail("gaurav.shimpi@gmail.com");
        getHomePage.enterMobileNumber("9876543210");
        getHomePage.selectNonSmokingRoom();
        getHomePage.selectLargeBed();
        getHomePage.clickFinalStep();

        // assertions for payment page displayed.
        Assert.assertTrue(getHomePage.isPaymentFillUpFormDisplay());
    }
}
