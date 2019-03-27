package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

public class ProfileActivity extends BaseAbstractActivity<Class> {
    private TextView tv_profile_name, tv_user_number_profile, user_email_profile;
    private ImageView iv_left_arrow;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.acitivity_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        tv_profile_name = findViewById(R.id.tv_profile_name);
        tv_user_number_profile = findViewById(R.id.tv_user_number_profile);
        user_email_profile = findViewById(R.id.user_email_profile);
        iv_left_arrow = findViewById(R.id.iv_left_arrow);
        tv_profile_name.setText(SharedPrefsUtils.getInstance(ProfileActivity.this).getName());
        tv_user_number_profile.setText(SharedPrefsUtils.getInstance(ProfileActivity.this).getPhoneNumber());
        user_email_profile.setText(SharedPrefsUtils.getInstance(ProfileActivity.this).getEmial());
        iv_left_arrow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                finish();
            }
        });

    }
}
