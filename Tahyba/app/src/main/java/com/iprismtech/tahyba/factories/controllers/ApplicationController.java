package com.iprismtech.tahyba.factories.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.iprismtech.tahyba.MyApplication;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.ViewFactory;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from DriverLoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * sets the reference of current mActivity
     *
     * @param mActivity
     */
    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * @return the application mContext
     */
    public MyApplication getApplication() {
        return application;
    }

    /**
     * sets the reference of application
     *
     * @param application
     */
    public void setApplication(MyApplication application) {
        this.application = application;
    }

    /**
     * @return the mContext of current mActivity
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * sets the reference of mContext
     *
     * @param
     */
    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    /**
     * Handle Event Based on Event ID
     *
     * @param eventId Event raised by View
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint({"WrongConstant", "SwitchIntDef"})
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {
            case AppConstants.EventIds.LAUNCH_SPLASH_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SPLASH_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_MAIN_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.MAIN_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;
            case AppConstants.EventIds.USER_SIGNUP_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.USER_SIGNUP_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_OTPVERIFICATION_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.OTP_VERIFICATION_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_LOCATION_GET_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.LOCATION_GET_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_OTP_VERIFICATIONSCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.OTP_VERIFICATION_PASSWORD_FORGOT_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_RESET_PASSWORD_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.RESET_PASSWORD_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SHOPS_LIST_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SHOPS_LISTS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_PROFILE_ACTIVITY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.USER_PROFILE_ACTIVITY_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_CONTACT_US_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.CONTACT_US_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_ABOUT_US_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ABOUT_US_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_SHOPS_SEARCH_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SHOPS_SEARCH_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_ENTER_MOBILE_NUMBER_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ENTER_MOBILE_NUMBER_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_APP_FEEDBACK_US_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.APP_FEEDBACK_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_SHOP_DETAILS_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SHOP_DETAILS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_USER_RATING_FEEDBACK_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.USER_FEEDBACK_RATING_SCREEN, (Bundle) eventObjects);
                break;
            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
