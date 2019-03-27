package com.iprismtech.tahyba.factories;

import android.support.annotation.IntDef;


import com.iprismtech.tahyba.MainActivity;
import com.iprismtech.tahyba.activities.AboutUsActivity;
import com.iprismtech.tahyba.activities.AppFeedBackActivity;
import com.iprismtech.tahyba.activities.ContactUsActivity;
import com.iprismtech.tahyba.activities.EnterMobileNumberActivity;
import com.iprismtech.tahyba.activities.ProfileActivity;
import com.iprismtech.tahyba.activities.ShopsSearchActivity;
import com.iprismtech.tahyba.activities.UserRatingFeedBackAcrivity;
import com.iprismtech.tahyba.activities.LocationGetActivity;
import com.iprismtech.tahyba.activities.LoginActivity;
import com.iprismtech.tahyba.activities.OTPVerficationForgotPasswordActivity;
import com.iprismtech.tahyba.activities.OTPVerificationActivity;
import com.iprismtech.tahyba.activities.ResetPasswordActivity;
import com.iprismtech.tahyba.activities.ShopDetailsActivity;
import com.iprismtech.tahyba.activities.ShopsListActivity;
import com.iprismtech.tahyba.activities.SplashScreenActivity;
import com.iprismtech.tahyba.activities.UserSignUpActivity;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.ABOUT_US_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.APP_FEEDBACK_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.CONTACT_US_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.ENTER_MOBILE_NUMBER_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.LOCATION_GET_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.MAIN_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.OTP_VERIFICATION_PASSWORD_FORGOT_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.OTP_VERIFICATION_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.RESET_PASSWORD_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.SHOPS_LISTS_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.SHOPS_SEARCH_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.SHOP_DETAILS_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.SPLASH_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.USER_FEEDBACK_RATING_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.USER_PROFILE_ACTIVITY_SCREEN;
import static com.iprismtech.tahyba.factories.ViewFactory.ScreenIds.USER_SIGNUP_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {


    @Retention(RetentionPolicy.CLASS)
    @IntDef({SPLASH_SCREEN, MAIN_SCREEN, LOGIN_SCREEN, USER_SIGNUP_SCREEN, OTP_VERIFICATION_SCREEN, LOCATION_GET_SCREEN,
            OTP_VERIFICATION_PASSWORD_FORGOT_SCREEN, RESET_PASSWORD_SCREEN, SHOPS_LISTS_SCREEN, SHOP_DETAILS_SCREEN,
            USER_FEEDBACK_RATING_SCREEN, USER_PROFILE_ACTIVITY_SCREEN, CONTACT_US_SCREEN, ABOUT_US_SCREEN, APP_FEEDBACK_SCREEN,
            SHOPS_SEARCH_SCREEN,ENTER_MOBILE_NUMBER_SCREEN
    })
    public @interface ScreenIds {

        int SPLASH_SCREEN = 1001;
        int MAIN_SCREEN = 1002;
        int LOGIN_SCREEN = 1003;
        int USER_SIGNUP_SCREEN = 1004;
        int OTP_VERIFICATION_SCREEN = 1005;
        int LOCATION_GET_SCREEN = 1006;
        int OTP_VERIFICATION_PASSWORD_FORGOT_SCREEN = 1007;
        int RESET_PASSWORD_SCREEN = 1008;
        int SHOPS_LISTS_SCREEN = 1009;
        int SHOP_DETAILS_SCREEN = 1010;
        int USER_FEEDBACK_RATING_SCREEN = 1011;
        int USER_PROFILE_ACTIVITY_SCREEN = 1012;
        int CONTACT_US_SCREEN = 1013;
        int ABOUT_US_SCREEN = 1014;
        int APP_FEEDBACK_SCREEN = 1015;
        int SHOPS_SEARCH_SCREEN = 1016;
        int ENTER_MOBILE_NUMBER_SCREEN = 1017;


    }


    /**
     * Reference of Application Controller
     */
    private ApplicationController mApplicationController = null;

    /**
     * Constructor
     */
    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }

    /**
     * This function should only be used when whole application is made by
     * multiple activity.
     *
     * @param id
     * @return Activity class
     */
    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {
            case SPLASH_SCREEN: {
                return SplashScreenActivity.class;
            }
            case MAIN_SCREEN: {
                return MainActivity.class;
            }
            case LOGIN_SCREEN: {
                return LoginActivity.class;
            }
            case USER_SIGNUP_SCREEN: {
                return UserSignUpActivity.class;
            }
            case OTP_VERIFICATION_SCREEN: {
                return OTPVerificationActivity.class;
            }
            case LOCATION_GET_SCREEN: {
                return LocationGetActivity.class;
            }
            case OTP_VERIFICATION_PASSWORD_FORGOT_SCREEN: {
                return OTPVerficationForgotPasswordActivity.class;
            }
            case RESET_PASSWORD_SCREEN: {
                return ResetPasswordActivity.class;
            }
            case SHOPS_LISTS_SCREEN: {
                return ShopsListActivity.class;
            }
            case SHOP_DETAILS_SCREEN: {
                return ShopDetailsActivity.class;
            }
            case USER_FEEDBACK_RATING_SCREEN: {
                return UserRatingFeedBackAcrivity.class;
            }
            case USER_PROFILE_ACTIVITY_SCREEN: {
                return ProfileActivity.class;
            }
            case CONTACT_US_SCREEN: {
                return ContactUsActivity.class;
            }
            case ABOUT_US_SCREEN: {
                return AboutUsActivity.class;
            }
            case APP_FEEDBACK_SCREEN: {
                return AppFeedBackActivity.class;
            }
            case SHOPS_SEARCH_SCREEN: {
                return ShopsSearchActivity.class;
            }  case ENTER_MOBILE_NUMBER_SCREEN: {
                return EnterMobileNumberActivity.class;
            }
            default:
                throw new IllegalStateException("Invalid screen id");
        }

    }


    /**
     * This function should only be used when whole application is made by
     * multiple Fragment.
     *
     * @param id
     * @return Fragment class
     */
    public static Class getFragmentClass(@ScreenIds int id) {

        switch (id) {
            //todo logic for fragments are same

            default:
                throw new IllegalStateException("Invalid Event id");

        }

    }
}