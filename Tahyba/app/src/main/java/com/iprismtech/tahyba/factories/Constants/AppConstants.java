package com.iprismtech.tahyba.factories.Constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Prasad on 7/5/2017.
 */
public interface AppConstants {

    String LOG_CAT = ">>logs : ";

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({


    })
    @interface EventIds {


        int LAUNCH_SPLASH_SCREEN = 101;
        int LAUNCH_MAIN_SCREEN = 102;
        int LAUNCH_LOGIN_SCREEN = 103;
        int USER_SIGNUP_SCREEN = 104;
        int LAUNCH_OTPVERIFICATION_SCREEN = 105;
        int LAUNCH_LOCATION_GET_SCREEN = 106;
        int LAUNCH_FORGOT_PASSWORD_OTP_VERIFICATIONSCREEN = 107;
        int LAUNCH_RESET_PASSWORD_SCREEN = 108;
        int LAUNCH_SHOPS_LIST_SCREEN = 109;
        int LAUNCH_SHOP_DETAILS_SCREEN = 110;
        int LAUNCH_USER_RATING_FEEDBACK_SCREEN = 111;
        int LAUNCH_PROFILE_ACTIVITY_SCREEN = 112;
        int LAUNCH_CONTACT_US_SCREEN = 113;
        int LAUNCH_ABOUT_US_SCREEN = 114;
        int LAUNCH_APP_FEEDBACK_US_SCREEN = 115;
        int LAUNCH_SHOPS_SEARCH_SCREEN = 116;
        int LAUNCH_ENTER_MOBILE_NUMBER_SCREEN = 117;

    }
    public static final long INTERVAL = 1000 * 1;
    public static final long FASTEST_INTERVAL = 500 * 1;


    /**
     * Maintain the PREFERENCES Keys and constant
     */
    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }

}
