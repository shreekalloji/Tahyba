package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

public class SplashScreenActivity<Class> extends BaseAbstractActivity {
    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_splash_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        new Handler().postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {

                if (SharedPrefsUtils.getInstance(SplashScreenActivity.this).isUserLoggedIn() == true) {
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                    finish();
                } else {
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                    finish();
                }
            }

        }, 3000);
    }
}
